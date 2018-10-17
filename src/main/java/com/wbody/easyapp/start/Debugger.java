package com.wbody.easyapp.start;

import com.jfinal.core.JFinal;



public class Debugger {


    public static void main(String[] args) {
        // eclipse 下的启动方式
        JFinal.start("src/main/webapp", 8080, "/", 5);
    }
}
