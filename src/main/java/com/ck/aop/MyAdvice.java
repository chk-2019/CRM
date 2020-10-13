package com.ck.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAdvice {
    @After("execution(* *..ContextLoaderListener.*(..))")
    public void contextAfterReturning(){
        System.out.println("上下文域创建了");
    }
}
