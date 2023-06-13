package com.iweb.service;

import com.iweb.dao.impl.UsersDaoImpl;
import com.iweb.pojo.Users;
import com.iweb.util.Print;
import com.iweb.util.ServerThread;
import com.iweb.view.Main;
import com.iweb.view.MainView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ichinose
 * @date 2023/6/11 17:28
 */
public class UserService {
    private static UsersDaoImpl usersDao=new UsersDaoImpl();
    public static boolean login(Users inputUser){
        boolean flag = usersDao.checkUsernameAndPassword(inputUser);
        return flag;
    }
    public static boolean register(Users registerUser){
        boolean flag=usersDao.checkUsername(registerUser);
        if (flag){
            usersDao.registerMessage(registerUser);
            return true;
        }else {
            Print.write("用户名已存在，请重新输入");
            return false;
        }
    }
    public static void queryUser(){
        HashMap hashMap =usersDao.queryUserMessage();
        Iterator<Integer> iterator=hashMap.keySet().iterator();
        while (iterator.hasNext()){
            int key= iterator.next();
            Print.write("uid="+key+",username="+hashMap.get(key));
        }
    }
    public static void fuzzyQuery(String str){
        HashMap<Integer,String> hashMap =usersDao.fuzzyQueryMessage(str);
        Iterator<Map.Entry<Integer,String>> iterator=hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,String> entry=iterator.next();
            Print.write("uid="+entry.getKey()+",username="+entry.getValue());
        }
    }

    /**根据id删除用户
     * @param str 传入需要删除的用户id
     */
    public static void deleteUserById(String str){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (str.matches(regex)){
            int id=Integer.parseInt(str);
            Users users=new Users();
            users.setUid(id);
            usersDao.queryById(users);
            if (users.getUsername()!=null){
                if (users.isAdmin()&&!users.getUid().equals(ServerThread.currentUser.getUid())){
                    Print.write("此用户为管理员，无权限删除");
                    MainView.deleteUserView();
                }else{
                    Print.write("用户信息:"+"uid="+users.getUid()+";username="+users.getUsername());
                    Print.write("确定要删除吗?(y:删除,n:取消)");
                    String choose = Print.read();
                    if (choose.equalsIgnoreCase("y")){
                        boolean succeed = usersDao.deleteUserById(users);
                        if (succeed){
                            if (users.getUid().equals(ServerThread.currentUser.getUid())){
                                ServerThread.currentUser=null;
                                Print.write("当前用户已被删除，即将返回主界面");
                                MainView.mainView();
                            }else {
                                Print.write("删除成功,即将返回上一步");
                                MainView.deleteUserView();
                            }
                        }else {
                            Print.write("删除有误，返回上一步");
                            MainView.deleteUserView();
                        }
                    }else if(choose.equalsIgnoreCase("n")){
                        Print.write("即将返回上一步");
                        MainView.deleteUserView();
                    }else {
                        Print.write("输入信息有误，返回上一步");
                        MainView.deleteUserView();
                    }
                }
            }else{
                Print.write("没有这个用户，请重新输入");
                MainView.deleteUserView();
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
            MainView.deleteUserByIdView();
        }
    }
    public static void deleteUserByUsername(String str){
        Users users=new Users();
        users.setUsername(str);
        usersDao.queryByUsername(users);
        if (users.getUid()!=0){
            if (users.isAdmin()&&!users.getUsername().equals(ServerThread.currentUser.getUsername())){
                Print.write("此用户为管理员，无权限删除");
                MainView.deleteUserView();
            }else{
                Print.write("用户信息:"+"uid="+users.getUid()+";username="+users.getUsername());
                Print.write("确定要删除吗?(y:删除,n:取消)");
                String choose = Print.read();
                if (choose.equalsIgnoreCase("y")){
                    boolean succeed = usersDao.deleteUserById(users);
                    if (succeed){
                        if (users.getUsername().equals(ServerThread.currentUser.getUsername())){
                            ServerThread.currentUser=null;
                            Print.write("当前用户已被删除，即将返回主界面");
                            MainView.mainView();
                        }else {
                            Print.write("删除成功,即将返回上一步");
                            MainView.deleteUserView();
                        }
                    }else {
                        Print.write("删除有误，返回上一步");
                        MainView.deleteUserView();
                    }
                }else if(choose.equalsIgnoreCase("n")){
                    Print.write("即将返回上一步");
                    MainView.deleteUserView();
                }else {
                    Print.write("输入信息有误，返回上一步");
                    MainView.deleteUserView();
                }
            }
        }else{
            Print.write("没有这个用户，请重新输入");
            MainView.deleteUserView();
        }
    }

    public static void updateUser(Users users){
        if (users.getUsername()!=null){
            String username= users.getUsername();
            usersDao.queryById(users);
            users.setUsername(username);
            if (users.getPassword()!=null){
                if (!users.isAdmin()||users.getUid().equals(ServerThread.currentUser.getUid())) {
                    boolean succeed = usersDao.updateUser(users);
                    if (succeed) {
                        Print.write("更新成功");
                    } else {
                        Print.write("更新异常，即将返回上一页面");
                    }
                }else{
                    Print.write("无权修改管理员账号的用户名");
                }
            }else{
                Print.write("此用户不存在");
            }
            MainView.adminUserManagementView();
        }
        if (users.getPassword()!=null){
            String password= users.getPassword();
            usersDao.queryById(users);
            users.setPassword(password);
            if (users.getUsername()!=null){
                if (!users.isAdmin()||users.getUid().equals(ServerThread.currentUser.getUid())) {
                    boolean succeed = usersDao.updateUser(users);
                    if (succeed) {
                        Print.write("更新成功");
                    } else {
                        Print.write("更新异常，即将返回上一页面");
                    }
                }else{
                    Print.write("无权修改管理员账号的密码");
                }
            }else{
                Print.write("此用户不存在");
            }
            MainView.adminUserManagementView();
        }
    }
}
