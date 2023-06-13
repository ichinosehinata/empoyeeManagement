package com.iweb.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ichinose
 * @date 2023/6/11 16:45
 */
public class Print {
    static InputStream is;
    static OutputStream os;
    static DataInputStream dis;
    static DataOutputStream dos;

    static {
        try {
            os = ServerThread.socket.getOutputStream();
            dos = new DataOutputStream(os);
            is=ServerThread.socket.getInputStream();
            dis=new DataInputStream(is);
        }catch (Exception e){

        }
    }
    public static void write(String printMessage){
        try {
            dos.writeUTF(printMessage);
        }catch (Exception e){

        }
    }

    public static String read(){
        String str="";
        try{
            str=dis.readUTF();
        }catch (Exception e){

        }
        return str;
    }
}
