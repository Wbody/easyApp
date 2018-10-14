package com.wbody.easyapp.start;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
    public void index(){
        renderText("服务已启动");
    }
}
