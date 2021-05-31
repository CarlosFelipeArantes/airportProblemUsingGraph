package com.carlosarantes.grafos.graph;

import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex {

    private Airport airport;
    private double dijkstraValue = Double.MAX_VALUE;
    private List<Edge> edgeList;

    public Vertex(Airport airport, List<Trip> tripList) {
        this.airport = airport;
        this.edgeList = new ArrayList<>();
        tripList.forEach(trip -> edgeList.add(new Edge(trip,trip.getAirportB())));
    }

    public Vertex(Airport airport, Trip trip) {
        this.airport = airport;
        this.edgeList = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return airport.equals(vertex.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport);
    }

    public double getDijkstraValue() {
        return dijkstraValue;
    }

    public void setDijkstraValue(double dijkstraValue) {
        this.dijkstraValue = dijkstraValue;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }
}
