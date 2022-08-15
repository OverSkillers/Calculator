package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Operands {

    private final int a;
    private final int b;
    private final String operation;

    @JsonCreator
    public Operands(@JsonProperty("a") int a,
                    @JsonProperty("b") int b,
                    @JsonProperty("operation") String operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getOperation() {
        return operation;
    }
}
