package com.iweb.dao.impl;

import com.iweb.dao.UsersDao;
import com.iweb.pojo.Users;
import com.iweb.view.Main;

import java.sql.*;
import java.util.HashMap;

/**
 * @author ichinose
 * @date 2023/6/11 17:37
 */
public class UsersDaoImpl implements UsersDao {
    @Override
    public boolean checkUsernameAndPassword(Users users) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from users where username=? and password=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                flag=true;
                users.setUid(rs.getInt("uid"));
                if ("是".equals(rs.getString("isadmin"))){
                    users.setAdmin(true);
                }else{
                    users.setAdmin(false);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public boolean checkUsername(Users users) {
        boolean flag=false;
        Connection c=Main.connectionPool.getConnection();
        String sql="select * from users where username=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setString(1,users.getUsername());
            ResultSet rs=ps.executeQuery();
            if (!rs.next()){
                flag=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public boolean registerMessage(Users users) {
        boolean succeed=false;
        Connection c=Main.connectionPool.getConnection();
        String sql="insert into users(username,password) values(?,?)";
        try(
                PreparedStatement ps=c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1,users.getUsername());
            ps.setString(2, users.getPassword());
            ps.execute();
            succeed=ps.getUpdateCount()>=1;
            if (succeed) {
                ResultSet rs = ps.getGeneratedKeys();
                users.setUid(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return succeed;
    }

    @Override
    public HashMap<Integer, String> queryUserMessage() {
        HashMap<Integer,String> hashMap=new HashMap<>();
        Connection c=Main.connectionPool.getConnection();
        String sql="select uid,username from users";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                hashMap.put(rs.getInt("uid"),rs.getString("username"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return hashMap;
    }

    @Override
    public HashMap<Integer, String> fuzzyQueryMessage(String str) {
        HashMap<Integer,String> hashMap=new HashMap<>();
        Connection c=Main.connectionPool.getConnection();
        String sql="select uid,username from users where username like concat('%',?,'%') ";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,str);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                hashMap.put(rs.getInt("uid"),rs.getString("username"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return hashMap;
    }

    @Override
    public Users queryById(Users users) {
        Connection c=Main.connectionPool.getConnection();
        String sql="select * from users where uid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setInt(1,users.getUid());
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setAdmin(rs.getString("isadmin").equals("是")?true:false);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return users;
    }

    @Override
    public boolean deleteUserById(Users users) {
        boolean flag=false;
        Connection c=Main.connectionPool.getConnection();
        String sql="delete from users where uid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setInt(1,users.getUid());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Users queryByUsername(Users users) {
        Connection c=Main.connectionPool.getConnection();
        String sql="select * from users where username=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,users.getUsername());
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                users.setUid(rs.getInt("uid"));
                users.setAdmin(rs.getString("isadmin").equals("是")?true:false);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return users;
    }

    @Override
    public boolean deleteUserByUsername(Users users) {
        boolean flag=false;
        Connection c=Main.connectionPool.getConnection();
        String sql="delete from users where username=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,users.getUsername());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(Users users) {
        boolean flag=false;
        Connection c=Main.connectionPool.getConnection();
        String sql="update users set username=?,password=? where uid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,users.getUsername());
            ps.setString(2,users.getPassword());
            ps.setInt(3,users.getUid());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
