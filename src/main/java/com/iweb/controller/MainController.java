package com.iweb.controller;

import com.iweb.service.UserService;
import com.iweb.util.Print;
import com.iweb.util.ServerThread;
import com.iweb.view.*;

import java.io.IOException;

/**
 * @author ichinose
 * @date 2023/6/11 16:28
 */
public class MainController {
    public static void mainController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到登录页面
                Print.write("即将跳转到登录页面...");
                MainView.loginView();
                break;
            case "2":
//                跳转到注册页面
                Print.write("即将跳转到注册页面...");
                MainView.registerView();
                break;
            case "3":
                try {
                    ServerThread.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                MainView.mainView();
        }
    }
    public static void adminFunctionController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到用户管理页面
                Print.write("即将跳转到用户管理页面...");
                MainView.adminUserManagementView();
                break;
            case "2":
//                跳转到部门管理页面
                Print.write("即将跳转到部门管理页面...");
                DepartmentView.departmentManagementView();
                break;
            case "3":
//                跳转到职位管理页面
                Print.write("即将跳转到职位管理页面...");
                PostView.positionManagementView();
                break;
            case "4":
//                跳转到员工管理页面
                Print.write("即将跳转到员工管理页面...");
                EmployeeView.employeeManagementView();
                break;
            case "5":
//                跳转到公告管理页面
                Print.write("即将跳转到公告管理页面...");
                AnnouncementView.adminAnnouncementManagement();
                break;
            case "6":
                try {
                    ServerThread.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                MainView.adminLoginSucceedView();
        }
    }
    public static void ordinaryUserFunctionController(String key) {
        //根据key的值 决定后续的跳转页面
        switch (key) {
            case "1":
//                跳转到查询当前用户
               UserService.ordinaryUserQuery();
                break;
            case "2":
//                跳转到公告查询页面
                Print.write("即将跳转到公告查询页面...");
                AnnouncementView.ordinaryUserAnnouncementInquiryView();
                break;
            case "3":
                try {
                    ServerThread.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                MainView.ordinaryUserLoginSucceedView();
        }
    }
    public static void adminUserManagementController(String key) {
        //根据key的值 决定后续的跳转页面
        switch (key) {
            case "1":
//                跳转到查询用户页面
                Print.write("即将跳转到查询用户页面...");
                MainView.adminQueryUserView();
                break;
            case "2":
//                跳转到删除用户页面
                Print.write("即将跳转到删除用户页面...");
                MainView.deleteUserView();
                break;
            case "3":
//                跳转到修改用户页面
                Print.write("即将跳转到修改用户页面...");
                MainView.updateUserView();
                break;
            case "4":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminLoginSucceedView();
                break;
            default:
                //打印提示 返回当前页面
                Print.write("输入范围有误,请重新输入");
                MainView.adminUserManagementView();
        }
    }
    public static void queryUserController(String key) {
        //根据key的值 决定后续的跳转页面
        switch (key) {
            case "1":
//                跳转到查询所有用户的service层
                UserService.queryUser();
                MainView.adminQueryUserView();
                break;
            case "2":
//                跳转到模糊查询页面
                Print.write("即将跳转到模糊查询页面...");
                MainView.fuzzyQueryView();
                MainView.adminQueryUserView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminUserManagementView();
                break;
            default:
                //打印提示 返回当前页面
                Print.write("输入范围有误,请重新输入");
                MainView.adminQueryUserView();
        }
    }
    public static void deleteUserController(String key) {
        switch (key){
            case "1":
                //跳转提供删除的id页面
                MainView.deleteUserByIdView();
                break;
            case "2":
                MainView.deleteUserByUsernameView();
                break;
            case "3":
                //返回上一层
                Print.write("即将返回上一步...");
                MainView.adminUserManagementView();
                break;
            default:
                //打印提示 返回当前页面
                Print.write("输入范围有误,请重新输入");
                MainView.deleteUserView();
        }
    }
    public static void updateUserController(String key){
        switch (key){
            case "1":
                //跳转到修改用户名页面
                MainView.updateUsernameUserView();
                break;
            case "2":
                //跳转到修改用户密码页面
                MainView.updatePasswordUserView();
                break;
            case "3":
                //返回上一层
                Print.write("即将返回上一步...");
                MainView.adminUserManagementView();
                break;
            default:
                //打印提示 返回当前页面
                Print.write("输入范围有误,请重新输入");
                MainView.deleteUserView();
        }
    }
}
