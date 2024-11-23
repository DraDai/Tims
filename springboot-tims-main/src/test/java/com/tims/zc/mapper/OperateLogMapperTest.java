package com.tims.zc.mapper;

import com.teacher.pojo.OperateLog;
import com.tims.zc.springboottimsmain.SpringbootTimsMainApplication;
import org.apache.ibatis.executor.BatchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SpringbootTimsMainApplication.class)
public class OperateLogMapperTest {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Test
    public void insertOperateLog() {
        OperateLog log1 = new OperateLog();
        log1.setOperateUser(1);
        log1.setOperateTime(LocalDateTime.now());
        log1.setClassName("com.teacher.service.TeacherService");
        log1.setMethodName("createTeacher");
        log1.setMethodParams("teacherName=John Doe, teacherAge=35");
        log1.setReturnValue("Teacher created successfully");
        log1.setCostTime(150L);

        OperateLog log2 = new OperateLog();
        log2.setOperateUser(2);
        log2.setOperateTime(LocalDateTime.now());
        log2.setClassName("com.teacher.service.TeacherService");
        log2.setMethodName("updateTeacher");
        log2.setMethodParams("teacherId=1, newTeacherName=Jane Doe");
        log2.setReturnValue("Teacher updated successfully");
        log2.setCostTime(200L);

        List<OperateLog> operateLogs = new ArrayList<>();
        operateLogs.add(log1);
        operateLogs.add(log2);
        List<BatchResult> batchResults = operateLogMapper.insert(operateLogs);
        System.out.println(batchResults);
    }

    @Test
    public void selectOperateLog() {
        OperateLog operateLog = operateLogMapper.selectById(1);
        System.out.println(operateLog);
    }

    @Test
    public void updateOperateLog() {
        OperateLog operateLog = operateLogMapper.selectById(3);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLogMapper.updateById(operateLog);
    }

    @Test
    public void deleteOperateLog() {
        int i = operateLogMapper.deleteById(1);
        System.out.println(i);
    }
}
