package com.teacher.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * OperateLog类是一个用于记录操作日志的实体类。该类包含了有关操作的详细信息，如操作用户、操作时间、调用的方法及其参数、返回值和执行时间等。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("log")
public class OperateLog {
    @TableId(type = IdType.AUTO)
    private Integer id;                  // 日志的唯一标识符
    private Integer operateUser;         // 操作用户的标识符（如用户ID）
    private LocalDateTime operateTime;   // 操作发生的时间
    private String className;            // 操作所在的类名
    private String methodName;           // 操作调用的方法名
    private String methodParams;         // 方法的参数信息（以字符串形式存储）
    private String returnValue;          // 方法的返回值（以字符串形式存储）
    private Long costTime;               // 方法执行所花费的时间（单位：毫秒）

}
