package com.gray.demo.routecalculator;

import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
class Node {

    private int id;

    private String name;

    private ArrayList<Node> neighbors;

    private Node previousNeighbor;

    Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", neighbors=" + neighbors.stream().map(Node::getName)
                .collect(Collectors.joining(",","[","]")) +
                '}';
    }
}
