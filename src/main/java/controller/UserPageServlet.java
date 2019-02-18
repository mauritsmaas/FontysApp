package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile", urlPatterns = "/profile")
public class UserPageServlet  extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        System.out.print("----------------------------- "+ "LETS GO"+ " =====================");
//        req.setAttribute("testdata", username);
//
//        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

}
