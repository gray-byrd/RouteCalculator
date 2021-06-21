package com.gray.demo.routecalculator;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Location {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("deliversTo")
    private ArrayList<String> deliversTo;

    @SerializedName("inventory")
    private ArrayList<Item> inventory;

    private ArrayList<Location> neighbors = new ArrayList<>();

    private Location previousNeighbor;

    public void addNeighbor(Location neighbor) {
        neighbors.add(neighbor);
    }

    public boolean checkOrderAvailability(Order order) {
        for (Item item : order.getItems()) {
            if (!checkItemAvailability(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkItemAvailability(Item orderItem) {
        for (Item item : inventory) {
            if (orderItem.equals(item) && orderItem.getQuantity() <= item.getQuantity()) {
                return true;
            }
        }
        System.out.println("Order quantity exceeds " + orderItem.getCommodity() + " inventory at " + this.getName());
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getId() == location.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliversTo=" + deliversTo +
                ", inventory=" + inventory +
                ", neighbors=" + neighbors.stream().map(Location::getName)
                    .collect(Collectors.joining(",","[","]")) +
                '}';
    }
}

