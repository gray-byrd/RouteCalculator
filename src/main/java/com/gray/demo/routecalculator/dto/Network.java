package com.gray.demo.routecalculator.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.*;

@Data
public class Network {

    @SerializedName("locations")
    private ArrayList<Location> locations;

    @SerializedName("connections")
    private ArrayList<Connection> connections;

    public boolean isValid() {
        return Objects.nonNull(locations) && Objects.nonNull(connections);
    }
}
