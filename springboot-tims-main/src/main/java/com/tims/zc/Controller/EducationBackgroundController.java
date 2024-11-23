package com.tims.zc.Controller;

import com.teacher.pojo.EducationBackground;
import com.teacher.pojo.PageBean;
import com.teacher.pojo.Result;
import com.tims.zc.service.EducationBackgroundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/education-background")
public class EducationBackgroundController {
    private final EducationBackgroundService educationBackgroundService;

    @Autowired
    public EducationBackgroundController(EducationBackgroundService educationBackgroundService) {
        this.educationBackgroundService = educationBackgroundService;
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       String tno,
                       String major,
                       String graduation_school,
                       String highest_degree,
                       LocalDate degree_attainment
                       ) {
        log.info("分页查询用户{},{} 参数 {},{},{}, {},{} ",page,size,tno,major,graduation_school,highest_degree,degree_attainment);

        PageBean<EducationBackground> list = educationBackgroundService.list(page, size, tno, major, graduation_school, highest_degree, degree_attainment);
        return Result.success(list);
    }

    @DeleteMapping("/{tnos}")
    public Result delete(@PathVariable List<String> tnos) {
        log.info("删除用户教育背景信息");
        educationBackgroundService.delete(tnos);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody EducationBackground educationBackground) {
        log.info("新增用户教育背景信息 教师号{}", educationBackground.getTno());
        educationBackgroundService.insert(educationBackground);
        return Result.success();
    }

    @PutMapping
    public Result alter(@RequestBody EducationBackground educationBackground) {
        log.info("编辑用户教育背景信息{}", educationBackground.getTno());
        educationBackgroundService.update(educationBackground);
        return Result.success();
    }

    @GetMapping("/{tno}")
    public  Result getById(@PathVariable String tno){
        log.info("回显用户还没修改的信息{}",tno);
        EducationBackground educationBackground=educationBackgroundService.getByTno(tno);
        return Result.success(educationBackground);
    }
}
