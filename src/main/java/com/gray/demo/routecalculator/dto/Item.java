package com.gray.demo.routecalculator.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class Item {

    @SerializedName("commodity")
    private String commodity;

    @SerializedName("quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getCommodity().equals(item.getCommodity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommodity());
    }
}
