package com.teacher.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * 教师教育背景实体类,包含教师背景的基本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("education_background")
public class EducationBackground {
    @TableId
    private String tno;                // 工号
    private String major;              // 最高学历所学专业
    private String graduationSchool;   // 毕业院校
    private String highestDegree;      // 最高学位
    private LocalDate degreeAttainment;   // 取得学位时间
    private byte[] certificatePicture; // 证书照片
}
