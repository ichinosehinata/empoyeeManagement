package com.iweb.dao.impl;

import com.iweb.dao.PostDao;
import com.iweb.pojo.Department;
import com.iweb.pojo.Post;
import com.iweb.view.Main;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:16
 */
public class PostDaoImpl implements PostDao {
    @Override
    public boolean addPost(Post p) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="insert into post(pname) values (?)";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1, p.getPName());
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
    public List<Post> queryAllPost() {
        List<Post> postList =new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from post";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                postList.add(new Post(rs.getInt("pid"),rs.getString("pname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return postList;
    }

    @Override
    public List<Post> queryPostByStr(String str) {
        List<Post> postList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from post where pname like concat('%',?,'%')";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,str);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                postList.add(new Post(rs.getInt("pid"),rs.getString("pname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return postList;
    }

    @Override
    public synchronized boolean deletePost(int id) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="delete from post where pid=?";
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
    public synchronized boolean updatePost(Post p) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="update post set pname=? where pid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1, p.getPName());
            ps.setInt(2,p.getPid());
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
    public Post queryById(int id) {
        Post p=new Post();
        p.setPid(id);
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from post where pid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                p.setPName(rs.getString("pname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return p;
    }

    @Override
    public Post queryByName(Post post) {
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from post where pname=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,post.getPName());
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                post.setPid(rs.getInt("pid"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return post;
    }
}
