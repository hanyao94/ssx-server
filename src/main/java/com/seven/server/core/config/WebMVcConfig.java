/**
 * <p>
 * 项目名：	ssx-server
 * 文件名：	WebMVcConfig.java
 * 模块说明：
 * 修改历史：
 * 2018/7/19 - seven - 创建。
 */
package com.seven.server.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seven
 */
@Configuration
public class WebMVcConfig extends WebMvcConfigurationSupport {
  /**
   * 阿里 FastJson 作JSON MessageConverter
   * <p>
   * // 输出key是包含双引号
   * SerializerFeature.QuoteFieldNames,
   * // 是否输出为null的字段,若为null 则显示该字段
   * SerializerFeature.WriteMapNullValue,
   * // 数值字段如果为null，则输出为0
   * SerializerFeature.WriteNullNumberAsZero,
   * // List字段如果为null,输出为[],而非null
   * SerializerFeature.WriteNullListAsEmpty,
   * // 字符类型字段如果为null,输出为"",而非null
   * SerializerFeature.WriteNullStringAsEmpty,
   * // Boolean字段如果为null,输出为false,而非null
   * SerializerFeature.WriteNullBooleanAsFalse,
   * // Date的日期转换器
   * SerializerFeature.WriteDateUseDateFormat,
   * // 循环引用
   * SerializerFeature.DisableCircularReferenceDetect,
   */
  @Override
  protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    FastJsonConfig config = new FastJsonConfig();
    config.setSerializerFeatures(
            // 保留空的字段
            //SerializerFeature.WriteMapNullValue,
            // String null -> ""
            // 字符类型字段如果为null,输出为"",而非null
            SerializerFeature.WriteNullStringAsEmpty,
            // Number null -> 0
            //数值字段如果为null，则输出为0
            SerializerFeature.WriteNullNumberAsZero
            // List字段如果为null,输出为[],而非null
            //SerializerFeature.WriteNullListAsEmpty
                                );
    converter.setSupportedMediaTypes(supportedMediaTypes);
    converter.setFastJsonConfig(config);
    converter.setDefaultCharset(Charset.forName("UTF-8"));
    converters.add(converter);
  }
}
