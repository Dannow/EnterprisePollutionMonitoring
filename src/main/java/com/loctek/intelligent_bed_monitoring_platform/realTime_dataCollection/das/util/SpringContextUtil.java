package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        context = applicationContext;
    }

    /**
     * 获取在*.xml文件中有注入的bean
     *
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName)
    {
        return (T)context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanClass)
    {
        return (T)context.getBean(beanClass);
    }

    /**
     * 获取到的类的实例对象
     */
    private Map<String, Object> classInstances;

    /**
     * 获取到的类的实例对象 指定spring注解的类对象
     */
    public Map<String, Object> getClassInstances()
    {
        if (this.classInstances == null)
        {
            init();
        }
        return this.classInstances;
    }

    @SuppressWarnings("static-access")
    public void init()
    {
        if (this.context == null)
        {
            return;
        }
        if (this.classInstances == null)
        {
            this.classInstances = new HashMap<String, Object>();
        }

        Map<String, Object> beansWithAnnotationMap = this.context.getBeansWithAnnotation(
                org.springframework.stereotype.Service.class);

        Class<? extends Object> clazz = null;
        for (Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet())
        {
            clazz = entry.getValue().getClass();// 获取到实例对象的class信息
            Class<? extends Object>[] interfaces = clazz.getInterfaces();
            for (Class<? extends Object> aInterface : interfaces)
            {
                // 接口实例名(只是将接口的首字母换成小写)
                String aInterfaceSimpleName = getDefaultInstanceName(aInterface);
                classInstances.put(aInterfaceSimpleName, entry.getValue());
            }
        }
    }

    /**
     * 根据这个类来获取默认的实例名（默认：将首字母换成小写）
     *
     * @param clazz
     *            类信息
     * @return 默认的实例名
     */
    public static String getDefaultInstanceName(Class<?> clazz)
    {
        if (clazz == null)
        {
            return null;
        }
        String className = clazz.getSimpleName();
        String firsrLowerChar = className.substring(0, 1).toLowerCase();
        className = firsrLowerChar + className.substring(1);
        return className;
    }
}
