package com.lazarev.springcoreadvanced.bpp;

import com.lazarev.springcoreadvanced.annotation.InTransaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InTransactionAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> inTransactionBeans = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(InTransaction.class)){
            inTransactionBeans.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(inTransactionBeans.containsKey(beanName)){
            Class<?> beanClass = inTransactionBeans.get(beanName);

            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("--Start transaction--");
                    Object result = method.invoke(bean, args);
                    System.out.println("--Commit transaction--");
                    return result;
                }
            });
        }

        return bean;
    }
}
