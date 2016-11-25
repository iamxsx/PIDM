package com.oneclouder.pidm.survey.action;

import com.oneclouder.pidm.n_user.service.INUserService;
import com.oneclouder.pidm.n_user.web_bean.NUserInfo;
import com.oneclouder.pidm.survey.model.TOption;
import com.oneclouder.pidm.survey.service.IOptionService;
import com.oneclouder.pidm.survey.service.IQuestionnaireSurveyService;
import com.oneclouder.pidm.survey.service.ITopicService;
import com.oneclouder.pidm.survey.webBean.SurveyFormBean;
import com.oneclouder.pidm.survey.webBean.TopicFormBean;
import com.oneclouder.pidm.user.webBean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouder on 10/19/16.
 */
@Controller
@RequestMapping("survey")
public class foregroundSurveyAction {

    @Resource(name = "questionnaireSurveyService")
    private IQuestionnaireSurveyService questionnaireSurveyService;

    @Resource(name = "topicService")
    private ITopicService topicService;

    @Resource(name = "optionService")
    private IOptionService optionService;

    @Resource(name = "nUserService")
    private INUserService nUserService;

    /**
     * 参与调查的人数,选项统计
     * @param survey
     * @throws SQLException
     */
    @RequestMapping(value = "statistics",method = RequestMethod.POST)
    @Transactional
    public String statistics(String menu,SurveyFormBean survey, Integer userId) throws SQLException, UnsupportedEncodingException {
//        System.out.println(survey);
        //更新调查表的统计人数
        questionnaireSurveyService.updateJoinNum(survey.getQuestionnaireSurvey().getId());
        List<TopicFormBean> topics = survey.getTopic();
        for (TopicFormBean topic:topics) {
            if (topic.getNature() == 0 || topic.getNature() == 2){
                List<TOption> options = topic.getOption();
                if(options!=null){
                    for (TOption option:options) {
                        if (option.getId() != null){
                            optionService.updateCount(option.getId());
                        }
                    }
                }
            }else if(topic.getNature() == 1){
                topic.getOption().get(0).setTopicId(topic.getTid());
                optionService.insertOption(topic.getOption().get(0));
            }
        }
        //更新用户表survey_id字段
        nUserService.updataUserSurveyId(survey.getQuestionnaireSurvey().getId(),userId);
//        String param = new String("需求调查".getBytes(),"");
        String param = URLEncoder.encode("menu=需求调查","utf-8");
        return "redirect:/section/survey/SurveyInfo?"+param+"&succSurvey=0";
    }

    /**
     * 判断用户是否填写过调查表
     * @param userId
     * @return
     */
    @RequestMapping("isWriteSurvey")
    @ResponseBody
    public String isWriteSurvey(Integer userId,Integer s_id){
        Integer surveyId = nUserService.isWriteSurvey(userId);
        if (surveyId == null || surveyId != s_id){
            return "notWrite";
        }else {
            return "isWrite";
        }
    }
}
