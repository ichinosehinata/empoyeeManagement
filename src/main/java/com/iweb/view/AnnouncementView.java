package com.iweb.view;

import com.iweb.controller.AnnouncementController;
import com.iweb.controller.DepartmentController;
import com.iweb.pojo.Announcement;
import com.iweb.service.AnnouncementService;
import com.iweb.service.DepartmentService;
import com.iweb.util.Print;
import com.iweb.util.StringUtil;

/**
 * @author ichinose
 * @date 2023/6/12 15:48
 */
public class AnnouncementView {
    /**
     * 管理员公告管理页面
     */
    public static void adminAnnouncementManagement(){
        Print.write("1.添加公告");
        Print.write("2.查询公告");
        Print.write("3.删除公告");
        Print.write("4.修改公告");
        String str=Print.read();
        AnnouncementController.announcementManagementController(str);
    }

    public static void addAnnouncementView(){
        Print.write("请输入需要添加的公告名称");
        String name=Print.read();
        Print.write("请输入需要添加的公告内容");
        String content=Print.read();
        AnnouncementService.addAnnouncement(name,content);
    }

    public static void announcementInquiryView(){
        Print.write("1.查询所有公告");
        Print.write("2.模糊查询");
        Print.write("3.返回上一步");
        String str=Print.read();
        AnnouncementController.announcementInquiryController(str);
    }
    public static void announcementFuzzyQueryView(){
        Print.write("请输入字符串进行模糊查询");
        String str= Print.read();
        AnnouncementService.announcementFuzzyQuery(str);
    }
    public static void deleteAnnouncementView(){
        Print.write("请输入需要删除的编号:");
        String str = Print.read();
        AnnouncementService.deleteAnnouncement(str);
    }
    public static void updateAnnouncementView(){
        Print.write("1.修改公告名称");
        Print.write("2.修改公告内容");
        Print.write("3.返回上一步");
        String str=Print.read();
        AnnouncementController.updateAnnouncementController(str);
    }
    public static void updateAnnouncementByNameView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的公告名称：");
        String name = Print.read();
        Announcement announcement=new Announcement();
        if (StringUtil.isInteger(id)){
            int aid=Integer.parseInt(id);
            announcement.setAid(aid);
            announcement.setAName(name);
            AnnouncementService.updateAnnouncement(announcement);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            AnnouncementView.updateAnnouncementView();
        }
    }
    public static void updateAnnouncementByContentView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的公告内容：");
        String content = Print.read();
        Announcement announcement=new Announcement();
        if (StringUtil.isInteger(id)){
            int aid=Integer.parseInt(id);
            announcement.setAid(aid);
            announcement.setAContext(content);
            AnnouncementService.updateAnnouncement(announcement);
        }else {
            Print.write("输入的编号中包含不是数字的字符，请重新输入");
            AnnouncementView.updateAnnouncementView();
        }
    }

    /**
     * 普通用户公告管理页面
     */
    public static void ordinaryUserAnnouncementManagement(){
        Print.write("欢迎来到公告页面");
        Print.write("1.查询所有公告");
        Print.write("模糊查询");
        String str=Print.read();
    }
}
