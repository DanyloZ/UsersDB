package usersDB.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import usersDB.servlets.AddUserFormServlet;
import usersDB.servlets.UsersTableServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AddUserFormServlet addUserFormServlet = new AddUserFormServlet();
        UsersTableServlet usersTableServlet = new UsersTableServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addUserFormServlet), "/");
        context.addServlet(new ServletHolder(usersTableServlet), "/table");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
