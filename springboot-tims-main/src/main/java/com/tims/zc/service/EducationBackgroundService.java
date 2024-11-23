package com.tims.zc.service;

import com.teacher.pojo.EducationBackground;
import com.teacher.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;
/**
 * 提供教师教育背景信息管理的服务接口。
 * 该接口包含分页查询、批量删除、插入、更新和根据教师编号查询教育背景信息的方法。
 */
public interface EducationBackgroundService {

    /**
     * 分页查询教师教育背景信息
     * @param page 当前页码
     * @param pageSize 每页数据条数
     * @param tno 教师编号
     * @param major 最高学历所学专业
     * @param graduationSchool 毕业学校
     * @param highestDegree 最高学历
     * @param degreeAttainment 取得学位时间
     * @return 一个 PageBean 对象
     */
    PageBean<EducationBackground> list(int page, int pageSize, String tno , String major , String graduationSchool, String highestDegree, LocalDate degreeAttainment);

    /**
     * 批量删除教育背景信息
     * @param tnos 接收一个包含多个 tno 的列表
     */
    void delete(List<String> tnos);

    /**
     * 插入一条新的教育背景记录
     * @param educationBackground 接受一个 EducationBackground 对象，包含教师用户的基本信息
     */
    void insert(EducationBackground educationBackground);

    /**
     * 更新教师教育背景信息
     * @param educationBackground 接受一个 EducationBackground 对象，包含用户修改后的信息
     */
    void update(EducationBackground educationBackground);

    /**
     * 根据教师编号（tno）查询教育背景信息
     * @param tno 教师编号
     * @return 一个 EducationBackground 对象，包含该教师的教育背景信息
     */
    EducationBackground getByTno(String tno);

}
