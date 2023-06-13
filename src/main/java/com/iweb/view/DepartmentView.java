package com.iweb.view;

import com.iweb.controller.DepartmentController;
import com.iweb.pojo.Department;
import com.iweb.service.DepartmentService;
import com.iweb.util.Print;

/**
 * @author ichinose
 * @date 2023/6/12 15:47
 */
public class DepartmentView {
    /**
     * 管理员部门管理页面
     */
    public static void departmentManagementView(){
        Print.write("1.添加部门");
        Print.write("2.查询部门");
        Print.write("3.删除部门");
        Print.write("4.修改部门");
        Print.write("5.返回上一步");
        String str=Print.read();
        DepartmentController.departmentManagementController(str);
    }

    /**
     * 管理员新增部门页面
     */
    public static void addDepartmentView(){
        Print.write("请输入需要添加的部门名称");
        String str=Print.read();
        DepartmentService.addDepartment(str);
    }

    /**
     * 部门查询页面
     */
    public static void departmentInquiryView(){
        Print.write("1.查询所有部门");
        Print.write("2.模糊查询");
        Print.write("3.返回上一步");
        String str=Print.read();
        DepartmentController.departmentInquiryController(str);
    }
    public static void departmentFuzzyQueryView(){
        Print.write("请输入字符串进行模糊查询");
        String str= Print.read();
        DepartmentService.departmentFuzzyQuery(str);
    }
    public static void deleteDepartmentView(){
        Print.write("请输入需要删除的编号:");
        String str = Print.read();
        DepartmentService.deleteDepartment(str);
    }
    public static void updateDepartmentView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的部门名称：");
        String departmentName = Print.read();
        DepartmentService.updateDepartment(id,departmentName);
    }
}
