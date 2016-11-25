package com.oneclouder.pidm.n_user.service;

import com.oneclouder.pidm.base.service.IBaseService;
import com.oneclouder.pidm.n_user.model.NCompanyEmployee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-14
 * @Time: 上午8:58
 */
public interface INCompanyEmployeeService extends IBaseService<NCompanyEmployee> {
    //--------------------------------成员变量----------------------------------


    //--------------------------------hxj----------------------------------
//    void deleteUserInfo(Integer eid);
    void updateEmployeeInfo(NCompanyEmployee nCompanyEmployee);


    //--------------------------------zcb----------------------------------

    /**
     * 通过公司ID 查找公司员工
     **/
    List<NCompanyEmployee> getCompanyEmployeeByCId(Integer cId);
    /**
     * 存公司员工入 临时表*
     * */
    void insertToNTempCompanyEmp(NCompanyEmployee nCompanyEmployee);
    /**
     * 根据公司ID 删除员工ID
     */
    void deleteByCompanyId(Integer companyId);

    //--------------------------------xucb---------------------------------

    /**
     * 通过临时表 公司ID 查找公司员工
     * @param cId
     * @return
     */
    List<NCompanyEmployee> getTCompanyEmployeeByCId(Integer cId);

    //--------------------------------zjf----------------------------------
    /**
     * Created By IntelliJ IDEA
     * 插入NCompanyEmployee
     * @Author: AngryFeng
     * @Date: 16-10-16 下午3:34
     * 封装companyId 插入NCompanyEmployee对象
     * @param cpmpanyId 单位id
     * @param employee 单位员工
     * @param target 插入标志:1-插入正式表 2-插入临时表
     * @throws SQLException
     * @return Integer 插入不成功返回null 成功返回id
     */
    public Integer creatCompanyEmployee(Integer cpmpanyId, NCompanyEmployee employee, Integer target) throws SQLException;


    /**
     * Created By IntelliJ IDEA
     * 封装对象的companyId 批量插入NCompanyEmployee
     * @Author: AngryFeng
     * @Date: 16-10-16 下午4:02
     * @param cpmpanyId 单位id
     * @param employees 单位员工集合
     * @param target 插入标志:1-插入正式表 2-插入临时表
     * @throws SQLException
     * @return employeesId 插入员工的id
     */
    List<Integer> creatCompanyEmployees(Integer cpmpanyId, List<NCompanyEmployee> employees, Integer target) throws SQLException;


}
