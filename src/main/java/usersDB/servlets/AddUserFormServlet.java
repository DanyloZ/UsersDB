package usersDB.servlets;

import usersDB.main.User;
import usersDB.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static usersDB.dbdataproccessing.DBDataProcessing.getUsersFromDB;

public class AddUserFormServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println(PageGenerator.instance().getPage("index.html"));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ArrayList<User> usersFromDB = getUsersFromDB();

        int id = Integer.parseInt(request.getParameter("Id"));
        String name = request.getParameter("Name");
        String[] dateStrings = (request.getParameter("DateOfBirth")).split("\\.");
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dateStrings[2]), Integer.parseInt(dateStrings[1]), Integer.parseInt(dateStrings[0]));
        User user = new User(id, name, dateOfBirth);
        PageGenerator.getPageVariables().put("usersFromDB", usersFromDB);
        ((ArrayList<User>)PageGenerator.getPageVariables().get("users")).add(user);
        response.sendRedirect("/table");
    }
}
