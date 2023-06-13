package com.iweb.dao;

import com.iweb.pojo.Announcement;
import com.iweb.pojo.Employee;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:17
 */
public interface EmployeeDao {
    /**根据传进的员工信息新建员工
     * @param e 传入需要新建的员工
     * @return 传入成功返回true，失败返回false
     */
    boolean addEmployee(Employee e);

    /**查询全部员工
     * @return 返回数据库中员工的信息
     */
    List<Employee> queryAllEmployee();

    /**员工模糊查询
     * @param str 传入进行员工模糊查询的字段
     * @return 返回查询到结果集合
     */
    List<Employee> queryEmployeeByStr(String str);

    /**根据id删除员工
     * @param id 传入需要删除的员工id
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteEmployee(int id);

    /**根据id更新员工信息
     * @param e 传入需要更新的员工信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateEmployee(Employee e);

    /**查询该部门下的员工
     * @param did 传入部门id
     * @return 返回员工集合
     */
    List<Employee> queryByDid(int did);

    /**查询该职位下的员工
     * @param pid 传入职位id
     * @return 返回员工集合
     */
    List<Employee> queryByPid(int pid);

    /**根据员工编号查询出员工信息
     * @param eid 传入需要查询的员工编号
     * @return 返回员工信息
     */
    Employee queryById(int eid);
}
