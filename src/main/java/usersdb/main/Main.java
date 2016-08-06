package usersdb.main;

import org.eclipse.jetty.server.Server;
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

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
