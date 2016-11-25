package com.oneclouder.pidm.survey.action;

import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.survey.model.TOption;
import com.oneclouder.pidm.survey.model.TQuestionnaireSurvey;
import com.oneclouder.pidm.survey.model.TTopic;
import com.oneclouder.pidm.survey.service.IOptionService;
import com.oneclouder.pidm.survey.service.IQuestionnaireSurveyService;
import com.oneclouder.pidm.survey.service.ITopicService;
import com.oneclouder.pidm.survey.webBean.SurveyFormBean;
import com.oneclouder.pidm.survey.webBean.TopicFormBean;
import com.oneclouder.pidm.user.service.IUserService;
import com.oneclouder.pidm.user.webBean.UserInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by clouder on 10/7/16.
 */
@RequestMapping(value = "/back/survey")
@Controller
public class SurveyAction {

    @Resource(name = "questionnaireSurveyService")
    private IQuestionnaireSurveyService questionnaireSurveyService;

    @Resource(name = "topicService")
    private ITopicService topicService;

    @Resource(name = "optionService")
    private IOptionService optionService;

    @Resource(name = "nUserService")
    private INUserService nUserService;

    /**
     * 跳转到后台发布调查表页面
     * @return
     */
    @RequestMapping(value = "questionSurvey",method = RequestMethod.GET)
    public String questionSurvey(){
        return "/back/survey/questionSurvey";
    }

    /**
     * 跳转到后台修改意见调查表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "changeSurvey",method = RequestMethod.GET)
    public ModelAndView changeSurvey(ModelAndView model){
        SurveyFormBean surveyFormBean = questionnaireSurveyService.SurveyInfo();

        if (surveyFormBean.getTopic()!=null){
            Integer[] num = {0,0,0};
            surveyFormBean.getQuestionnaireSurvey().setJoinNum(surveyFormBean.getTopic().size());
            for (TopicFormBean topic:surveyFormBean.getTopic()) {
                if(topic.getNature() == 0){
                    num[0]++;
                }else if(topic.getNature() == 1){
                    num[1]++;
                }else if(topic.getNature() == 2){
                    num[2]++;
                }
            }
            surveyFormBean.setSingselection(num[0]);
            surveyFormBean.setMulselect(num[2]);
            surveyFormBean.setComp(num[1]);
        }
        model.addObject("survey",surveyFormBean);
        model.setViewName("/back/survey/changeSurvey");
        return model;
    }

    /**
     * 当前调查问卷 统计情况  zcb
     */
    @RequestMapping(value = "statisChart")
    public String statisChart(){
        return "/back/survey/statisChart";
    }
    @RequestMapping(value = "statisResponse")
    @ResponseBody
    public Map<String,Object> statisResponse(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("survey",questionnaireSurveyService.SurveyInfo());
        return map;
    }
    @RequestMapping(value = "overSurvey")
    @ResponseBody
    public Map<String,Object> overSurvey(Integer id){
        Map<String,Object> map = new HashedMap();
        questionnaireSurveyService.overSurvey(id);
        map.put("msg",true);
        return map;
    }

    /**
     * 查看历史调查表统计    zcb
     */
    @RequestMapping(value = "historyStatis")
    public String historyStatis(){
        return "/back/survey/historyStatis";
    }
    @RequestMapping(value = "historyResponse")
    @ResponseBody
    public Map<String,Object> historyResponse(){
        Map<String,Object> map = new HashedMap();
        List<SurveyFormBean> surveyFormBeans = questionnaireSurveyService.historySurveyInfo();
        Integer total = surveyFormBeans.size();
        map.put("total",total);
        map.put("rows",surveyFormBeans);
        return map;
    }

    /**
     * 根据调查表ID查看统计情况
     */
    @RequestMapping(value = "showStatisChart")
    public ModelAndView showStatisChart(ModelAndView model,Integer id){
        model.addObject("id",id);
        model.setViewName("back/survey/showStatisChart");
        return model;
    }
    @RequestMapping(value = "showStatisResponse")
    @ResponseBody
    public Map<String,Object> showStatisResponse(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("survey",questionnaireSurveyService.findSurveyInfoById(id));
        return map;
    }


    @RequestMapping(value = "surveyManage",method = RequestMethod.GET)
    public ModelAndView surveyManage(ModelAndView model,Integer sta){
        if (sta == null){
            model.addObject("mess","创建意见调查表");
        }else if (sta == 1){
            model.addObject("mess","调查表创建成功,继续创建意见调查表?");
        }else if (Objects.equals(sta, 2)){
            model.addObject("mess","调查表已中止调查或未创建调查表,请先创建意见调查表");
        }

        model.setViewName("/back/survey/surveyManage");
        return model;

    }


