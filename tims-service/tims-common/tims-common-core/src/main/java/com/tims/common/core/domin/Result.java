package com.tims.common.core.domin;

import com.tims.common.core.constant.CommonConstant;
import com.tims.common.core.constant.StateCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回结果
 * @param <T> 数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private int code; // 状态码
    private String msg; // 提示信息
    private T data; // 返回的数据

    /**
     * 请求成功
     * @param data 返回的数据
     * @return 结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(StateCodeConstant.SUCCESS, "", data);
    }

    /**
     * 请求成功
     * @return 结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> success() {
        return new Result<>(StateCodeConstant.SUCCESS, "", null);
    }

    /**
     * 请求失败
     * @param msg 提示信息
     * @param code 状态码
     * @return 结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> fail(String msg, int code){
        return new Result<>(code, msg, null);
    }

    /**
     * 403 权限不足
     * @param msg 提示信息
     * @return 403结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> forbiddenFail(String msg){
        return new Result<>(StateCodeConstant.FORBIDDEN, msg, null);
    }

    /**
     * 401 未授权
     * @param msg 提示信息
     * @return 401结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> unauthorizedFail(String msg){
        return new Result<>(StateCodeConstant.UNAUTHORIZED, msg, null);
    }

    /**
     * 500 服务器内部错误
     * @param msg 提示信息
     * @return 500结果
     * @param <T> 数据类型
     */
    public static <T> Result<T> internalServerErrorFail(String msg){
        return new Result<>(StateCodeConstant.INTERNAL_SERVER_ERROR, msg, null);
    }

    public static <T> Result<T> notImplementedFail(String msg){
        return new Result<>(StateCodeConstant.NOT_IMPLEMENTED, msg, null);
    }
}
