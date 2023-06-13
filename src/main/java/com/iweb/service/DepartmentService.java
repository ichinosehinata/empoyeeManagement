package com.iweb.service;

import com.iweb.dao.DepartmentDao;
import com.iweb.dao.EmployeeDao;
import com.iweb.dao.impl.DepartmentDaoImpl;
import com.iweb.dao.impl.EmployeeDaoImpl;
import com.iweb.pojo.Department;
import com.iweb.pojo.Employee;
import com.iweb.util.Print;
import com.iweb.view.DepartmentView;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:13
 */
public class DepartmentService {
    private static DepartmentDao departmentDao=new DepartmentDaoImpl();
    private static EmployeeDao employeeDao=new EmployeeDaoImpl();
    public static void addDepartment(String departmentName){
        Department department=new Department();
        department.setDName(departmentName);
        boolean succeed = departmentDao.addDepartment(department);
        if (succeed){
            Print.write("部门添加成功");
        }else {
            Print.write("部门添加失败");
        }
        DepartmentView.departmentManagementView();
    }
    public static void queryAllDepartment(){
        List<Department> departmentList = departmentDao.queryAllDepartment();
        for (Department d :departmentList) {
            Print.write("编号:"+d.getDid()+";部门名:"+d.getDName());
        }
        DepartmentView.departmentManagementView();
    }
    public static void departmentFuzzyQuery(String str){
        List<Department> departmentList = departmentDao.queryDepartmentByStr(str);
        for (Department d:departmentList) {
            Print.write("编号:"+d.getDid()+";部门名"+d.getDName());
        }
        DepartmentView.departmentManagementView();
    }
    public static void deleteDepartment(String str){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (str.matches(regex)) {
            int id = Integer.parseInt(str);
            if (id==1){
                Print.write("此部门设定不可删除，请重新输入");
                DepartmentView.departmentManagementView();
            }else{
                Department d=departmentDao.queryById(id);
                if (d.getDName()!=null){
                    List<Employee> employeeList=employeeDao.queryByDid(id);
                    Print.write("该部门下有这些员工：");
                    for (Employee e:employeeList) {
                        Print.write("员工编号:"+e.getEid()+"\t员工姓名:"+e.getEName()+"\t身份编号:"+e.getEidNumber()+
                                "\t手机号:"+e.getEPhone()+"\t性别:"+e.getESex());
                        e.getDepartment().setDid(1);
                    }
                    Print.write("确定要删除吗？(y是,n否)");
                    String choose=Print.read();
                    if (choose.equalsIgnoreCase("y")){
                        if (employeeList.size()!=0) {
                            for (Employee e : employeeList) {
                                employeeDao.updateEmployee(e);
                            }
                        }
                        boolean succeed = departmentDao.deleteDepartment(id);
                        if (succeed){
                            Print.write("删除成功");
                        }else {
                            Print.write("删除异常，即将返回上一界面");
                        }
                        DepartmentView.departmentManagementView();
                    }else if(choose.equalsIgnoreCase("n")){
                        Print.write("已取消，即将返回上一步...");
                        DepartmentView.departmentManagementView();
                    }else{
                        Print.write("输入有误，即将返回上一界面...");
                        DepartmentView.departmentManagementView();
                    }
                }else{
                    Print.write("输入的编号不存在部门，请重新输入");
                    DepartmentView.departmentManagementView();
                }
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
            DepartmentView.departmentManagementView();
        }
    }
    public static void updateDepartment(String id,String departmentName){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (id.matches(regex)) {
            int did = Integer.parseInt(id);
            Department d=new Department(did,departmentName);
            boolean succeed=departmentDao.updateDepartment(d);
            if (succeed){
                Print.write("更新成功");
            }else {
                Print.write("更新异常，即将返回上一界面");
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
        }
        DepartmentView.departmentManagementView();
    }
}
