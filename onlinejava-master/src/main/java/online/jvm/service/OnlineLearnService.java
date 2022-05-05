package online.jvm.service;

import online.jvm.servlet.ExecuteJavaServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class OnlineLearnService {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8090);
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        servletHandler.setContextPath("/");
        servletHandler.setResourceBase(".");
        servletHandler.addServlet(new ServletHolder(ExecuteJavaServlet.class), "/execute");
        servletHandler.addServlet(DefaultServlet.class, "/");
        server.setHandler(servletHandler);

        server.start();
        server.join();

    }
}
