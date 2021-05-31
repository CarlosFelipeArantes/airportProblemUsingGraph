package com.carlosarantes.grafos.graph;

import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.Trip;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private List<Vertex> vertexList;

    public Graph(List<Airport> airportList, List<Trip> tripList) {

        this.vertexList = new ArrayList<>();

        airportList.forEach(airport->{

            List<Trip> filteredTripsForAirportA = tripList.stream()
                    .filter(trip -> trip.getAirportA().equals(airport)).collect(Collectors.toList());

            Vertex vertex = new Vertex(airport,filteredTripsForAirportA);

            vertexList.add(vertex);
        });
    }

    public Vertex getCheapestPath(String airportA, String airportB){

        Set<Vertex> visitedVertexs = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        Stack<Vertex> stack2 = new Stack<>();

        vertexList.forEach(vertex -> {

            if(vertex.getAirport().getId().equals(airportA)){

                this.calculateDijkstraCost(vertex,stack,0.0);

                visitedVertexs.add(vertex);

                do {

                    Vertex stackVertex = stack.pop();

                    vertexList.forEach(vertex1 -> {

                        if(vertex1.getAirport().equals(stackVertex.getAirport()) && !visitedVertexs.contains(vertex1)){

                            if(vertex1.getEdgeList().size()>0){
                                this.calculateDijkstraCost(vertex1,stack2,stackVertex.getDijkstraValue());
                            }

                            visitedVertexs.add(vertex1);

                            System.out.println();
                        }
                    });

                }while (!stack.isEmpty());
            }
        });

        for (Vertex vertex : stack2) {
            if(vertex.getAirport().getId().equals(airportB))
                return vertex;
        }

        return null;

    }

    public Vertex getShortestPath(String airportA, String airportB){

        Set<Vertex> visitedVertexs = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        Stack<Vertex> stack2 = new Stack<>();

        vertexList.forEach(vertex -> {

            if(vertex.getAirport().getId().equals(airportA)){

                this.calculateDijkstraDistance(vertex,stack,0.0);

                visitedVertexs.add(vertex);

                do {

                    Vertex stackVertex = stack.pop();

                    vertexList.forEach(vertex1 -> {

                        if(vertex1.getAirport().equals(stackVertex.getAirport()) && !visitedVertexs.contains(vertex1)){

                            if(vertex1.getEdgeList().size()>0){
                                this.calculateDijkstraDistance(vertex1,stack2,stackVertex.getDijkstraValue());
                            }

                            visitedVertexs.add(vertex1);

                            System.out.println();
                        }
                    });

                }while (!stack.isEmpty());
            }
        });

        for (Vertex vertex : stack2) {
            if(vertex.getAirport().getId().equals(airportB))
                return vertex;
        }

        return null;
    }

    private void calculateDijkstraCost(Vertex vertex, Stack<Vertex> stack, Double dijkstraValue){

        vertex.setDijkstraValue(dijkstraValue);

        vertex.getEdgeList().forEach(edge -> {
            if(vertex.getDijkstraValue() + edge.getTrip().getCost() <edge.getTo().getDijkstraValue()){
                edge.getTo().setDijkstraValue(vertex.getDijkstraValue() + edge.getTrip().getCost());
                stack.push(edge.getTo());
            }
        });
    }

    private void calculateDijkstraDistance(Vertex vertex, Stack<Vertex> stack, Double dijkstraValue){

        vertex.setDijkstraValue(dijkstraValue);

        vertex.getEdgeList().forEach(edge -> {
            if(vertex.getDijkstraValue() + edge.getTrip().getDistance() <edge.getTo().getDijkstraValue()){
                edge.getTo().setDijkstraValue(vertex.getDijkstraValue() + edge.getTrip().getDistance());
                stack.push(edge.getTo());
            }
        });
    }

}
