package com.teacher.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * User类用于封装教师的详细信息，包含教师的个人信息、工作信息以及教学相关信息。
 * 该类通过Lombok注解简化了构造方法和Getter/Setter的生成。
 */
@Data
@NoArgsConstructor  // 自动生成无参构造函数
@AllArgsConstructor // 自动生成全参构造函数
@TableName("user")
public class User {
    @TableId
    private String tno; // 教师工号
    private String name; // 教师姓名
    private String idNum; // 教师身份证号
    private String pwd; // 教师的登录密码
    private Boolean sex; // 教师的性别，true表示男性，false表示女性
    private LocalDate birthDate; // 教师出生日期（建议使用java.time.LocalDate）
    private LocalDate appointmentTime; // 教师入校任聘时间
    private String subject; // 学科类别
    private String teacherType; // 教师的类型（如专职教师、兼职教师等）
    private String teachWork; // 任教专业名称
    private String telephone; // 教师的联系电话
}