package com.example.tpd_server.data_access;

//import com.example.connection_package.ConnectionHelper;
import com.example.tpd_server.models.Book;
import com.example.tpd_server.models.UserBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBooksDAO {

    public static ArrayList<UserBooks> getAll() {
        ArrayList<UserBooks> result = new ArrayList<>();

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"UserBooks\"")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new UserBooks(resultSet.getInt(1),
                        resultSet.getInt(2)));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Book> getBooksForUser(int userId){
        List<UserBooks> ownedBooks = UserBooksDAO.getAll().stream().filter(userMotorcycle -> userMotorcycle.getUserId() == userId).collect(Collectors.toList());
        List<Book> motorcycles = new ArrayList<>();
        for(UserBooks userBook: ownedBooks){
           motorcycles.add(BookDAO.get(userBook.getBookId()));
        }

        return motorcycles;
    }

    public static void add(UserBooks userBooks) {
        if (userBooks == null) {
            return;
        }

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"UserBooks\"(\n" +
                     "\t\"userId\", \"bookId\")\n" +
                     "\tVALUES (?, ?);")) {

            preparedStatement.setInt(1, userBooks.getUserId());
            preparedStatement.setInt(2, userBooks.getBookId());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId, int bookId) {

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"userBooks\" WHERE \"userId\" = ? AND \"motorcycleId\" = ?")) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
