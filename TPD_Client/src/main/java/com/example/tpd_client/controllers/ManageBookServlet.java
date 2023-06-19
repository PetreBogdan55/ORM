package com.example.tpd_client.controllers;

import com.example.tpd_client.data_access.BookDAO;
import com.example.tpd_client.data_access.UserBookDAO;
import com.example.tpd_client.models.Book;
import com.example.tpd_client.models.UserBook;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet(name = "manageBooksServlet", value = "/manage-Books")
public class ManageBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manage-Books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String button = request.getParameter("button");
        if (button == null) {
            return;
        }
        if(button.equals("home")){
            response.sendRedirect(request.getContextPath() + "/home");
        }

        if (button.equals("add-Book")) {
            String result;
            try {
                result = TryToAddBook(request);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(result != null) {
                System.err.println(result);
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    private String TryToAddBook(HttpServletRequest request) throws IOException, InterruptedException {
        int year = Integer.parseInt(request.getParameter("year"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());

        if (title.isEmpty() || author.isEmpty() || year == 0) {
            return "All fields must be filled";
        }

        List<Book> ownedBooks = UserBookDAO.getBooksForUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        if (containsBook(ownedBooks, title, author, year).isPresent()) {
            return "The user already owns the specified Book.";
        }

        List<Book> allBooks = BookDAO.getAllBooks();

        Optional<Book> Book = containsBook(allBooks, title, author, year);

        int BookId;
        if (!Book.isPresent()) {
            Book newBook = new Book(allBooks.size() + 1, title, author, year);
            BookDAO.add(newBook);
            BookId = allBooks.size() + 1;
        }
        else {
            BookId = Book.get().getId();
        }
        UserBook newUserBook = new UserBook(userId, BookId);
        UserBookDAO.add(newUserBook);

        return null;
    }

    private Optional<Book> containsBook(List<Book> Books, String title, String author, int year) {
        Predicate<Book> titlePredicate = Book -> Book.getTitle().equals(title);
        Predicate<Book> authorPredicate = Book -> Book.getAuthor().equals(author);
        Predicate<Book> yearPredicate = Book -> Book.getYear() == year;
        Predicate<Book> combinedPredicates = titlePredicate.and(authorPredicate).and(yearPredicate);
        return Books.stream().filter(combinedPredicates).collect(Collectors.toList()).stream().findFirst();
    }
}
