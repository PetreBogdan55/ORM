package com.example.tpd_client.controllers;

import com.example.tpd_client.data_access.MotorcycleDAO;
import com.example.tpd_client.data_access.UserMotorcycleDAO;
import com.example.tpd_client.models.Motorcycle;
import com.example.tpd_client.models.User;
import com.example.tpd_client.models.UserMotorcycle;
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

@WebServlet(name = "manageMotorcyclesServlet", value = "/manage-motorcycles")
public class ManageMotorcycleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manage-motorcycles.jsp").forward(req, resp);
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

        if (button.equals("add-motorcycle")) {
            String result;
            try {
                result = TryToAddMotorcycle(request);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(result != null) {
                System.err.println(result);
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    private String TryToAddMotorcycle(HttpServletRequest request) throws IOException, InterruptedException {
        String brand = request.getParameter("brand");
        int yearOfProduction = Integer.parseInt(request.getParameter("yearOfProduction"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());

        if (brand.isEmpty() || yearOfProduction == 0) {
            return "All fields must be filled";
        }

        List<Motorcycle> ownedMotorcycles = UserMotorcycleDAO.getMotorcyclesForUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        if (containsMotorcycle(ownedMotorcycles, brand, yearOfProduction).isPresent()) {
            return "The user already owns the specified motorcycle.";
        }

        List<Motorcycle> allMotorcycles = MotorcycleDAO.getAllMotorcycles();

        Optional<Motorcycle> motorcycle = containsMotorcycle(allMotorcycles, brand, yearOfProduction);

        int motorcycleId;
        if (!motorcycle.isPresent()) {
            Motorcycle newMotorcycle = new Motorcycle(allMotorcycles.size() + 1, brand, yearOfProduction);
            MotorcycleDAO.add(newMotorcycle);
            motorcycleId = allMotorcycles.size() + 1;
        }
        else {
            motorcycleId = motorcycle.get().getId();
        }
        UserMotorcycle newUserMotorcycle = new UserMotorcycle(userId, motorcycleId);
        UserMotorcycleDAO.add(newUserMotorcycle);

        return null;
    }

    private Optional<Motorcycle> containsMotorcycle(List<Motorcycle> motorcycles, String brand, int yearOfProduction) {
        Predicate<Motorcycle> brandPredicate = motorcycle -> motorcycle.getBrand().equals(brand);
        Predicate<Motorcycle> yearOfProductionPredicate = motorcycle -> motorcycle.getYearOfProduction() == yearOfProduction;
        Predicate<Motorcycle> combinedPredicates = brandPredicate.and(yearOfProductionPredicate);
        return motorcycles.stream().filter(combinedPredicates).collect(Collectors.toList()).stream().findFirst();
    }
}
