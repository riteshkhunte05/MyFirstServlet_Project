package com.firstServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Dinesh"),
                @WebInitParam(name = "password", value = "BridgeLabz")
        }
)


public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get request parameters for userID and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        /*
         * Purpose : Validate username using Regular Expression
         * 			 Name starts with Cap and has minimum 3 characters
         */

        String regexName = "^[A-Z]{1}[a-zA-Z]{2,}$";
       /*
		 * Purpose : Validate password using Regular Expression
		  			 Rule1 – minimum 8 Characters
		  			 Rule2 – Should have at least 1 Upper Case
 					 Rule3 – Should have at least 1 numeric number in the password
					 Rule4 – Has exactly 1 Special Character
		 */

        String regexPassword = "^(?=.*[0-9])(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        if(userID.equals(user) && userID.matches(regexName) && password.equals(pwd) && password.matches(regexPassword)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either username or password is incorrect!</font>");
            rd.include(req, resp);
        }
    }




}