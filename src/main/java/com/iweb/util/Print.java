package com.iweb.util;

import com.iweb.view.Main;

import java.io.*;

/**
 * @author ichinose
 * @date 2023/6/11 16:45
 */
public class Print {
    static InputStream is;
    static OutputStream os;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void write(String printMessage){
        try {
            if (Main.map.get(Thread.currentThread()).isConnected()) {
                os = Main.map.get(Thread.currentThread()).getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF(printMessage);
            }
        }catch (Exception e){

        }
    }

    public static String read(){
        String str="";
        try{
            is=Main.map.get(Thread.currentThread()).getInputStream();
            dis=new DataInputStream(is);
            str=dis.readUTF();
        }catch (Exception e){

        }
        return str;
    }
}
