package com.gyh.base.spring.mvc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;

/**
 * 自定义Converter
 * 实现 GenericConverter 或 Converter<S,T> 都可以
 * 只需要将该转换器注入到spring IOC 容器中即可，如此处使用@Component或使用@Bean注入，WebMvcAutoConfiguration.addFormatters() 会自动获取所有类型的Converter；
 * 或者使用 registry.addConverter(new StringToLocalDateConverter());【WebMvcConfigurerExt.addFormatters()】进行添加
 *
 * @author guoyanhong
 * @date 2020/1/3 09:27
 */
@Component
public class StringToLocalDateConverter
        implements GenericConverter {
//        implements Converter<String, LocalDate> {
    /*@Override
    public LocalDate convert(String source) {
        System.out.println("-------------自定义 Converter-----------");
        if (source == null) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }*/

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, LocalDate.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        System.out.println("-------------自定义 Converter-----------");
        if (source == null) {
            return null;
        }
        String string = (String) source;
        Annotation annotation = targetType.getAnnotation(DateTimeFormat.class);
        String pattern = null;
        if (annotation != null) {
            pattern = ((DateTimeFormat) annotation).pattern();
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern(pattern));
        return localDate;
    }
}
