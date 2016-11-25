package com.oneclouder.pidm.base.backAction;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.oneclouder.pidm.employee.model.Employee;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.util.EncryptUtils;
import com.oneclouder.pidm.util.PhoneValidateMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by PhychoLee on 10/9/16 11:50 AM.
 * Description: 验证码生成
 */
@Controller
@RequestMapping("/back/code")
public class CaptchaAction {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 获取验证码图片
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "captcha-image")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * 添加或修改员工时获取短信验证码
     * @param phoneNum
     * @param request
     * @return
     */
    @RequestMapping("/getphoneMsg")
    @ResponseBody
    public String getPhoneMsg(String phoneNum, HttpServletRequest request){
        //用kaptcha来生成6个随机数字，保存到session中
        HttpSession session = request.getSession();

        String delayMark = (String) session.getAttribute("DelayMark");
        if (delayMark!=null && delayMark.equals(phoneNum)){
            //同个号码在60s内只能获取一条验证码
            System.out.println("重复获取验证码过快！");
            return "sent";
        }

        String codeText = captchaProducer.createText();
        // TODO 我他妈改了，我他妈又改回来了
        session.setAttribute("PhoneMsgValidate", codeText);
        session.setAttribute("DelayMark", phoneNum);

        kill(session,1);

        System.out.println("这里是模拟发短信,手机号为"+phoneNum+",验证码为："+codeText);

        //发送短信
        String result = PhoneValidateMsgUtil.sendPhoneMsg(phoneNum, codeText);

        if (result.equals("balanceLow") || result.equals("failed")){
            //发送失败
            return "error";
        }

        kill(session,2);

        return "success";
    }

    /**
     * 检查验证码是否正确
     * @param phoneMsg
     * @return
     */
    @RequestMapping("/checkPhoneMsg")
    @ResponseBody
    public Map<String, Object> checkPhoneMsg(String phoneMsg, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            String capText = (String) request.getSession().getAttribute("PhoneMsgValidate");
            if (capText.equals(phoneMsg.trim())){
                map.put("valid", "true");
            }else {
                map.put("valid", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 登陆时获取短信验证码
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/getLoginPhoneMsg",method = RequestMethod.POST)
    @ResponseBody
    public String getLoginPhoneMsg(String username,String password, HttpServletRequest request){
        //用kaptcha来生成6个随机数字，保存到session中
        HttpSession session = request.getSession();

        String delayMark = (String) session.getAttribute("DelayMark");
        if (delayMark!=null && delayMark.equals(username)){
            //同个号码在60s内只能获取一条验证码
            System.out.println("重复获取验证码过快！");
            return "sent";
        }

        try {
            Employee byUserName = employeeService.findByUserName(username.trim());

            if (byUserName == null){
                //没有找到账号
                return "errorAccount";
            }

            String encryptPassword = EncryptUtils.encrypt(byUserName.getSalt(), password.trim());

            if (!encryptPassword.equals(byUserName.getPassword())){
                //密码错误
                return "errorAccount";
            }

            String phoneNum = byUserName.getCellPhoneNum();

            String codeText = captchaProducer.createText();
            session.setAttribute("PhoneMsgValidate", codeText);
            session.setAttribute("DelayMark", username);

            kill(session,1);

            System.out.println("这里是模拟登陆发短信,账号为"+username+",手机号为"+phoneNum+"验证码为："+codeText);

            //发送短信
            String result = PhoneValidateMsgUtil.sendPhoneMsg(phoneNum, codeText);

            if (result.equals("balanceLow") || result.equals("failed")){
                //发送失败
                return "error";
            }

            kill(session, 2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private void kill(HttpSession session, int tag){
        Timer timer = new Timer();
        if (tag == 1) {
            //60秒内不能再次获取
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (session.getAttribute("DelayMark")!=null){
                        session.removeAttribute("DelayMark");
                    }
                }
            }, 60000);
        }else if(tag == 2){
            //验证码有效时长10分钟
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (session.getAttribute("PhoneMsgValidate")!=null){
                        session.setAttribute("PhoneMsgValidate", "");
                    }
                }
            }, 600000);
        }

    }
}
