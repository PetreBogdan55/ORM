package com.example.entity_service.data_access;

import com.example.connection_package.ConnectionHelper;
import com.example.entity_service.models.Motorcycle;
import com.example.entity_service.models.UserMotorcycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMotorcycleDAO {

    public static ArrayList<UserMotorcycle> getAll() {
        ArrayList<UserMotorcycle> result = new ArrayList<>();

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"UserMotorcycles\"")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new UserMotorcycle(resultSet.getInt(1),
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
    public static List<Motorcycle> getMotorcyclesForUser(int userId){
        List<UserMotorcycle> ownedMotorcycles = UserMotorcycleDAO.getAll().stream().filter(userMotorcycle -> userMotorcycle.getUserId() == userId).collect(Collectors.toList());
        List<Motorcycle> motorcycles = new ArrayList<>();
        for(UserMotorcycle userMotorcycle: ownedMotorcycles){
           motorcycles.add(MotorcycleDAO.get(userMotorcycle.getMotorcycleId()));
        }

        return motorcycles;
    }

    public static void add(UserMotorcycle userMotorcycle) {
        if (userMotorcycle == null) {
            return;
        }

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"UserMotorcycles\"(\n" +
                     "\t\"userId\", \"motorcycleId\")\n" +
                     "\tVALUES (?, ?);")) {

            preparedStatement.setInt(1, userMotorcycle.getUserId());
            preparedStatement.setInt(2, userMotorcycle.getMotorcycleId());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId, int motorcycleId) {

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"UserMotorcycles\" WHERE \"userId\" = ? AND \"motorcycleId\" = ?")) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, motorcycleId);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
