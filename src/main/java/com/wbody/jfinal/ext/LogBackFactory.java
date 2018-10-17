package com.wbody.jfinal.ext;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

public class LogBackFactory implements ILogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new LogBackLog(clazz);
    }

    @Override
    public Log getLog(String name) {
        return new LogBackLog(name);
    }
}
