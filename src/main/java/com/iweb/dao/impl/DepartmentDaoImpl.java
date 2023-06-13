package com.iweb.dao.impl;

import com.iweb.dao.DepartmentDao;
import com.iweb.pojo.Department;
import com.iweb.view.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:15
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public boolean addDepartment(Department d) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="insert into department(dname) values (?)";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1, d.getDName());
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
    public List<Department> queryAllDepartment() {
        List<Department> departmentList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from department";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                departmentList.add(new Department(rs.getInt("did"),rs.getString("dname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return departmentList;
    }

    @Override
    public List<Department> queryDepartmentByStr(String str) {
        List<Department> departmentList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from department where dname like concat('%',?,'%')";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,str);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                departmentList.add(new Department(rs.getInt("did"),rs.getString("dname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return departmentList;
    }

    @Override
    public boolean deleteDepartment(int id) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="delete from department where did=?";
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
    public boolean updateDepartment(Department d) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="update department set dname=? where did=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,d.getDName());
            ps.setInt(2, d.getDid());
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
    public Department queryById(int id) {
        Department d=new Department();
        d.setDid(id);
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from department where did=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                d.setDName(rs.getString("dname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return d;
    }

    @Override
    public Department queryByName(Department department) {
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from department where dname=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,department.getDName());
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                department.setDid(rs.getInt("did"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return department;
    }
}
