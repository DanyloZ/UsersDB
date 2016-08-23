package usersdb.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import usersdb.servlets.AddUserServlet;
import usersdb.servlets.UsersServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AddUserServlet addUserServlet = new AddUserServlet();
        UsersServlet usersServlet = new UsersServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addUserServlet), "/adduser");
        context.addServlet(new ServletHolder(usersServlet), "/users");

        ResourceHandler resource = new ResourceHandler();
        resource.setResourceBase("src/main/resources");
        //resource.setDirectoriesListed(true);

        HandlerCollection handlerList = new HandlerCollection();
        handlerList.setHandlers(new Handler[]{resource, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);


        server.start();
    }
}
