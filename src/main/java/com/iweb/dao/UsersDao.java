package com.iweb.dao;

import com.iweb.pojo.Users;

import java.util.HashMap;

/**
 * @author ichinose
 * @date 2023/6/11 17:32
 */
public interface UsersDao {
    /**验证users表中是否存在用户名和密码对应的数据
     * @param users 传入用户输入的用户名和密码
     * @return 存在返回true，不存在返回false
     */
    boolean checkUsernameAndPassword(Users users);

    /**判断这个用户名在数据库中是否已经存在
     * @param users 传入用户注册填入的用户名
     * @return 存在返回false，不存在返回true
     */
    boolean checkUsername(Users users);

    /**判断插入数据是否成功
     * @param users 传入用户注册的用户名和密码
     * @return 存在返回false，不存在返回true
     */
    boolean registerMessage(Users users);

    /**查询所有用户的信息
     * @return 返回的HashMap集合中键是users表的主键，值是用户名
     */
    HashMap<Integer,String> queryUserMessage();

    /**根据字段进行模糊查询
     * @param str 传入用户填写模糊查询的字段
     * @return 返回users表中的uid和username的集合
     */
    HashMap<Integer,String> fuzzyQueryMessage(String str);

    /**根据uid查询出users对应的信息
     * @param users 传入包含需要查询的uid的users
     * @return 返回users对象，如果没有信息就是查询不到结果
     */
    Users queryById(Users users);

    /**根据用户id删除用户信息
     * @param users 传入需要删除数据库对应信息的用户对象
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteUserById(Users users);

    /**根据用户名查询出用户对应信息
     * @param users 传入包含需要查询的用户名的用户对象
     * @return 返回users对象，如果没有信息就是查询不到结果
     */
    Users queryByUsername(Users users);

    /**根据用户名删除用户信息
     * @param users 传入包含需要删除的用户名的用户对象
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteUserByUsername(Users users);

    /**更新用户信息
     * @param users 传入包含需要修改的用户信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateUser(Users users);
}
