package com.iweb.service;

import com.iweb.dao.AnnouncementDao;
import com.iweb.dao.impl.AnnouncementDaoImpl;
import com.iweb.pojo.Announcement;
import com.iweb.pojo.Department;
import com.iweb.util.Print;
import com.iweb.view.AnnouncementView;
import com.iweb.view.DepartmentView;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:14
 */
public class AnnouncementService {
    private static AnnouncementDao announcementDao=new AnnouncementDaoImpl();
    public static void addAnnouncement(String name,String content){
        Announcement announcement=new Announcement();
        announcement.setAName(name);
        announcement.setAContext(content);
        boolean succeed = announcementDao.addAnnouncement(announcement);
        if (succeed){
            Print.write("公告添加成功");
        }else {
            Print.write("公告添加失败");
        }
        AnnouncementView.adminAnnouncementManagement();
    }

    public static void queryAllAnnouncement(){
        List<Announcement> announcementList = announcementDao.queryAllAnnouncement();
        for (Announcement a :announcementList) {
            Print.write("编号:"+a.getAid()+"\t公告名:"+a.getAName()+"\t公告内容"+a.getAContext());
        }
        AnnouncementView.adminAnnouncementManagement();
    }
    public static void announcementFuzzyQuery(String str){
        List<Announcement> announcementList = announcementDao.queryAnnouncementByStr(str);
        for (Announcement a:announcementList) {
            Print.write("编号:"+a.getAid()+"\t公告名:"+a.getAName()+"\t公告内容"+a.getAContext());
        }
        AnnouncementView.adminAnnouncementManagement();
    }

    public static void deleteAnnouncement(String str){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (str.matches(regex)) {
            int id = Integer.parseInt(str);
            boolean succeed = announcementDao.deleteAnnouncement(id);
            if (succeed){
                Print.write("删除成功");
            }else {
                Print.write("删除异常，即将返回上一页面");
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
        }
        AnnouncementView.adminAnnouncementManagement();
    }
    public static void updateAnnouncement(Announcement a){
        if (a.getAName()==null){
            Announcement original = announcementDao.queryById(a.getAid());
            a.setAName(original.getAName());
        }
        if (a.getAContext()==null){
            Announcement original = announcementDao.queryById(a.getAid());
            a.setAContext(original.getAContext());
        }
        boolean succeed = announcementDao.updateAnnouncement(a);
        if (succeed){
            Print.write("更新成功");
        }else {
            Print.write("更新异常，返回上一页面");
        }
        AnnouncementView.updateAnnouncementView();
    }
}
