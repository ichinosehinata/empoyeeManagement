package com.iweb.service;

import com.iweb.dao.EmployeeDao;
import com.iweb.dao.PostDao;
import com.iweb.dao.impl.EmployeeDaoImpl;
import com.iweb.dao.impl.PostDaoImpl;
import com.iweb.pojo.Employee;
import com.iweb.pojo.Post;
import com.iweb.util.Print;
import com.iweb.view.PostView;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:12
 */
public class PostService {
    private static PostDao postDao=new PostDaoImpl();
    private static EmployeeDao employeeDao=new EmployeeDaoImpl();
    public static void addPost(String postName){
        Post post=new Post();
        post.setPName(postName);
        boolean succeed = postDao.addPost(post);
        if (succeed){
            Print.write("职位添加成功");
        }else {
            Print.write("职位添加失败");
        }
        PostView.positionManagementView();
    }
    public static void queryAllPost(){
        List<Post> postList = postDao.queryAllPost();
        for (Post p :postList) {
            Print.write("编号:"+p.getPid()+";职位名:"+p.getPName());
        }
        PostView.positionManagementView();
    }
    public static void postFuzzyQuery(String str){
        List<Post> postList = postDao.queryPostByStr(str);
        for (Post p:postList) {
            Print.write("编号:"+p.getPid()+";职位名"+p.getPName());
        }
        PostView.positionManagementView();
    }

    public static void deletePost(String str){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (str.matches(regex)) {
            int id = Integer.parseInt(str);
            if (id==1){
                Print.write("此职位设定不可删除，请重新输入");
                PostView.positionManagementView();
            }else{
                Post p=postDao.queryById(id);
                if (p.getPName()!=null){
                    List<Employee> employeeList=employeeDao.queryByPid(id);
                    Print.write("该职位下有这些员工：");
                    for (Employee e:employeeList) {
                        Print.write("员工编号:"+e.getEid()+"\t员工姓名:"+e.getEName()+"\t身份编号:"+e.getEidNumber()+
                                "\t手机号:"+e.getEPhone()+"\t性别:"+e.getESex());
                        e.getPost().setPid(1);
                    }
                    Print.write("确定要删除吗？(y是,n否)");
                    String choose=Print.read();
                    if (choose.equalsIgnoreCase("y")){
                        if (employeeList.size()!=0) {
                            for (Employee e : employeeList) {
                                employeeDao.updateEmployee(e);
                            }
                        }
                        boolean succeed = postDao.deletePost(id);
                        if (succeed){
                            Print.write("删除成功");
                        }else {
                            Print.write("删除异常，即将返回上一界面");
                        }
                        PostView.positionManagementView();
                    }else if(choose.equalsIgnoreCase("n")){
                        Print.write("已取消，即将返回上一步...");
                        PostView.positionManagementView();
                    }else{
                        Print.write("输入有误，即将返回上一界面...");
                        PostView.positionManagementView();
                    }
                }else{
                    Print.write("输入的编号不存在职位，请重新输入");
                    PostView.positionManagementView();
                }
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
            PostView.positionManagementView();
        }
    }

    public static void updatePost(String id,String postName){
        //正则表达式判断数字
        String regex="[0-9]+";
        if (id.matches(regex)) {
            int pid = Integer.parseInt(id);
            Post p=new Post(pid,postName);
            boolean succeed=postDao.updatePost(p);
            if (succeed){
                Print.write("更新成功");
            }else {
                Print.write("更新异常，即将返回上一界面");
            }
        }else{
            Print.write("输入的信息中包含不是数字的字符，请重新输入");
        }
        PostView.positionManagementView();
    }
}
