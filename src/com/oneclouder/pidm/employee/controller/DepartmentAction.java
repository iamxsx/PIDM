package com.oneclouder.pidm.employee.controller;

import com.oneclouder.pidm.employee.model.Department;
import com.oneclouder.pidm.employee.model.JobPosition;
import com.oneclouder.pidm.employee.service.IDepartmentService;
import com.oneclouder.pidm.employee.service.IJobPositionService;
import com.oneclouder.pidm.menu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhychoLee on 9/17/16 3:48 PM.
 * Description:部门Action类
 */
@Controller
@RequestMapping("/back/department")
public class DepartmentAction {
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IJobPositionService jobPositionService;
    @Autowired
    private IMenuService menuService;

    /**
     * 链接到jsp
     * @return
     */
    @RequestMapping("")
    public String department(HttpServletRequest request){
//        menuService.setBackMenu(request);
        return "back/system/department";
    }


    @RequestMapping("/getDepartment")
    @ResponseBody
    public Map<String, Object> getDepartment(HttpServletRequest request, Integer deptId, @RequestParam(required = false) Integer mark){
        Map<String, Object> map = new HashMap<>();
        try {
            Department department = departmentService.findById(deptId);
            Department parent =null;
            if (department.getParentId() != null){
                 parent = departmentService.findById(department.getParentId());
            }

            map.put("id", department.getId());
            map.put("code", department.getCode());
            map.put("name", department.getName());
            map.put("parent", parent == null ? "" : parent.getName());
            map.put("parentId", department.getParentId());
            map.put("description", department.getDescription());

            if (mark != null && mark.equals(33)){
                request.getSession().setAttribute("updateDepartmentId", department.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            map.put("error","获取部门信息失败");
        }
        return map;
    }

    /**
     * 获取所有部门
     * @return
     */
    @RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getDepartments(){
        List<Object> mapList = null;
        List<Department> departments = null;
        try {
            departments = departmentService.iterateDepartment(null);
            mapList = transform2Map(departments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    /**
     * 将部门数据转为Bootsrap-treeview能解析的数据
     * @param list
     * @return
     */
    private List<Object> transform2Map(List<Department> list){
        List<Object> mapList = new ArrayList<>();
        if (list != null && list.size() != 0){
            for (Department department : list){
                Map<String, Object> map = new HashMap<>();
                map.put("text", department.getName());
                map.put("href", department.getId());

                List<Department> childList = department.getChildList();
                if (childList != null && childList.size() != 0){
                    map.put("nodes", transform2Map(childList));
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    @RequestMapping("/updateDept")
    @ResponseBody
    public Map<String, Object> updateDept(@RequestBody Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();

        String code = (String) params.get("code");
        String name = (String) params.get("name");
        String description = (String) params.get("description");
        String parentId = (String) params.get("parentId");
        Integer parentId2;
        if (parentId ==null || "".equals(parentId.trim())){
            parentId2 = null;
        } else {
            parentId2 = Integer.valueOf(parentId);
        }

        Integer id;
        if ("".equals(params.get("id"))){
            throw new RuntimeException("传入id不能为空");
        } else {
            id = Integer.valueOf((String) params.get("id"));
        }

        try {
            //判断当前编号是否已被其他部门使用
            Department byCode = departmentService.findByCode(code);
            if (byCode != null && !byCode.getId().equals(id)){
                map.put("code", "conflict");
                throw new RuntimeException("编号已存在");
            }

            Department byNameAndParent = departmentService.findByNameAndParent(name, parentId2);
            if (byNameAndParent != null && byCode.getId() != id){
                map.put("name", "conflict");
                throw new RuntimeException("父部门下已有此部门名称存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }

        Department department = new Department();
        department.setId(id);
        department.setCode(code);
        department.setName(name);
        department.setDescription(description);

        try {
            departmentService.update(department);
        } catch (SQLException e) {
            e.printStackTrace();
            map.put("error", "error");
            return map;
        }

        map.put("success", "success");
        return map;
    }

    /**
     * 新增部门
     * @param params
     * @return
     */
    @RequestMapping("/saveDept")
    @ResponseBody
    public Map<String, Object> saveDept(@RequestBody Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();

        String parentId = (String) params.get("parentId");
        String code = (String) params.get("code");
        String name = (String) params.get("name");
        String description = (String) params.get("description");
        Integer parentId2;
        if ("".equals(parentId.trim())){
            parentId2 = null;
        } else {
            parentId2 = Integer.valueOf(parentId);
        }

        try {
            //判断当前编号是否已使用
            Department byCode = departmentService.findByCode(code);
            if (byCode != null){
                map.put("code", "conflict");
                throw new RuntimeException("编号已存在");
            }

            Department byNameAndParent = departmentService.findByNameAndParent(name, parentId2);
            if (byNameAndParent != null){
                map.put("name", "conflict");
                throw new RuntimeException("父部门下已有此部门名称存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }

        Department department = new Department();
        department.setCode(code);
        department.setName(name);
        department.setDescription(description);
        department.setParentId(parentId2);

        try {
            departmentService.insert(department);
        } catch (SQLException e) {
            e.printStackTrace();
            map.put("error", "error");
            return map;
        }

        map.put("success", "success");
        map.put("id", department.getId());
        return map;
    }

    /**
     * 检查编号有没有被占用，新增
     * @param code
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> checkCode(String code){
        Map<String, Object> map = new HashMap<>();
        try {
            Department byCode = departmentService.findByCode(code);
            if (byCode == null){
                map.put("valid", "true");
            }else {
                map.put("valid", "false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 检查编号有没有被占用,更新
     * @param code
     * @return
     */
    @RequestMapping("/checkCode2")
    @ResponseBody
    public Map<String, Object> checkCode2(HttpServletRequest request, String code){
        Map<String, Object> map = new HashMap<>();
        try {
            Department byCode = departmentService.findByCode(code);
            Integer id = (Integer) request.getSession().getAttribute("updateDepartmentId");
            if (byCode != null && !byCode.getId().equals(id)){
                map.put("valid", "false");
            }else {
                map.put("valid", "true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteDept")
    @ResponseBody
    public Map<String, Object> deleteDept(Integer id){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Department> byParent = departmentService.findByParent(id);
            if (byParent.size()>0){
                map.put("child", "notNull");
                throw new RuntimeException("此部门下有子部门");
            }

            List<JobPosition> byDept = jobPositionService.findByDept(id);
            if (byDept.size()>0){
                map.put("JP", "notNull");
                throw new RuntimeException("此部门下有岗位");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }

        try {
            departmentService.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            map.put("error", "error");
            return map;
        }

        map.put("success","success");
        return map;
    }

}
