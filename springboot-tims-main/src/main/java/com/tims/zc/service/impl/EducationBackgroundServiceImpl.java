package com.tims.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teacher.pojo.EducationBackground;
import com.teacher.pojo.PageBean;
import com.tims.zc.mapper.EducationBackgroundMapper;
import com.tims.zc.service.EducationBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EducationBackgroundServiceImpl implements EducationBackgroundService {
    private EducationBackgroundMapper educationBackgroundMapper;
    @Autowired
    public void setEducationBackgroundMapper(EducationBackgroundMapper educationBackgroundMapper) {
        this.educationBackgroundMapper = educationBackgroundMapper;
    }

    @Override
    public PageBean<EducationBackground> list(int page, int pageSize, String tno, String major, String graduationSchool, String highestDegree, LocalDate degreeAttainment) {
        //创建用于分页功能的Page类
        Page<EducationBackground> educationBackgroundPage = new Page<>(page, pageSize);

        //创建查询条件构造器
        LambdaQueryWrapper<EducationBackground> educationBackgroundLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //动态添加查询信息
        if(tno !=null && !tno.isEmpty()){
            educationBackgroundLambdaQueryWrapper.like(EducationBackground::getTno, tno);
        }
        if(major!=null && !major.isEmpty()){
            educationBackgroundLambdaQueryWrapper.like(EducationBackground::getMajor, major);
        }
        if(graduationSchool!=null && !graduationSchool.isEmpty()){
            educationBackgroundLambdaQueryWrapper.like(EducationBackground::getGraduationSchool, graduationSchool);
        }
        if(highestDegree!=null && !highestDegree.isEmpty()){
            educationBackgroundLambdaQueryWrapper.like(EducationBackground::getHighestDegree, highestDegree);
        }
        if(degreeAttainment!=null){
            educationBackgroundLambdaQueryWrapper.eq(EducationBackground::getDegreeAttainment, degreeAttainment);
        }

        //分页查询
        Page<EducationBackground> selectPage = educationBackgroundMapper.selectPage(educationBackgroundPage, educationBackgroundLambdaQueryWrapper);

        //封装返回结果
        PageBean<EducationBackground> pageBean = new PageBean<>();
        pageBean.setTotal(selectPage.getTotal());
        pageBean.setRows(selectPage.getRecords());
        return pageBean;
    }

    @Override
    public void delete(List<String> tnos) {
        educationBackgroundMapper.deleteByIds(tnos);
    }

    @Override
    public void insert(EducationBackground educationBackground) {
        educationBackgroundMapper.insert(educationBackground);
    }

    @Override
    public void update(EducationBackground educationBackground) {
        educationBackgroundMapper.updateById(educationBackground);
    }

    @Override
    public EducationBackground getByTno(String tno) {
        return educationBackgroundMapper.selectById(tno);
    }
}
