package com.iweb.view;

import com.iweb.controller.MainController;
import com.iweb.pojo.Users;
import com.iweb.service.UserService;
import com.iweb.util.Print;
import com.iweb.util.ServerThread;
import com.iweb.util.StringUtil;


/**
 * @author ichinose
 * @date 2023/6/10 16:52
 */
public class MainView {
    /**
     * 主界面
     */
    public static void mainView(){
            Print.write("欢迎来到人事管理系统，请选择你要进行的业务");
            Print.write("1.登录用户");
            Print.write("2.注册用户");
            Print.write("3.退出系统");
            String str= Print.read();
        MainController.mainController(str);
    }

    /**
     * 用户登录页面
     */
    public static void loginView() {
        Print.write("请输入您的用户名");
        String username= Print.read();
        Print.write("请输入您的密码");
        String password= Print.read();
        //Users对象用来存放用户输入的用户名和密码
        Users inputUser=new Users();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        //获取随机的验证码
        String confirmCode = StringUtil.getRandomString();
        Print.write("验证码为:"+confirmCode);
        Print.write("请输入验证码进行验证");
        String inputConfirmCode = Print.read();
        //验证用户输入的验证码
        if(confirmCode.equalsIgnoreCase(inputConfirmCode)) {
//            如果发现验证码验证通过,则进行验证 在进入到后续流程
            Print.write("验证码通过,正在验证用户名和密码,请稍后...");
            boolean isLogin = UserService.login(inputUser);
            //如果登录成功,则跳转到下一级页面
            if(isLogin){
                ServerThread.currentUser=inputUser;
                //跳转到下一级页面
                if (ServerThread.currentUser.isAdmin()) {
                    adminLoginSucceedView();
                }else {
                    ordinaryUserLoginSucceedView();
                }
            }else {
                //否则 重新访问登录页面
                Print.write("用户名或者密码错误，请重新输入");
                loginView();
            }
        }else {
            //直接要求用户重新输入用户名 密码 新的验证码
            Print.write("验证码输入有误,请重新输入!");
            loginView();
        }
    }

    /**
     * 管理员的功能页面
     */
    public static void adminLoginSucceedView(){
        Print.write("尊敬的用户"+ServerThread.currentUser.getUsername()+(",欢迎您"));
        Print.write("请输入您想访问的功能");
        Print.write("1.用户管理");
        Print.write("2.部门管理");
        Print.write("3.职位管理");
        Print.write("4.员工管理");
        Print.write("5.公告管理");
        Print.write("6.退出");
        String str=Print.read();
        MainController.adminFunctionController(str);
    }

    /**
     * 普通用户的功能页面
     */
    public static void ordinaryUserLoginSucceedView(){
        Print.write("尊敬的用户"+ServerThread.currentUser.getUsername()+(",欢迎您"));
        Print.write("请输入您想访问的功能");
        Print.write("1.查询当前用户");
        Print.write("2.查询公告");
        Print.write("3.退出");
        String str=Print.read();
        MainController.ordinaryUserFunctionController(str);
    }


    /**
     * 注册页面
     */
    public static void registerView(){
        Print.write("输入您要注册的用户名称:");
        String registerUsername = Print.read();
        Print.write("输入您用注册的用户密码:");
        String registerPassword = Print.read();
        //Users对象用来存放用户输入的用户名和密码
        Users registerUser = new Users();
        registerUser.setUsername(registerUsername);
        registerUser.setPassword(registerPassword);
        //将用户传递给UserService的register方法
        boolean isRegister = UserService.register(registerUser);
        //根据用户注册的情况,决定跳转不同的页面
        if (isRegister){
            ServerThread.currentUser=registerUser;
            Print.write("注册成功，即将进入登录页面");
            MainView.loginView();
        } else {
            MainView.mainView();
        }
    }

    /**
     * 管理员用户管理页面
     */
    public static void adminUserManagementView(){
        Print.write("1.查询用户");
        Print.write("2.删除用户");
        Print.write("3.修改用户");
        Print.write("4.返回上一步");
        String str=Print.read();
        MainController.adminUserManagementController(str);
    }

    /**
     * 管理员查询用户界面
     */
    public static void adminQueryUserView(){
        Print.write("1.查询所有用户");
        Print.write("2.模糊查询");
        Print.write("3.返回上一步");
        String str=Print.read();
        MainController.queryUserController(str);
    }

    /**
     * 管理员用户管理模糊查询页面
     */
    public static void fuzzyQueryView(){
        Print.write("请输入模糊查询的字段");
        String str = Print.read();
        UserService.fuzzyQuery(str);
    }

    /**
     *管理员用户管理删除用户页面
     */
    public static void deleteUserView(){
        Print.write("1.根据id删除");
        Print.write("2.根据用户名删除");
        Print.write("3.返回上一步");
        String str=Print.read();
        MainController.deleteUserController(str);
    }

    /**
     * 根据id删除用户提供id页面
     */
    public static void deleteUserByIdView(){
        Print.write("请输入需要删除的id");
        String str=Print.read();
        UserService.deleteUserById(str);
    }
    /**
     * 根据用户名删除用户提供用户名页面
     */
    public static void deleteUserByUsernameView(){
        Print.write("请输入需要删除的用户名");
        String str=Print.read();
        UserService.deleteUserByUsername(str);
    }

    public static void updateUserView(){
        Print.write("1.修改用户名");
        Print.write("2.修改密码");
        Print.write("返回上一步");
        String str=Print.read();
        MainController.updateUserController(str);
    }

    public static void updateUsernameUserView(){
        Print.write("请输入需要更新的id");
        String id=Print.read();
        Print.write("请输入更新后的用户名");
        String username=Print.read();
        Users users=new Users();
        if (StringUtil.isInteger(id)){
            int uid=Integer.parseInt(id);
            users.setUid(uid);
            users.setUsername(username);
            UserService.updateUser(users);
        }else{
            Print.write("输入的id不是数字，请重新输入");
            MainView.adminUserManagementView();
        }
    }

    public static void updatePasswordUserView(){
        Print.write("请输入需要更新的id");
        String id=Print.read();
        Print.write("请输入更新后的密码");
        String password=Print.read();
        Users users=new Users();
        if (StringUtil.isInteger(id)){
            int uid=Integer.parseInt(id);
            users.setUid(uid);
            users.setPassword(password);
            UserService.updateUser(users);
        }else{
            Print.write("输入的id不是数字，请重新输入");
            MainView.adminUserManagementView();
        }
    }
}
