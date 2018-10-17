package com.wbody.easyapp.start;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.wbody.jfinal.kit.ExplorerKit;

public class JfinalConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        Config config = ConfigFactory.load("application.json");
        Config produce=ConfigFactory.load("produce.json");
        config.resolveWith(produce);
        config.checkValid(ConfigFactory.defaultReference(), "easyapp");
        JFinal.me().getServletContext().setAttribute("config",config.getConfig("easyapp"));
    }

    @Override
    public void configRoute(Routes routes) {
       routes.add("/",IndexController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        Config config = (Config) JFinal.me().getServletContext().getAttribute("config");
        Config mysql=config.getConfig("mysql");
        DruidPlugin dp = new DruidPlugin(mysql.getString("url"), mysql.getString("username"), mysql.getString("password"), mysql.getString("driverClassName"));
        dp.set(mysql.getInt("initialSize"),mysql.getInt("minIdle"),mysql.getInt("maxActive"));
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        dp.addFilter(statFilter);

        WallFilter wall = new WallFilter();
        wall.setDbType("mysql");
        dp.addFilter(wall);

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

    }



    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        ExplorerKit.openExplorer("http://127.0.0.1:8080");



    }
}
