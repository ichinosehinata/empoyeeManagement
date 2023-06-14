package com.iweb.view;

import com.iweb.util.ConnectionPool;
import com.iweb.util.ServerThread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ichinose
 * @date 2023/6/10 16:50
 */
public class Main {
    /**
     * 数据库连接池
     */
    public static ConnectionPool connectionPool;
    public static HashMap<Thread,Socket> map=new HashMap<>();
    public static void main(String[] args){
        //java自带线程连接池
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10,15,60, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        try {
        //服务器Socket对象
        ServerSocket ss=new ServerSocket(9000);
        //数据库连接池初始化
        connectionPool=new ConnectionPool();
            while (true) {
                //监听客户端的连接
                Socket s= ss.accept();
                //获取到的客户端连接socket放入线程连接池
                threadPoolExecutor.execute(new ServerThread(s));
            }
        }catch (Exception e){

        }
    }
}
