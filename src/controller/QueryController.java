package controller;

import java.io.IOException;

/**
 * Created by sliu on 27/3/15.
 */
public class QueryController extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String searchQuery = request.getParameter("text");

        String url = "/result?text=" + searchQuery;
        response.sendRedirect(url);
    }
}
