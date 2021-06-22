package com.gray.demo.routecalculator;

import com.gray.demo.routecalculator.dto.*;
import lombok.Data;

@Data
class DeliveryManager {

    private Network network;

    private Order order;

    private Graph graph;

    private Location orderLocation, deliveryLocation;

    DeliveryManager(Network network, Order order) {
        this.network = network;
        this.order = order;
    }

    String findPath() {
        if (isOrderAvailable() && isDeliveryAvailable()) {
            graph = new Graph(network, orderLocation.getId(), deliveryLocation.getId());
            return graph.generateRouteString();
        } else {
            return "No Path";
        }
    }

    private boolean isDeliveryAvailable() {
        for (Location location : network.getLocations()) {
            if (location.getDeliversTo().contains(order.getTo())){
                this.deliveryLocation = location;
                return true;
            }
        }
        System.out.println("Deliveries are not available to " + order.getTo());
        return false;
    }

    private boolean isOrderAvailable() {
        for (Location location : network.getLocations()) {
            if (location.getInventory().containsAll(order.getItems())
                    && checkItemsQuantity(location)) {
                this.orderLocation = location;
                return true;
            }
        }
        System.out.println("No location can fulfill full order");
        return false;
    }

    private boolean checkItemsQuantity(Location location) {
        for (Item item : order.getItems()) {
            if (!checkItemQuantity(item, location)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkItemQuantity(Item orderItem, Location location) {
        for (Item item : location.getInventory()) {
            if (orderItem.equals(item)
                    && orderItem.getQuantity() <= item.getQuantity()) {
                return true;
            }
        }
        System.out.println("Order quantity exceeds " + orderItem.getCommodity() + " inventory at " + location.getName());
        return false;
    }
}
