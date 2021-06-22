package com.gray.demo.routecalculator.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class Order {

    @SerializedName("to")
    private String to;

    @SerializedName("items")
    private ArrayList<Item> items;

    public boolean isValid() {
        return StringUtils.isNotEmpty(to) && Objects.nonNull(items);
    }
}
