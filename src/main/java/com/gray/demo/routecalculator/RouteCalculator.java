package com.gray.demo.routecalculator;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RouteCalculator {
    private static final Logger LOGGER = Logger.getLogger(RouteCalculator.class.getName());
    public static void main(String[] args) {
        Network network = new Network();
        Order order = new Order();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(args[0])) {
            network = gson.fromJson(reader, Network.class);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,ex.getMessage());
        }
        try (FileReader reader = new FileReader(args[1])) {
            order = gson.fromJson(reader, Order.class);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,ex.getMessage());
        }

        if (network.isValid() && order.isValid()) {
            network.loadNeighbors();
            if (network.searchOrderAvailability(order)
                    && network.searchDeliveryLocation(order)
                    && network.searchRoute()) {
                System.out.println(network.generateRouteString());
            } else {
                System.out.println("No Path");
            }
        }
    }
}
