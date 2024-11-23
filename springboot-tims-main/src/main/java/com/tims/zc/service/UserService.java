package com.tims.zc.service;

import com.teacher.pojo.PageBean;
import com.teacher.pojo.User;

import java.util.List;
/**
 * UserService接口定义了与User实体类相关的服务操作。
 * 该接口提供了对教师信息的基本增删改查功能，以及分页查询和登录验证功能。
 */
public interface UserService {
    /**
     * 分页查询教师信息。
     * 根据提供的页码、页面大小以及其他可选筛选条件（工号、姓名、性别、学科类别、教师类型、任教专业名称），
     * 返回符合条件的教师信息列表以及分页信息。
     *
     * @param page 当前页码
     * @param pageSize 每页显示的记录数
     * @param tno 教师工号（可选筛选条件）
     * @param name 教师姓名（可选筛选条件）
     * @param sex 教师性别（可选筛选条件）
     * @param subject 学科类别（可选筛选条件）
     * @param teacherType 教师类型（可选筛选条件）
     * @param teachWork 任教专业名称（可选筛选条件）
     * @return PageBean<User> 包含教师信息列表和分页信息的对象
     */
    PageBean<User> list(int page, int pageSize, String tno , String name , Boolean sex , String subject, String teacherType, String teachWork);

    /**
     * 根据工号列表删除教师信息。
     * 该方法接受一个工号列表作为参数，删除所有对应的教师信息。
     *
     * @param tnos 教师工号列表
     */
    void delete(List<String> tnos);

    /**
     * 插入新的教师信息。
     * 该方法接受一个User对象作为参数，将其插入到数据库中。
     *
     * @param user 要插入的教师信息对象
     */
    void insert(User user);

    /**
     * 更新教师信息。
     * 该方法接受一个User对象作为参数，根据其工号更新数据库中的教师信息。
     *
     * @param user 包含更新信息的教师对象
     */
    void update(User user);

    /**
     * 根据工号获取教师信息。
     * 该方法接受一个工号作为参数，返回对应的教师信息对象。
     *
     * @param tno 教师工号
     * @return User 对应的教师信息对象
     */
    User getByTno(String tno);

    /**
     * 教师登录验证。
     * 该方法接受教师工号和密码作为参数，验证登录信息是否正确。
     *
     * @param tno 教师工号
     * @param pwd 教师密码
     * @return User 登录验证成功的教师信息对象
     */
    User login(String tno, String pwd);

}
