package com.iweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/11 17:39
 */
public class ConnectionPool {
    private List<Connection> connectionList=new ArrayList<>();
    private int size=10;

    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/personnel_management_system?characterEncoding=utf8","root","root");
                connectionList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnectionPool() {
        init();
    }

    public synchronized Connection getConnection(){
        while (connectionList.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection c = connectionList.remove(0);
        return c;
    }

    public synchronized void returnConnection(Connection c){
        connectionList.add(c);
        this.notifyAll();
    }
}