    /**
     * 插入题目信息
     * @param surveyFormBean
     * @throws SQLException
     */
    @Transactional
    public void insertTopicInfo(SurveyFormBean surveyFormBean) throws SQLException {
        List<TopicFormBean> topicFormBean = surveyFormBean.getTopic();
        for (TopicFormBean topic:topicFormBean) {
            if (topic.getTopicContent()!=null){
                TTopic top = new TTopic();
                top.setNature(topic.getNature());
                top.setSurveyId(surveyFormBean.getQuestionnaireSurvey().getId());
                top.setTopicContent(topic.getTopicContent());
                topicService.insertTopic(top);

                if (top.getNature() == 0 || top.getNature() == 2){
                    List<TOption> optionList = topic.getOption();
                    for (TOption option:optionList) {
                        if(option.getOptionContent()!=null){
                            option.setTopicId(top.getId());
                            option.setsId(surveyFormBean.getQuestionnaireSurvey().getId());
                            optionService.insertOption(option);
                        }
                    }
                }
            }
        }
    }

    /**
     * 插入调查表信息
     * @param surveyFormBean
     * @throws SQLException
     */
    @RequestMapping(value = "saveSurveyInfo",method = RequestMethod.POST)
    @Transactional
    public String saveSurveyInfo(SurveyFormBean surveyFormBean) throws SQLException {
        /*Integer titleNum = surveyFormBean.getTopic()
        System.out.println(titleNum);*/

        /*System.out.println(surveyFormBean);*/
        /*surveyFormBean.getQuestionnaireSurvey().setTitleNum(titleNum);*/
        questionnaireSurveyService.insertSurvey(surveyFormBean.getQuestionnaireSurvey());
        insertTopicInfo(surveyFormBean);
        return "redirect:surveyManage?sta=1";
    }

    /**
     * 删除题目
     * @param deleteId
     * @return
     */
    @RequestMapping("deleteTopic")
    @ResponseBody
    public String deleteTopic(Integer deleteId){
        try {
            topicService.deleteTopicById(deleteId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return null;
    }

    /**
     * 修改标题,题目,选项内容
     * @param id
     * @param content
     * @param junature
     * @return
     */
    @RequestMapping("changeContent")
    @ResponseBody
    public String changeContent(Integer id,String content,Integer junature){
        try {
            if (junature == 0){
                questionnaireSurveyService.updateTitle(content,id);
            }else if(junature == 1){
                topicService.updateTopic(content,id);
            }else if (junature == 2){
                optionService.updateOption(content,id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    /**
     * 重新发布调查表
     * @throws SQLException
     */
    @RequestMapping("change")
    @Transactional
    public String change(Integer id) throws SQLException {
        TQuestionnaireSurvey questionnaireSurvey = new TQuestionnaireSurvey();
        questionnaireSurvey.setJoinNum(0);
        questionnaireSurvey.setStatus(3);
        questionnaireSurvey.setId(id);
        //题目选项参与人数全部置为空
        optionService.countChangeZero(id);
        //更新调查表状态
        questionnaireSurveyService.updateDub(questionnaireSurvey);
        //更新会员survey_id字段
        nUserService.updataSurveyId();
        return "redirect:surveyManage";
    }

    /**
     * 撤回调查表,修改调查表状态
     * @param id
     * @throws SQLException
     */
    @RequestMapping("changeStatus")
    @Transactional
    public String changeStatus(Integer id) throws SQLException {
        TQuestionnaireSurvey questionnaireSurvey = questionnaireSurveyService.findSurvey();
        questionnaireSurvey.setStatus(1);
        questionnaireSurveyService.updateDub(questionnaireSurvey);
        return "redirect:changeSurvey";
    }

    /**
     * 获取调查表状态
     * @throws SQLException
     */
    @RequestMapping("getSurveyStatus")
    @ResponseBody
    public String getSurveyStatus() throws SQLException {
        String status = questionnaireSurveyService.findStatus().toString();
        return status;
    }

    /**
     * 添加空题目
     * @param topicNature 题目类型(0:选择,1:填空)
     * @param sid      调查表id
     * @param optionNum    题目选项个数
     * @return
     * @throws SQLException
     */
    @RequestMapping("addTopic")
    @Transactional
    @ResponseBody
    public Map<String,Object> addTopic(Integer topicNature, Integer sid,Integer optionNum) throws SQLException {
        TTopic topic = new TTopic();
        Map<String,Object> map = new LinkedHashMap<>();
        topic.setSurveyId(sid);
        topic.setNature(topicNature);
        topicService.insertTopic(topic);
        map.put("tid",topic.getId());
        if(topicNature == 0 || topicNature == 2){
            List<TOption> options = new ArrayList<>();
            for (int i = 0; i< optionNum;i++){
                TOption option = new TOption();
                option.setTopicId(topic.getId());
                option.setsId(sid);
                if(i==0){
                    option.setNature("A");
                }else if(i==1){
                    option.setNature("B");
                }else if(i==2){
                    option.setNature("C");
                }else if(i==3){
                    option.setNature("D");
                }
                optionService.insertOption(option);
                options.add(option);
            }
            map.put("optionList",options);
        }
        return map;
    }

}
