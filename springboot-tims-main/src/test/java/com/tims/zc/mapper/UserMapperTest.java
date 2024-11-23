package com.tims.zc.mapper;

import com.teacher.pojo.User;
import com.tims.zc.springboottimsmain.SpringbootTimsMainApplication;
import org.apache.ibatis.executor.BatchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest(classes = SpringbootTimsMainApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        User user1 = new User("T001", "张三", "123456789012345678", "password123", true, LocalDate.of(1980, 1, 1), LocalDate.of(2024,1,1), "数学", "专职教师", "教授数学课程", "12345678901");

        User user2 = new User("T002", "李四", "987654321098765432", "password456", false, LocalDate.of(1990, 2, 2), LocalDate.of(2024,2,2), "英语", "兼职教师", "教授英语课程", "09876543210");

        User user3 = new User("T003", "王五", "555555555555555555", "password789", true, LocalDate.of(1995, 3, 3), LocalDate.of(2024,3,3), "物理", "专职教师", "教授物理课程", "11223344556");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        List<BatchResult> insert = userMapper.insert(users);
    }

    @Test
    public void selectUser() {
        User t001 = userMapper.selectById("T001");
        System.out.println(t001);
    }

    @Test
    public void updateUser() {
        User t002 = userMapper.selectById("T002");
        t002.setName("李志恒");
        int i = userMapper.updateById(t002);
        System.out.println(i);
    }

    @Test
    public void deleteUser() {
        int i = userMapper.deleteById("T002");
        System.out.println(i);
    }
}
