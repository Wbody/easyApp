package com.wbody.easyapp.start;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.template.Engine;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.wbody.jfinal.ext.LogBackFactory;
import com.wbody.jfinal.kit.ExplorerKit;

public class JfinalConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {

        Config config = ConfigFactory.load("application.json");
        Config produce = ConfigFactory.load("produce.json");
        config = produce.withFallback(config);
        config.checkValid(ConfigFactory.defaultReference(), "easyapp");
        JFinal.me().getServletContext().setAttribute("config", config.getConfig("easyapp"));

        constants.setLogFactory(new LogBackFactory());
        constants.setReportAfterInvocation(true);
        constants.setDevMode(true);

        LogKit.info("配置初始化");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        Config config = (Config) JFinal.me().getServletContext().getAttribute("config");
        Config mysql = config.getConfig("mysql");
        DruidPlugin dp = new DruidPlugin(mysql.getString("url"), mysql.getString("username"), mysql.getString("password"), mysql.getString("driverClassName"));
        dp.set(mysql.getInt("initialSize"), mysql.getInt("minIdle"), mysql.getInt("maxActive"));
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        dp.addFilter(statFilter);
        plugins.add(dp);


        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setShowSql(true);
        plugins.add(arp);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
        handlers.add(dvh);
    }


    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        ExplorerKit.openExplorer("http://127.0.0.1:8080");


    }
}
