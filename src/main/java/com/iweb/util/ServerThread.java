package com.iweb.util;

import com.iweb.pojo.Users;
import com.iweb.view.MainView;
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
        MainView.mainView();
    }
}
