package com.gray.demo.routecalculator;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.*;

@Data
public class Network {

    @SerializedName("locations")
    private ArrayList<Location> locations;

    @SerializedName("connections")
    private ArrayList<Connection> connections;

    private Location orderLocation, deliveryLocation;

    public boolean isValid() {
        return Objects.nonNull(locations) && Objects.nonNull(connections);
    }

    public Location getLocationById(int id) {
        for (Location location : locations) {
            if (location.getId() == id) return location;
        }
        return null;
    }

    public void loadNeighbors() {
        for (Connection connection : connections) {
            getLocationById(connection.getFrom()).addNeighbor(getLocationById(connection.getTo()));
        }
    }

    public boolean searchOrderAvailability(Order order) {
        for (Location location : locations) {
            if (location.getInventory().containsAll(order.getItems())) {
                if (location.checkOrderAvailability(order)) {
                    orderLocation = location;
                    return true;
                }
            }
        }
        System.out.println("No location can fulfill full order");
        return false;
    }

    public boolean searchDeliveryLocation(Order order) {
        for (Location location : locations) {
            if (location.getDeliversTo().contains(order.getTo())){
                deliveryLocation = location;
                return true;
            }
        }
        return false;
    }

    public boolean searchRoute() {
        Queue<Location> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(orderLocation);
        visited.add(orderLocation.getId());

        while (!queue.isEmpty()) {
            Location currentLocation = queue.remove();
            if (currentLocation.equals(deliveryLocation)) return true;
            for (Location location : currentLocation.getNeighbors()) {
                if (!visited.contains(location.getId())) {
                    queue.add(location);
                    visited.add(location.getId());
                    location.setPreviousNeighbor(currentLocation);
                }
            }
        }
        return false;
    }

    public String generateRouteString() {
        StringBuilder route = new StringBuilder();
        Location currentLocation = deliveryLocation;
        while (!currentLocation.equals(orderLocation)) {
            route.insert(0,currentLocation.getName());
            route.insert(0," -> ");
            currentLocation = currentLocation.getPreviousNeighbor();
        }
        return route.insert(0,orderLocation.getName()).toString();
    }
}
