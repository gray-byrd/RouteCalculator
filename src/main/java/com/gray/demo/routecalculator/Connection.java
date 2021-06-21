package com.gray.demo.routecalculator;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Connection {

    @SerializedName("from")
    private int from;

    @SerializedName("to")
    private int to;
}
