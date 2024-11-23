package com.tims.zc.service;

import com.teacher.pojo.PageBean;
import com.teacher.pojo.User;
import com.tims.zc.springboottimsmain.SpringbootTimsMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = SpringbootTimsMainApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
        User user = new User("T002", "李四", "987654321098765432", "password456", false,
                LocalDate.of(1990, 5, 15), LocalDate.of(2010,3,1), "物理", "兼职教师", "量子物理", "12345678902");
        userService.insert(user);
    }

    @Test
    public void testUpdate(){
        PageBean<User> list = userService.list(1, 10, null, null, null, null, null, null);
        List<User> rows = list.getRows();
        for (User user : rows) {
            userService.update(user);
        }
    }
    @Test
    public void testList(){
        PageBean<User> list = userService.list(1, 2, null, null, null, null, null, null);

        System.out.println(list);
    }

    @Test
    public void testLogin(){
        User t001 = userService.login("T001", "456");
        System.out.println(t001);
        User t002 = userService.login("T002", "789");
        System.out.println(t002);
        User t003 = userService.login("T003", "789");
        System.out.println(t003);
    }
}
