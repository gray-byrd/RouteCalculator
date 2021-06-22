package com.gray.demo.routecalculator.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

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
}

