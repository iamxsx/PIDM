package com.oneclouder.pidm.user.action;

import com.oneclouder.pidm.user.annotation.Token;
import com.oneclouder.pidm.user.model.Status;
import com.oneclouder.pidm.user.model.User;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.UserInfo;
import com.oneclouder.pidm.util.CookieUtil;
import com.oneclouder.pidm.util.MailUtil;
import com.oneclouder.pidm.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/16/16
 * Time: 3:34 PM
 */
@Controller
@RequestMapping("login")
public class MyLoginAction {
    @Resource(name = "userService")
    private IUserService userService;

    private Log log = LogFactory.getLog(this.getClass());

    // TODO 注意：正常来说是异步的 有时间控制一下跳转
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @Token(remove = true)
    public String login(String account, String password, String isRemember,
                        HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, SQLException {
//        System.out.println("account = " + account + " password=" + password + " isRemember=" + isRemember);
//        System.out.println(request.getSession().getAttribute("token"));
        User user = null;
        user = userService.login(account, password);
        if (user != null) {
            //创建userInfo,封装用户必要的登陆信息
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(user.getAccount());
            userInfo.setLoginDate(new Date());
            userInfo.setIp(getRemortIP(request));
            userInfo.setRealName(user.getRealName());
            userInfo.setId(user.getId());

            //获取当前会话的session
            HttpSession currSession = request.getSession();
            currSession.setAttribute("userInfo", userInfo);
            ServletContext context = request.getServletContext();
            Map<String, HttpSession> userInfos = (HashMap<String, HttpSession>) context.getAttribute("userInfos");
            //第一个用户登陆
            if (userInfos == null || userInfos.size() == 0) {
                userInfos = new HashMap<String, HttpSession>();
            } else {
                //用户不同ip登陆
                if (userInfos.containsKey(userInfo.getAccount())) {
                    //获取前一个用户的session 并将他的登陆信息注销掉
                    HttpSession oldSession = userInfos.get(userInfo.getAccount());
                    UserInfo oldUserInfo = (UserInfo) oldSession.getAttribute("userInfo");
                    log.info("账号" + oldUserInfo.getAccount() + "在" + userInfo.getIp() + "已经登录，该登录将被迫下线！");
                    oldSession.removeAttribute("userInfo");
                    oldSession.setAttribute("loginmsg", "您的账号已经在其他机器上登录,您被迫下线！");
                }
            }
            userInfos.put(userInfo.getAccount(), currSession);
            context.setAttribute("userInfos", userInfos);
            //若记住密码 存放到cookie中
            if (isRemember != null) {
                CookieUtil.addCookie(response, "account", user.getAccount(), 7);
                CookieUtil.addCookie(response, "password", user.getPassword(), 7);
            }
            return "redirect:../index";
        } else {
//            model.addAttribute("msg", "login-error");
            return "../../register/redirect?msg=login-error";
        }

    }

    @RequestMapping("/testVerifycode")
    @ResponseBody
    public String testVerifycode(String verifycode, HttpSession session) {
        String sVerifycode = (String) session.getAttribute("verifycode");
        if (verifycode.equals(sVerifycode)) {
            return "T";
        }
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        ServletContext context = session.getServletContext();
        Map<String, HttpSession> userInfos = (HashMap<String, HttpSession>) context.getAttribute("userInfos");
        if (userInfos != null && userInfos.size() > 0) {
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if (userInfo != null) {
                if (userInfos.containsKey(userInfo.getAccount())) {
                    userInfos.remove(userInfo.getAccount());
                }
            }
        }
        session.removeAttribute("userInfo");
        return "foreground/index";
    }

    //异步请求获取邮箱验证码
    @RequestMapping(value = "/get-verifycode")
    @ResponseBody
    public Map<String, Object> getVerifycode(String account, String email) throws SQLException, IOException {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(account)) {
            if (!userService.isExist(account)) {
                map.put("msg", Status.GETCODE_FAILED);
            }
        }
        //前四位
        String verifycode = UUIDUtil.generateUid().substring(0, 4);
        userService.addVerifycode(account, verifycode);
        MailUtil.sendVerifycode(email, verifycode);
        map.put("msg", Status.GETCODE_SUCCESS);
        return map;
    }

    // @RequestParam String account, @RequestParam String  password, @RequestParam String desiContactPhone, @RequestParam String verifycode
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(User user, Model model) throws SQLException {
        Integer row = userService.resetPassword(user);
        // TODO check return value
        if (row == 1) {
            model.addAttribute("msg", "密码修改成功");
            return "foreground/user/redirect";
        } else {
            model.addAttribute("msg", "邮箱验证码错误等原因，导致密码修改失败");
            return "foreground/user/password-reset";
        }
    }

    /**
     * 获取用户真实ip
     *
     * @param request
     * @return
     */
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
