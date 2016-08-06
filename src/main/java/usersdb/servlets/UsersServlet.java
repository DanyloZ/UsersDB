package usersdb.servlets;

import usersdb.dao.DAO;
import usersdb.entity.User;
import usersdb.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static usersdb.buffer.Buffer.getBuffer;

public class UsersServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> allUsers  = new ArrayList<>();
        allUsers.addAll(DAO.getUsers());
        allUsers.addAll(getBuffer().getNewUsers());
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", allUsers);
        response.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String saveToDBButton = request.getParameter("save");
        String removeUserButton = request.getParameter("remove");

        if (saveToDBButton != null) {
            DAO.saveUsers();
            getBuffer().refreshUserList();
            response.sendRedirect("/users");
        } else if (removeUserButton != null) {
            DAO.removeUser(request.getParameter("Id"));
            response.sendRedirect("/users");
        }
    }

}
