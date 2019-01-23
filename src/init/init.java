package init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class init implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务器启动了");
    }
}
