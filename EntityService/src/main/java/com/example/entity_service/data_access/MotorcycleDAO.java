package com.example.entity_service.data_access;

import com.example.connection_package.ConnectionHelper;
import com.example.entity_service.models.Motorcycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotorcycleDAO {
    public static ArrayList<Motorcycle> getAll() {
        ArrayList<Motorcycle> result = new ArrayList<>();

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"Motorcycles\"")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Motorcycle(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Motorcycle get(int id) {
        Motorcycle motorcycle = null;
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"Motorcycles\" WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                motorcycle = new Motorcycle(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return motorcycle;
    }

    public static void add(Motorcycle motorcycle) {
        if (motorcycle == null) {
            return;
        }

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"Motorcycles\"(\n" +
                     "\tid, brand, \"yearOfProduction\")\n" +
                     "\tVALUES (?, ?, ?);")) {

            preparedStatement.setInt(1, motorcycle.getId());
            preparedStatement.setString(2, motorcycle.getBrand());
            preparedStatement.setInt(3, motorcycle.getYearOfProduction());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int motorcycleId) {

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"Motorcycles\" WHERE id = ?")) {

            preparedStatement.setInt(1, motorcycleId);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
