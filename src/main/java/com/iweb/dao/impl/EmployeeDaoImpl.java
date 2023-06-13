package com.iweb.dao.impl;

import com.iweb.dao.EmployeeDao;
import com.iweb.pojo.Department;
import com.iweb.pojo.Employee;
import com.iweb.pojo.Post;
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
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean addEmployee(Employee e) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="insert into employee(ename,eidnumber,ephone,esex,did,pid) values (?,?,?,?,?,?)";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1, e.getEName());
            ps.setString(2, e.getEidNumber());
            ps.setString(3, e.getEPhone());
            ps.setString(4, e.getESex());
            ps.setInt(5,e.getDepartment().getDid());
            ps.setInt(6,e.getPost().getPid());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public List<Employee> queryAllEmployee() {
        List<Employee> employeeList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from employee";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                employeeList.add(new Employee(rs.getInt("eid"),
                        rs.getString("ename"),
                        rs.getString("eidnumber"),
                        rs.getString("ephone"),
                        rs.getString("esex"),
                        new Department(rs.getInt("did")),
                        new Post(rs.getInt("pid"))
                        ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return employeeList;
    }

    @Override
    public List<Employee> queryEmployeeByStr(String str) {
        List<Employee> employeeList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from employee_view where ename like concat('%',?,'%')" +
                "or eidnumber like concat('%',?,'%')" +
                "or ephone like concat('%',?,'%')" +
                "or esex like concat('%',?,'%')" +
                "or dname like concat('%',?,'%')" +
                "or pname like concat('%',?,'%')";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,str);
            ps.setString(2,str);
            ps.setString(3,str);
            ps.setString(4,str);
            ps.setString(5,str);
            ps.setString(6,str);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                employeeList.add(new Employee(rs.getInt("eid"),
                        rs.getString("ename"),
                        rs.getString("eidnumber"),
                        rs.getString("ephone"),
                        rs.getString("esex"),
                        new Department(rs.getString("dname")),
                        new Post(rs.getString("pname"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return employeeList;
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="delete from employee where eid=?";
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
    public boolean updateEmployee(Employee e) {
        boolean flag=false;
        Connection c= Main.connectionPool.getConnection();
        String sql="update employee set ename=?,eidnumber=?,ephone=?,esex=?,did=?,pid=? where eid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,e.getEName());
            ps.setString(3,e.getEPhone());
            ps.setString(2,e.getEidNumber());
            ps.setString(4,e.getESex());
            ps.setInt(5,e.getDepartment().getDid());
            ps.setInt(6,e.getPost().getPid());
            ps.setInt(7,e.getEid());
            ps.execute();
            flag=ps.getUpdateCount()>=1;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return flag;
    }

    @Override
    public List<Employee> queryByDid(int did) {
        List<Employee> employeeList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from employee where did=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
                ){
            ps.setInt(1,did);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                employeeList.add(new Employee(rs.getInt("eid"),
                        rs.getString("ename"),
                        rs.getString("eidnumber"),
                        rs.getString("ephone"),
                        rs.getString("esex"),
                        new Department(rs.getInt("did")),
                        new Post(rs.getInt("pid"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return employeeList;
    }

    @Override
    public List<Employee> queryByPid(int pid) {
        List<Employee> employeeList=new ArrayList<>();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from employee where pid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setInt(1,pid);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                employeeList.add(new Employee(rs.getInt("eid"),
                        rs.getString("ename"),
                        rs.getString("eidnumber"),
                        rs.getString("ephone"),
                        rs.getString("esex"),
                        new Department(rs.getInt("did")),
                        new Post(rs.getInt("pid"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return employeeList;
    }

    @Override
    public Employee queryById(int eid) {
        Employee employee=new Employee();
        Connection c= Main.connectionPool.getConnection();
        String sql="select * from employee where eid=?";
        try(
                PreparedStatement ps=c.prepareStatement(sql)
                ){
            ps.setInt(1,eid);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                employee.setEid(eid);
                employee.setEName(rs.getString("ename"));
                employee.setEidNumber(rs.getString("eidnumber"));
                employee.setEPhone(rs.getString("ephone"));
                employee.setESex(rs.getString("esex"));
                employee.setDepartment(new Department(rs.getInt("did")));
                employee.setPost(new Post(rs.getInt("pid")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Main.connectionPool.returnConnection(c);
        }
        return employee;
    }
}
