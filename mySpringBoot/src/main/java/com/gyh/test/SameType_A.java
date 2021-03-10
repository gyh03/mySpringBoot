package com.gyh.test;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * SameType_A简介
 * 若容器中存在多个相同类型的bean，如 SameType.class 有多个实现并都加载到了容器中
 * 1、@Autowied spring 注解 ，AutowiredAnnotationBeanPostProcessor#postProcessProperties 处理
 *  1/1、默认按类型注入
 *      a.若多个同类型bean中，有标有@Primary的bean ,则此bean將被优先使用。
 *      但若多个同类型bean都标注了@Primary，则报错：more than one 'primary' bean found
 *      b.若多个同类型bean中，没有使用@Primary的bean，
 *      则根据各个bean标注的@Priority值(若使用了@Priority注解)，进行排序，选取优先级最高(数值最小，如-5)的bean，
 *      若优先级一样且未最高的bean有多个，则报错：Multiple beans found with the same priority -5
 *  1/2、按beanName注入，配合@Qualifier("beanName")使用
 * 2、@Resource java 注解 ，CommonAnnotationBeanPostProcessor#postProcessProperties 处理
 *
 * @author guoyanhong5
 * @date 2021-02-24 14:32
 */
@Component
@Primary
public class SameType_A implements SameType {
}
