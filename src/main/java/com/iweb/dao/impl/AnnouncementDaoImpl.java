package com.iweb.dao.impl;

import com.iweb.dao.AnnouncementDao;
import com.iweb.pojo.Announcement;
import com.iweb.view.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:17
 */
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Override
    public boolean addAnnouncement(Announcement a) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="insert into announcement(aname,acontent) values (?,?)";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1, a.getAName());
            ps.setString(2,a.getAContext());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public List<Announcement> queryAllAnnouncement() {
        List<Announcement> announcementList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from announcement";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                announcementList.add(new Announcement(rs.getInt("aid"),rs.getString("aname"),rs.getString("acontent")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return announcementList;
    }

    @Override
    public List<Announcement> queryAnnouncementByStr(String str) {
        List<Announcement> announcementList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from announcement where aname like concat('%',?,'%') or acontent like concat('%',?,'%')";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,str);
            ps.setString(2,str);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                announcementList.add(new Announcement(rs.getInt("aid"),rs.getString("aname"),rs.getString("acontent")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return announcementList;
    }

    @Override
    public synchronized boolean deleteAnnouncement(int id) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="delete from announcement where aid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public synchronized boolean updateAnnouncement(Announcement a) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="update announcement set aname=?,acontent=? where aid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,a.getAName());
            ps.setString(2,a.getAContext());
            ps.setInt(3, a.getAid());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public Announcement queryById(int aid) {
        Announcement announcement=new Announcement();
        announcement.setAid(aid);
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from announcement where aid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setInt(1,aid);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                announcement.setAName(rs.getString("aname"));
                announcement.setAContext(rs.getString("acontent"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return announcement;
    }
}
