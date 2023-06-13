package com.iweb.dao;

import com.iweb.pojo.Department;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:14
 */
public interface DepartmentDao {
    /**根据传进的部门名新建部门
     * @param d 传入需要新建的部门
     * @return 传入成功返回true，失败返回false
     */
    boolean addDepartment(Department d);

    /**查询全部部门
     * @return 返回数据库中部门的信息
     */
    List<Department> queryAllDepartment();

    /**部门模糊查询
     * @param str 传入进行部门模糊查询的字段
     * @return 返回查询到结果集合
     */
    List<Department> queryDepartmentByStr(String str);

    /**根据id删除部门，但是这个部门不能是1.未定义部门
     * @param id 传入需要删除的部门id
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteDepartment(int id);

    /**根据id更新部门名
     * @param d 传入需要更新的部门信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateDepartment(Department d);

    /**根据id查询部门信息
     * @param id 传入需要查询的id
     * @return 返回查询到的部门信息对象
     */
    Department queryById(int id);

    /**根据名称查询部门id
     * @param department 传入需要查询的名称
     * @return 返回查询到的部门信息，id为0代表查询不到信息
     */
    Department queryByName(Department department);
}
