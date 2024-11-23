package com.tims.zc.Controller;

import com.teacher.pojo.PageBean;
import com.teacher.pojo.Result;
import com.teacher.pojo.User;
import com.tims.zc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam (defaultValue = "10") int page_size,
                       String tno , String name , Boolean sex ,
                       String subject, String teacher_type, String teach_work
    ) {
        log.info("分页查询用户{},{} 参数 {},{},{}, {},{},{} ",page,page_size,tno,name,sex,subject,teacher_type,teach_work);
        PageBean<User> pageBean= userService.list(page,page_size,tno,name,sex,subject,teacher_type,teach_work);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{tnos}")
    public Result delete(@PathVariable List<String> tnos) {
        log.info("删除用户");
        userService.delete(tnos);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody User user) {
        log.info("新增用户 教师号{}", user.getTno());
        userService.insert(user);
        return Result.success();
    }

    @PutMapping
    public Result alter(@RequestBody User user) {
        log.info("编辑用户{}", user.getTno());
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/{tno}")
    public  Result getById(@PathVariable String tno){
        log.info("回显用户还没修改的信息{}",tno);
        User user=userService.getByTno(tno);
        return Result.success(user);
    }
}
