package com.iweb.dao;

import com.iweb.pojo.Announcement;
import com.iweb.pojo.Department;

import java.util.List;

/**
 * @author ichinose
 * @date 2023/6/12 9:17
 */
public interface AnnouncementDao {
    /**根据传进的公告类新建公告
     * @param a 传入需要新建的公告
     * @return 传入成功返回true，失败返回false
     */
    boolean addAnnouncement(Announcement a);

    /**查询全部公告
     * @return 返回数据库中公告的信息
     */
    List<Announcement> queryAllAnnouncement();

    /**公告模糊查询
     * @param str 传入进行公告模糊查询的字段
     * @return 返回查询到结果集合
     */
    List<Announcement> queryAnnouncementByStr(String str);

    /**根据id删除公告
     * @param id 传入需要删除的公告id
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteAnnouncement(int id);

    /**根据id更新公告
     * @param a 传入需要更新的公告信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateAnnouncement(Announcement a);

    /**通过id查询公告信息
     * @param aid 传入需要查询的公告id
     * @return 返回对应的公告对象
     */
    Announcement queryById(int aid);
}
