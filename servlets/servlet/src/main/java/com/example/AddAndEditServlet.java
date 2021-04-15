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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet ("/BogdanAdd")
public class AddAndEditServlet extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String btn = httpServletRequest.getParameter("btn");
        if(btn.equals("author")){
            String id = httpServletRequest.getParameter("id");
            if(id.equals("")){
                List<Author> authors = EntityService.getForAuthor().stream().sorted(Comparator.comparing(Author::getId))
                        .collect(Collectors.toList());
                int maxId = authors.get(authors.size() - 1).getId();

                Author author = new Author();
                author.setAuthor(httpServletRequest.getParameter("text"));
                author.setId(maxId+1);

                EntityService.persistMergeAuthor(author);
            } else{
                Author author = EntityService.getAuthorById(Integer.parseInt(id));

                author.setAuthor(httpServletRequest.getParameter("text"));
                EntityService.persistMergeAuthor(author);
            }

        } else{
            String id = httpServletRequest.getParameter("id");
            if(id.equals("")){
                List<Quotes> quotes = EntityService.getForQuotes().stream().sorted(Comparator.comparing(Quotes::getId))
                        .collect(Collectors.toList());
                int maxId = quotes.get(quotes.size() - 1).getId();

                String authorId = httpServletRequest.getParameter("authorId");
                if(authorId==null || authorId.equals("") ){
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/bogdan");
                    return;
                }
                Author author = EntityService.getAuthorById(Integer.parseInt(authorId));

                Quotes newQuote = new Quotes();
                newQuote.setQuote(httpServletRequest.getParameter("text"));
                newQuote.setAuthor(author);
                newQuote.setId(maxId+1);


                EntityService.persistMergeQuote(newQuote);
            }
            else {
                Quotes quote = EntityService.getQuoteById(Integer.parseInt(id));

                String authorId = httpServletRequest.getParameter("authorId");
                if(authorId==null || authorId.equals("") ){
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/bogdan");
                    return;
                }
                Author author = EntityService.getAuthorById(Integer.parseInt(authorId));

                quote.setQuote(httpServletRequest.getParameter("text"));
                quote.setAuthor(author);
                EntityService.persistMergeQuote(quote);
            }
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/bogdan");
    }
}
