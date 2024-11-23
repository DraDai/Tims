package com.tims.zc.mapper;

import com.teacher.pojo.EducationBackground;
import com.tims.zc.springboottimsmain.SpringbootTimsMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(classes = SpringbootTimsMainApplication.class)
public class EducationBackgroundMapperTest {
    @Autowired
    private EducationBackgroundMapper educationBackgroundMapper;

    @Test
    public void selectByPrimaryKey() {
        EducationBackground educationBackground = educationBackgroundMapper.selectById("21");
        System.out.println(educationBackground);
    }

    @Test
    public void insertEducationBackground() {
        EducationBackground educationBackground = new EducationBackground("22", "aaa", "gsee", "dsvs", LocalDate.of(2021,1,1), null);
        educationBackgroundMapper.insert(educationBackground);
    }

    @Test
    public void updateEducationBackground() {
        EducationBackground educationBackground = educationBackgroundMapper.selectById("22");
        educationBackground.setGraduationSchool("caonima");
        educationBackgroundMapper.updateById(educationBackground);
    }

    @Test
    public void deleteEducationBackground() {
        int result = educationBackgroundMapper.deleteById("22");
        System.out.println(result);
    }
}
