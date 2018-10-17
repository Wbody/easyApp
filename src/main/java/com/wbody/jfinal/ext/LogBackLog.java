package com.wbody.jfinal.ext;

import com.jfinal.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackLog extends Log {
    private static Logger log;
    private String clazzName;

    LogBackLog(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz.getName());
        clazzName = clazz.getName();
    }

    LogBackLog(String name) {
        log = LoggerFactory.getLogger(name);
        clazzName = name;
    }

    public static LogBackLog getLog(Class<?> clazz) {
        return new LogBackLog(clazz);
    }

    public static LogBackLog getLog(String name) {
        return new LogBackLog(name);
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void debug(String message, Throwable t) {
        log.debug(message, t);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void info(String message, Throwable t) {
        log.info(message, t);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }

    @Override
    public void warn(String message, Throwable t) {
        log.warn(message, t);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void error(String message, Throwable t) {
        log.error(message, t);
    }

    @Override
    public void fatal(String message) {
        log.warn(message);
    }

    @Override
    public void fatal(String message, Throwable t) {
        log.warn(message, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return log.isWarnEnabled();
    }
}
