package com.iweb.dao;

import com.iweb.pojo.Department;
import com.iweb.pojo.Post;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:16
 */
public interface PostDao {
    /**根据传进的职位名新建职位
     * @param p 传入需要新建的职位
     * @return 传入成功返回true，失败返回false
     */
    boolean addPost(Post p);

    /**查询全部职位
     * @return 返回数据库中职位的信息
     */
    List<Post> queryAllPost();

    /**职位模糊查询
     * @param str 传入进行职位模糊查询的字段
     * @return 返回查询到结果集合
     */
    List<Post> queryPostByStr(String str);

    /**根据id删除职位，但是这个职位不能是1.未定义职位
     * @param id 传入需要删除的职位id
     * @return 删除成功返回true，失败返回false
     */
    boolean deletePost(int id);

    /**根据id更新职位名
     * @param p 传入需要更新的职位信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updatePost(Post p);

    /**根据id查询职位信息
     * @param id 传入职位id
     * @return 返回包含职位名的post对象
     */
    Post queryById(int id);

    /**根据职位名查询出id
     * @param post 传入需要查询的职位名
     * @return 返回查询到的职位信息
     */
    Post queryByName(Post post);
}
