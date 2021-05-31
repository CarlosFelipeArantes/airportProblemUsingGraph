package com.carlosarantes.grafos.graph;

import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.Trip;


public class Edge {

    private Vertex to;
    private Trip trip;

    public Edge(Trip trip, Airport airport) {
        this.trip = trip;
        this.to = new Vertex(airport, trip);
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }
}
