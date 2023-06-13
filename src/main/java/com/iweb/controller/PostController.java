package com.iweb.controller;

import com.iweb.service.DepartmentService;
import com.iweb.service.PostService;
import com.iweb.util.Print;
import com.iweb.view.DepartmentView;
import com.iweb.view.MainView;
import com.iweb.view.PostView;

/**
 * @author ichinose
 * @date 2023/6/12 9:18
 */
public class PostController {
    public static void postManagementController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到添加职位页面
                Print.write("即将跳转到添加职位页面...");
                PostView.addPostView();
                break;
            case "2":
//                跳转到查询职位页面
                Print.write("即将跳转到查询职位页面...");
                PostView.postInquiryView();
                break;
            case "3":
//                跳转到删除职位页面
                Print.write("即将跳转到删除职位页面...");
                PostView.deletePostView();
                break;
            case "4":
//                跳转到修改职位页面
                Print.write("即将跳转到修改职位页面...");
                PostView.updatePostView();
                break;
            case "5":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminLoginSucceedView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                PostView.positionManagementView();
        }
    }

    public static void postInquiryController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                查询全部职位的信息
                PostService.queryAllPost();
                break;
            case "2":
//                接收用户输入的模糊查询字段
                PostView.postFuzzyQueryView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                PostView.positionManagementView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                PostView.positionManagementView();
        }
    }
}
