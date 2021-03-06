package com.example;

import Service.EntityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet ("/say")
public class QuotesFromStatham  extends HttpServlet {

    EntityService es =new EntityService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Entity quotes = es.entityOnEveryDay();
        String author = quotes.getAuthor();
        String quote = quotes.getQuote();
        PrintWriter out  = response.getWriter();

        out.println("<font size = '100%'>");
        out.println("Цитата на сегодняшний день: " + "\"" + quote + "\"");
        out.println("</font>");
        out.println(author );
    }
}
