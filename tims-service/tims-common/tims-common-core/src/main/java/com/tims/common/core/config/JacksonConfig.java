package com.tims.common.core.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Jackson配置类
 */
@Configuration
public class JacksonConfig {

    /**
     * 定义一个私有静态常量，用于表示日期时间的格式化模式字符串。
     *
     * <p>此字符串遵循Java日期时间格式化模式的约定，指定日期时间格式为 "年-月-日 时:分:秒"，
     * 例如 "2024-10-01 12:34:56"。它主要用于配置日期时间的格式化输出或解析输入。
     * 在整个类中，此常量用于统一日期时间的表示形式，以确保日期时间相关操作的一致性。
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 定义一个私有静态常量，用于创建一个日期时间格式化器。
     *
     * <p>此格式化器使用上面定义的{@link #DATETIME_FORMAT}模式字符串进行初始化，
     * 用于对{@link java.time.LocalDateTime}类型进行格式化和解析操作。通过使用此格式化器，
     * 可以确保在处理{@link java.time.LocalDateTime}时，按照指定的日期时间格式进行转换，
     * 与类中其他日期时间相关的操作保持一致。
     *
     * @see java.time.LocalDateTime
     * @see java.time.format.DateTimeFormatter
     */
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    /**
     * 创建一个JavaTimeModule的Spring Bean。
     *
     * <p>此方法用于配置Jackson序列化和反序列化Java 8日期时间类型（如LocalDateTime）的方式。
     * 具体操作如下：
     * <ol>
     *     <li>创建一个{@link JavaTimeModule}实例，该模块用于支持Java 8日期时间类型的序列化和反序列化。</li>
     *     <li>为{@link LocalDateTime}类型添加一个序列化器，使用预定义的{@code DATETIME_FORMATTER}进行格式化。</li>
     *     <li>为{@link LocalDateTime}类型添加一个反序列化器，同样使用{@code DATETIME_FORMATTER}进行解析。</li>
     *     <li>返回配置好的{@link JavaTimeModule}实例，该实例将作为一个Spring Bean被管理。</li>
     * </ol>
     *
     * @return 配置好的用于处理Java 8日期时间类型的{@link JavaTimeModule}实例。
     * @see JavaTimeModule
     * @see LocalDateTimeSerializer
     * @see LocalDateTimeDeserializer
     */
    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMATTER));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER));
        return timeModule;
    }

    /**
     * 创建一个Jackson2ObjectMapperBuilderCustomizer的Spring Bean。
     *
     * <p>此方法用于定制Jackson的{@link ObjectMapper}构建器的各种特性。
     * 它接收前面定义的{@link JavaTimeModule}实例，并对构建器进行如下配置：
     * <ol>
     *     <li>设置日期格式化模式（针对{@code java.util.Date}类型），使用预定义的{@code DATETIME_FORMAT}。</li>
     *     <li>设置时区为GMT+8，确保日期时间处理使用指定的时区。</li>
     *     <li>禁用{@link SerializationFeature#FAIL_ON_EMPTY_BEANS}特性，使得序列化空对象时不会抛出异常。</li>
     *     <li>启用{@link SerializationFeature#INDENT_OUTPUT}特性，使得序列化输出结果进行美化缩进，更易阅读。</li>
     *     <li>禁用{@link SerializationFeature#WRITE_DATES_AS_TIMESTAMPS}特性，禁止将日期序列化为时间戳形式（针对{@code Date}类）。</li>
     *     <li>禁用{@link DeserializationFeature#FAIL_ON_UNKNOWN_PROPERTIES}特性，使得反序列化时忽略未知字段，而不抛出异常。</li>
     *     <li>设置属性命名策略为{@link PropertyNamingStrategies.SNAKE_CASE}，即数据库中的蛇形命名（如user_name）会被映射为Java对象中的驼峰命名（如userName）。</li>
     *     <li>注册前面定义的{@link JavaTimeModule}，以便支持Java 8日期时间类型的序列化和反序列化。</li>
     * </ol>
     *
     * @param javaTimeModule 前面定义的用于处理Java 8日期时间类型的{@link JavaTimeModule}实例。
     * @return 配置好的{@link Jackson2ObjectMapperBuilderCustomizer}实例，用于定制Jackson的{@link ObjectMapper}构建器。
     * @see Jackson2ObjectMapperBuilderCustomizer
     * @see ObjectMapper
     * @see SerializationFeature
     * @see DeserializationFeature
     * @see PropertyNamingStrategies
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer(JavaTimeModule javaTimeModule) {
        return builder -> {
            // 日期格式化（用于 java.util.Date）
            builder.simpleDateFormat(DATETIME_FORMAT);

            // 时区设置（系统时区）
            builder.timeZone(TimeZone.getTimeZone("GMT+8"));

            // 忽略空对象
            builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            // 美化输出
            builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);

            // 禁止将日期序列化为时间戳（用于 Date 类）
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // 反序列化时忽略未知字段
            builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            // 命名策略：SNAKE_CASE → user_name => userName
            builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            // 注册支持 Java8 时间的模块
            builder.modules(javaTimeModule);
        };
    }
}
