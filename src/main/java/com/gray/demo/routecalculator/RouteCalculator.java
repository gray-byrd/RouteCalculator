package com.gray.demo.routecalculator;

import com.google.gson.Gson;
import com.gray.demo.routecalculator.dto.Network;
import com.gray.demo.routecalculator.dto.Order;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RouteCalculator {
    private static final Logger LOGGER = Logger.getLogger(RouteCalculator.class.getName());

    public static void main(String[] args) {
        Network network = (Network) parseJSONFromFile(args[0], Network.class);
        Order order = (Order) parseJSONFromFile(args[1], Order.class);

        if (Objects.isNull(network) || !network.isValid()) {
            System.out.println("Network is invalid");
        } else if (Objects.isNull(order) || !order.isValid()) {
            System.out.println("Order is invalid");
        } else {
            DeliveryManager deliveryManager = new DeliveryManager(network, order);
            System.out.println(deliveryManager.findPath());
        }
    }

    private static Object parseJSONFromFile(String path, Type type) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, type);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,ex.getMessage());
        }
        return null;
    }

}
