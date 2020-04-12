package com.geek.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类。
 * 开启事务、提交事务、回滚事务和释放连接。
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.geek.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 开启事务。
     */
//    @Before("pt1()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务。
     */
//    @AfterReturning("pt1()")
    public void commitTransaction() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务。
     */
//    @AfterThrowing("pt1()")
    public void rollbackTransaction() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接。
     */
//    @After("pt1()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();// 还回连接池中。
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把连接和线程解绑。
     */
    public void removeConnection() {
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object rtValue = null;
        try {
            // 获取参数。
            Object[] args = proceedingJoinPoint.getArgs();
            // 开启事务。
            this.beginTransaction();
            // 执行方法。
            rtValue = proceedingJoinPoint.proceed(args);
            // 提交事务。
            this.commitTransaction();

            // 返回结果。
            return rtValue;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.rollbackTransaction();
            throw new RuntimeException(throwable);
        } finally {
            // 释放资源。
            this.release();
        }
    }
}
