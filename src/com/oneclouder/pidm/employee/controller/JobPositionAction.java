package com.oneclouder.pidm.employee.controller;

import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.JobPosition;
import com.oneclouder.pidm.employee.service.IEmployeeService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/18/16 6:37 PM.
 * Description:
 */
@Controller
@RequestMapping("/back/jobposition")
public class JobPositionAction {

    @Autowired
    private IJobPositionService jobPositionService;
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 链接到jsp
     * @return
     */
    @RequestMapping("")
    public String jobposition(HttpServletRequest request){
        return "back/system/job-position";
    }

    /**
     * 根据部门获取岗位，分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getJobPositionByPage", method = RequestMethod.POST)
    @ResponseBody
    public List<JobPosition> getJobPositionByPage(@RequestBody Map<String, Object> params){

        Integer deptId = (Integer) params.get("deptId");
        String jpName = (String) params.get("jpName");

        List<JobPosition> jobPositionList = null;
        try {
            jobPositionList = jobPositionService.findByDeptByPage(deptId, jpName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobPositionList;
    }

    /**
     * 根据部门获取岗位部分信息
     * @param deptId
     * @return
     */
    @RequestMapping(value = "getJobPositionByDept", method = RequestMethod.GET)
    @ResponseBody
    public List<JobPosition> getJobPositionByDept(@RequestParam("deptId") Integer deptId){
        List<JobPosition> jobPositionList = null;
        try {
            jobPositionList = jobPositionService.findByDept(deptId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobPositionList;
    }

    /**
     * 保存岗位
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveJP", method = RequestMethod.POST)
    @ResponseBody
    public String saveJP(@RequestBody Map<String, Object> params){
        String name = (String) params.get("name");
        String description = (String) params.get("description");

        Integer deptId;
        if ("".equals(params.get("departmentId"))){
            deptId = null;
            return "error";
        } else {
            deptId = (Integer) params.get("departmentId");
        }

        JobPosition jobPosition = new JobPosition();
        jobPosition.setName(name);
        jobPosition.setDescription(description);

        Department department = new Department();
        department.setId(deptId);
        jobPosition.setDepartment(department);

        try {
            jobPositionService.insert(jobPosition);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    /**
     * 根据id获取岗位
     * @return
     */
    @RequestMapping(value = "/getJobPosition", method = RequestMethod.GET)
    @ResponseBody
    public JobPosition getJobPosition(Integer id){
        JobPosition jobPosition = null;
        try {
            jobPosition= jobPositionService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobPosition;
    }

    /**
     * 更新岗位信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateJP", method = RequestMethod.POST)
    @ResponseBody
    public String updateJP(@RequestBody Map<String, Object> params){
        String name = (String) params.get("name");
        String description = (String) params.get("description");

        Integer id;
        if ("".equals(params.get("id"))){
            id = null;
            return "error";
        } else {
            id = Integer.valueOf((String) params.get("id"));
        }

        Integer deptId;
        if ("".equals(params.get("departmentId"))){
            deptId = null;
            return "error";
        } else {
            deptId = Integer.valueOf((String) params.get("departmentId"));
        }

        JobPosition jobPosition = new JobPosition();
        jobPosition.setId(id);
        jobPosition.setName(name);
        jobPosition.setDescription(description);

        Department department = new Department();
        department.setId(deptId);
        jobPosition.setDepartment(department);

        try {
            jobPositionService.update(jobPosition);
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    /**
     * 删除岗位
     * @param ids
     * @return
     */
    @RequestMapping("/deleteJP")
    @ResponseBody
    public String deleteJP(String ids){
        try {
            String[] jpIds = ids.split(",");
            for (String id : jpIds) {
                if (!"".equals(id.trim())) {
                    Integer jpId = Integer.valueOf(id);

                    Integer count = employeeService.countByJP(jpId);
                    if (count>0){
                        //表示此岗位下有员工
                        return "reference";
                    }
                }
            }

            jobPositionService.deleteJP(ids);

        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
