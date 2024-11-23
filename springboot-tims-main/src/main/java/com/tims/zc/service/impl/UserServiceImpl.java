package com.tims.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teacher.pojo.PageBean;
import com.teacher.pojo.User;
import com.teacher.utils.JbcryptUtil;
import com.tims.zc.mapper.UserMapper;
import com.tims.zc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageBean<User> list(int page, int pageSize, String tno, String name, Boolean sex, String subject, String teacherType, String teachWork) {
        // 创建分页对象，用于存储分页信息，包括当前页码和每页显示的记录数
        Page<User> userPage = new Page<>(page, pageSize);

        // 创建LambdaQueryWrapper对象，用于构建数据库查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 如果传入的教师工号tno不为空，则添加LIKE查询条件
        if(tno != null && !tno.isEmpty()){
            queryWrapper.like(User::getTno, tno);
        }

        // 如果传入的教师姓名name不为空，则添加LIKE查询条件
        if(name != null && !name.isEmpty()){
            queryWrapper.like(User::getName, name);
        }

        // 如果传入的性别sex不为空，则添加等于查询条件
        if (sex != null){
            queryWrapper.eq(User::getSex, sex);
        }

        // 如果传入的学科类别subject不为空，则添加LIKE查询条件
        if (subject != null && !subject.isEmpty()){
            queryWrapper.like(User::getSubject, subject);
        }

        // 如果传入的教师类型teacherType不为空，则添加LIKE查询条件
        if (teacherType != null && !teacherType.isEmpty()){
            queryWrapper.like(User::getTeacherType, teacherType);
        }

        // 如果传入的任教专业名称teachWork不为空，则添加LIKE查询条件
        if (teachWork != null && !teachWork.isEmpty()){
            queryWrapper.like(User::getTeachWork, teachWork);
        }

        // 使用MyBatis-Plus的selectPage方法执行分页查询，传入分页对象和查询条件包装器
        userPage = userMapper.selectPage(userPage, queryWrapper);

        // 创建PageBean对象，封装查询结果和分页信息，然后返回
        return new PageBean<>(userPage.getTotal(), userPage.getRecords());
    }

    @Override
    public void delete(List<String> tnos) {
        userMapper.deleteByIds(tnos);
    }

    @Override
    public void insert(User user) {
        //将明文密码加密
        user.setPwd(JbcryptUtil.hashPassword(user.getPwd()));
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        //将明文密码加密
        user.setPwd(JbcryptUtil.hashPassword(user.getPwd()));
        userMapper.updateById(user);
    }

    @Override
    public User getByTno(String tno) {
        return userMapper.selectById(tno);
    }

    @Override
    public User login(String tno, String pwd) {
        //根据工号获取user对象
        User user = userMapper.selectById(tno);
        //账号不存在返回空
        if (user == null){
            return null;
        }
        //验证pwd是否和数据库中的hash密码匹配
        if(JbcryptUtil.checkPassword(pwd,user.getPwd())){
            return user;
        }
        return null;
    }
}
