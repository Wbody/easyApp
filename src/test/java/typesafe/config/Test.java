package typesafe.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Test {

    //合并两个配置
    public static void main(String[] args) {
        Config config = ConfigFactory.load("produce.json");
        config.withFallback(ConfigFactory.load("application.json"));
        config.checkValid(ConfigFactory.defaultReference(), "easyapp");
        Config mysql = config.getConfig("easyapp").getConfig("mysql");
        System.out.println(mysql.getString("username"));

    }

}
