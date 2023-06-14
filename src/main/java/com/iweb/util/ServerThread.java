package com.iweb.util;

import com.iweb.pojo.Users;
import com.iweb.view.Main;
import com.iweb.view.MainView;

import java.io.IOException;
import java.net.Socket;

/**
 * @author ichinose
 * @date 2023/6/11 15:51
 */
public class ServerThread implements Runnable{
    public static Users currentUser=null;
    public static Socket socket;

    public ServerThread(Socket s) {
        socket = s;
    }
    @Override
    public void run() {
        Main.map.put(Thread.currentThread(),socket);
        System.out.println("当前线程为"+Thread.currentThread());
        System.out.println("当前连接为"+Main.map.get(Thread.currentThread()));
        MainView.mainView();
    }
}
