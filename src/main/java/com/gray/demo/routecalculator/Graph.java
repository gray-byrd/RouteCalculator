package com.gray.demo.routecalculator;

import com.gray.demo.routecalculator.dto.Connection;
import com.gray.demo.routecalculator.dto.Location;
import com.gray.demo.routecalculator.dto.Network;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Graph {
    private static final Logger LOGGER = Logger.getLogger(Graph.class.getName());

    private Node startNode, endNode;

    private ArrayList<Node> nodes;

    Graph(Network network, int startNodeId, int endNodeId) {
        loadGraph(network);
        this.startNode = getNodeById(startNodeId);
        this.endNode = getNodeById(endNodeId);
    }

    String generateRouteString() {
        if (searchRoute()) {
            StringBuilder route = new StringBuilder();
            Node currentNode = endNode;
            while (!currentNode.equals(startNode)) {
                route.insert(0, currentNode.getName());
                route.insert(0, " -> ");
                currentNode = currentNode.getPreviousNeighbor();
            }
            return route.insert(0, startNode.getName()).toString();
        } else {
            return "No Path";
        }
    }

    private Node getNodeById (int id) {
        for (Node node : nodes) {
            if (node.getId() == id) return node;
        }
        LOGGER.log(Level.WARNING,"No node was found with id " + id);
        return null;
    }

    private void loadGraph(Network network) {
        nodes = new ArrayList<>();
        for (Location location : network.getLocations()) {
            nodes.add(new Node(location.getId(), location.getName()));
        }
        loadNeighbors(network);
    }

    private void loadNeighbors(Network network) {
        for (Connection connection : network.getConnections()) {
            Node from = getNodeById(connection.getFrom());
            Node to = getNodeById(connection.getTo());
            if (Objects.nonNull(from) && Objects.nonNull(to)) {
                from.addNeighbor(to);
            }
        }
    }

    private boolean searchRoute() {
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(startNode);
        visited.add(startNode.getId());

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            if (currentNode.equals(endNode)) return true;
            for (Node node : currentNode.getNeighbors()) {
                if (!visited.contains(node.getId())) {
                    queue.add(node);
                    visited.add(node.getId());
                    node.setPreviousNeighbor(currentNode);
                }
            }
        }
        return false;
    }
}
