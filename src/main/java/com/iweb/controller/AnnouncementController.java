package com.iweb.controller;

import com.iweb.pojo.Announcement;
import com.iweb.service.AnnouncementService;
import com.iweb.service.DepartmentService;
import com.iweb.util.Print;
import com.iweb.view.AnnouncementView;
import com.iweb.view.DepartmentView;
import com.iweb.view.MainView;

/**
 * @author ichinose
 * @date 2023/6/12 9:18
 */
public class AnnouncementController {
    public static void announcementManagementController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到添加公告页面
                Print.write("即将跳转到添加公告页面...");
                AnnouncementView.addAnnouncementView();
                break;
            case "2":
//                跳转到查询公告页面
                Print.write("即将跳转到查询公告页面...");
                AnnouncementView.announcementInquiryView();
                break;
            case "3":
//                跳转到删除公告页面
                Print.write("即将跳转到删除公告页面...");
                AnnouncementView.deleteAnnouncementView();
                break;
            case "4":
//                跳转到修改公告页面
                Print.write("即将跳转到修改公告页面...");
                AnnouncementView.updateAnnouncementView();
                break;
            case "5":
//                返回上一步
                Print.write("即将返回上一步...");
                MainView.adminLoginSucceedView();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                AnnouncementView.adminAnnouncementManagement();
        }
    }

    public static void announcementInquiryController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                查询全部公告的信息
                AnnouncementService.queryAllAnnouncement();
                break;
            case "2":
//                接收用户输入的模糊查询字段
                AnnouncementView.announcementFuzzyQueryView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                AnnouncementView.adminAnnouncementManagement();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                AnnouncementView.announcementInquiryView();
        }
    }

    public static void updateAnnouncementController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                修改公告名称页面
                AnnouncementView.updateAnnouncementByNameView();
                break;
            case "2":
//                修改公告内容页面
                AnnouncementView.updateAnnouncementByContentView();
                break;
            case "3":
//                返回上一步
                Print.write("即将返回上一步...");
                AnnouncementView.adminAnnouncementManagement();
                break;
            default:
                //打印提示 重新回到主页面
                Print.write("输入范围有误,请重新输入");
                AnnouncementView.updateAnnouncementView();
        }
    }
}
