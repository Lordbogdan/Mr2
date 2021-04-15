package com.example;

import Service.EntityService;
import com.bogdan.model.Author;
import com.bogdan.model.Quotes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet ("/bogdan")
public class QuotesFromStatham  extends HttpServlet {

    EntityService es = new EntityService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listAuthor",es.getForAuthor());
        request.setAttribute("listQuotes",es.getForQuotes());

        request.getRequestDispatcher("/front.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String getParam = httpServletRequest.getParameter("id-quotes" );
        String btn = httpServletRequest.getParameter("btn");


        if(getParam == null){
            getParam = httpServletRequest.getParameter("id-author" );
             if(btn.equals("edit")){
                 Author author= EntityService.getAuthorById(Integer.parseInt(getParam));
                 httpServletRequest.setAttribute("Bogdan",author.getAuthor());
                 httpServletRequest.setAttribute("Object_id",author.getId());
             }
             else if (btn.equals("delete")){
                 EntityService.deleteQuoteByAuthorId(Integer.parseInt(getParam));
                 EntityService.deleteAuthorById(Integer.parseInt(getParam));
                 httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/bogdan");
                 return;
             }
            httpServletRequest.setAttribute("btn","author");

        }
        else{
            Quotes quotes = EntityService.getQuoteById(Integer.parseInt(getParam));
            if(btn.equals("edit")){
                httpServletRequest.setAttribute("Bogdan",quotes.getQuote());
                httpServletRequest.setAttribute("Object_id",quotes.getId());
            }
            else if (btn.equals("delete")){
                EntityService.deleteQuoteById(Integer.parseInt(getParam));
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/bogdan");
                return;
            }
            httpServletRequest.setAttribute("btn","quote");
            httpServletRequest.setAttribute("Author",quotes.getAuthor());

            List<Author> authorList = EntityService.getForAuthor();
            authorList.remove(quotes.getAuthor());
            httpServletRequest.setAttribute("authors", authorList);

        }

        httpServletRequest.getRequestDispatcher("/authorAndQuotes.jsp").forward(httpServletRequest,httpServletResponse);

    }

}
