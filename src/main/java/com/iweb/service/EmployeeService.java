package com.iweb.service;

import com.iweb.dao.DepartmentDao;
import com.iweb.dao.EmployeeDao;
import com.iweb.dao.PostDao;
import com.iweb.dao.impl.DepartmentDaoImpl;
import com.iweb.dao.impl.EmployeeDaoImpl;
import com.iweb.dao.impl.PostDaoImpl;
import com.iweb.pojo.Department;
import com.iweb.pojo.Employee;
import com.iweb.pojo.Post;
import com.iweb.util.Print;
import com.iweb.util.StringUtil;
import com.iweb.view.AnnouncementView;
import com.iweb.view.EmployeeView;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:13
 */
public class EmployeeService {
    private static EmployeeDao employeeDao=new EmployeeDaoImpl();
    private static DepartmentDao departmentDao=new DepartmentDaoImpl();
    private static PostDao postDao=new PostDaoImpl();
    public static void addEmployee(Employee e){
        if (!checkEidNumber(e.getEidNumber())){
            Print.write("输入的身份证号不正确");
            EmployeeView.employeeManagementView();
        }
        if (!checkEPhone(e.getEPhone())){
            Print.write("输入的手机号不正确");
            EmployeeView.employeeManagementView();
        }
        if (!checkESex(e.getESex())){
            Print.write("输入的性别不正确");
            EmployeeView.employeeManagementView();
        }if (!checkDepartment(e.getDepartment())){
            Print.write("输入的部门不存在");
            EmployeeView.employeeManagementView();
        }if (!checkPost(e.getPost())){
            Print.write("输入的职位不存在");
            EmployeeView.employeeManagementView();
        }
        boolean succeed=employeeDao.addEmployee(e);
        if (succeed){
            Print.write("添加成功");
        }else{
            Print.write("添加异常，即将回到上一页面");
        }
        EmployeeView.employeeManagementView();
    }

    public static void queryAllEmployee(){
        List<Employee> employeeList = employeeDao.queryAllEmployee();
        for (Employee e :employeeList) {
            Print.write("编号:"+e.getEid()+"\t部门名:"+e.getEName()+"\t身份证号"+
                    e.getEidNumber()+"\t手机号"+e.getEPhone()+"\t性别"+e.getESex()+
                    "\t部门名"+departmentDao.queryById(e.getDepartment().getDid()).getDName()+
                    "\t职位名"+postDao.queryById(e.getPost().getPid()).getPName());
        }
        EmployeeView.employeeManagementView();
    }

    public static void employeeFuzzyQuery(String str){
        List<Employee> employeeList = employeeDao.queryEmployeeByStr(str);
        for (Employee e:employeeList) {
            Print.write("编号:"+e.getEid()+"\t部门名:"+e.getEName()+"\t身份证号"+
                    e.getEidNumber()+"\t手机号"+e.getEPhone()+"\t性别"+e.getESex()+
                    "\t部门名"+departmentDao.queryById(e.getDepartment().getDid()).getDName()+
                    "\t职位名"+postDao.queryById(e.getPost().getPid()).getPName());
        }
    }

    public static void deleteEmployee(String str){
        if(StringUtil.isInteger(str)){
            int id = Integer.parseInt(str);
            boolean succeed = employeeDao.deleteEmployee(id);
            if (succeed){
                Print.write("删除成功");
            }else {
                Print.write("删除异常，即将返回上一页面");
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
        }
        EmployeeView.employeeManagementView();
    }

    public static void updateEmployee(Employee e){
        if (e.getEName()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEidNumber(original.getEidNumber());
            e.setEPhone(original.getEPhone());
            e.setESex(original.getESex());
            e.setDepartment(departmentDao.queryById(original.getDepartment().getDid()));
            e.setPost(postDao.queryById(original.getPost().getPid()));
        }
        if (e.getEidNumber()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEName(original.getEName());
            e.setEPhone(original.getEPhone());
            e.setESex(original.getESex());
            e.setDepartment(departmentDao.queryById(original.getDepartment().getDid()));
            e.setPost(postDao.queryById(original.getPost().getPid()));
        }
        if (e.getEPhone()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEidNumber(original.getEidNumber());
            e.setEName(original.getEName());
            e.setESex(original.getESex());
            e.setDepartment(departmentDao.queryById(original.getDepartment().getDid()));
            e.setPost(postDao.queryById(original.getPost().getPid()));
        }
        if (e.getESex()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEidNumber(original.getEidNumber());
            e.setEPhone(original.getEPhone());
            e.setEName(original.getEName());
            e.setDepartment(departmentDao.queryById(original.getDepartment().getDid()));
            e.setPost(postDao.queryById(original.getPost().getPid()));
        }
        if (e.getDepartment()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEidNumber(original.getEidNumber());
            e.setEPhone(original.getEPhone());
            e.setESex(original.getESex());
            e.setEName(original.getEName());
            e.setPost(postDao.queryById(original.getPost().getPid()));
        }
        if (e.getPost()!=null){
            Employee original = employeeDao.queryById(e.getEid());
            e.setEidNumber(original.getEidNumber());
            e.setEPhone(original.getEPhone());
            e.setESex(original.getESex());
            e.setDepartment(departmentDao.queryById(original.getDepartment().getDid()));
            e.setEName(original.getEName());
        }
        boolean succeed = employeeDao.updateEmployee(e);
        if (succeed){
            Print.write("更新成功");
        }else {
            Print.write("更新异常，返回上一页面");
        }
        AnnouncementView.updateAnnouncementView();
    }

    private static boolean checkEidNumber(String eidNumber){
        String regex="[0-9]{18}";
        if (eidNumber.matches(regex)){
            return true;
        }else {
            return false;
        }
    }
    private static boolean checkEPhone(String ePhone){
        String regex="[0-9]{11}";
        if (ePhone.matches(regex)){
            return true;
        }else {
            return false;
        }
    }
    private static boolean checkESex(String eSex){
        if (eSex.equals("男")||eSex.equals("女")){
            return true;
        }else {
            return false;
        }
    }
    private static boolean checkDepartment(Department d){
        departmentDao.queryByName(d);
        if (d.getDid()!=0){
            return true;
        }else {
            return false;
        }
    }

    private static boolean checkPost(Post p){
        postDao.queryByName(p);
        if (p.getPid()!=0){
            return true;
        }else {
            return false;
        }
    }
}
