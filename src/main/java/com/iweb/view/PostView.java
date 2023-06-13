package com.iweb.view;

import com.iweb.controller.DepartmentController;
import com.iweb.controller.PostController;
import com.iweb.service.DepartmentService;
import com.iweb.service.PostService;
import com.iweb.util.Print;

/**
 * @author ichinose
 * @date 2023/6/12 15:46
 */
public class PostView {
    /**
     * 管理员职位管理页面
     */
    public static void positionManagementView(){
        Print.write("1.添加职位");
        Print.write("2.查询职位");
        Print.write("3.删除职位");
        Print.write("4.修改职位");
        Print.write("5.返回上一步");
        String str=Print.read();
        PostController.postManagementController(str);
    }
    public static void addPostView(){
        Print.write("请输入需要添加的职位名称");
        String str=Print.read();
        PostService.addPost(str);
    }

    public static void postInquiryView(){
        Print.write("1.查询所有职位");
        Print.write("2.模糊查询");
        Print.write("3.返回上一步");
        String str=Print.read();
        PostController.postInquiryController(str);
    }
    public static void postFuzzyQueryView(){
        Print.write("请输入字符串进行模糊查询");
        String str= Print.read();
        PostService.postFuzzyQuery(str);
    }
    public static void deletePostView(){
        Print.write("请输入需要删除的编号:");
        String str = Print.read();
        PostService.deletePost(str);
    }
    public static void updatePostView(){
        Print.write("请输入需要修改的编号：");
        String id=Print.read();
        Print.write("请输入修改之后的职位名称：");
        String postName = Print.read();
        PostService.updatePost(id,postName);
    }
}
