package com.tims.common.core.constant;

/**
 * 状态码常量
 */
public class StateCodeConstant {
    public static final int SUCCESS = 200;

    public static final int FORBIDDEN = 403; // 权限不足
    public static final int UNAUTHORIZED = 401; // 未授权
    public static final int INTERNAL_SERVER_ERROR = 500; // 服务器错误
    public static final int NOT_IMPLEMENTED = 501; // 未实现
}
