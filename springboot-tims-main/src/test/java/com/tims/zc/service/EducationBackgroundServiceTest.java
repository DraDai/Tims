package com.tims.zc.service;

import com.teacher.pojo.EducationBackground;
import com.teacher.pojo.PageBean;
import com.tims.zc.springboottimsmain.SpringbootTimsMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SpringbootTimsMainApplication.class)
public class EducationBackgroundServiceTest {
    @Autowired
    private EducationBackgroundService educationBackgroundService;

    @Test
    public void testInsert(){
        EducationBackground background1 = new EducationBackground();
        background1.setTno("T01");
        background1.setMajor("计算机科学");
        background1.setGraduationSchool("清华大学");
        background1.setHighestDegree("博士");
        background1.setDegreeAttainment(LocalDate.of(2015, 6, 30));

        EducationBackground background2 = new EducationBackground();
        background2.setTno("T02");
        background2.setMajor("物理学");
        background2.setGraduationSchool("北京大学");
        background2.setHighestDegree("硕士");
        background2.setDegreeAttainment(LocalDate.of(2018, 7, 1));

        EducationBackground background3 = new EducationBackground();
        background3.setTno("T03");
        background3.setMajor("数学");
        background3.setGraduationSchool("复旦大学");
        background3.setHighestDegree("博士");
        background3.setDegreeAttainment(LocalDate.of(2020, 1, 15));

        educationBackgroundService.insert(background1);
        educationBackgroundService.insert(background2);
        educationBackgroundService.insert(background3);
    }

    @Test
    public void testUpdate(){
        EducationBackground t01 = educationBackgroundService.getByTno("T01");
        t01.setDegreeAttainment(LocalDate.now());
        educationBackgroundService.update(t01);
    }

    @Test
    public void testGetByTno(){
        EducationBackground t01 = educationBackgroundService.getByTno("T01");
        System.out.println(t01);
    }

    @Test
    public void testDelete(){
        List<String> tnoList = new ArrayList<>();
        tnoList.add("T01");
        educationBackgroundService.delete(tnoList);
    }

    @Test
    public void testList(){
        PageBean<EducationBackground> list = educationBackgroundService.list(2, 1, null, null, null, null, null);
        for (EducationBackground educationBackground : list.getRows()) {
            System.out.println(educationBackground);
        }
        System.out.println(list.getTotal());

        list = educationBackgroundService.list(1, 10, null, null, "大学", null, null);
        for (EducationBackground educationBackground : list.getRows()) {
            System.out.println(educationBackground);
        }
        System.out.println(list.getTotal());

    }
}
