package usersdb.servlets;

import usersdb.buffer.Buffer;
import usersdb.entity.User;
import usersdb.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class AddUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println(PageGenerator.instance().getPage("adduser.html", new HashMap<String, Object>()));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String id = "n/a";
        String name = request.getParameter("Name");
        String[] dateStrings = (request.getParameter("DateOfBirth")).split("\\.");
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dateStrings[2]), Integer.parseInt(dateStrings[1]), Integer.parseInt(dateStrings[0]));
        User user = new User(id, name, dateOfBirth);
        Buffer.getBuffer().getNewUsers().add(user);
        response.sendRedirect("/users");
    }
}
