package com.carlosarantes.grafos.model;

import java.util.Objects;

public class Airport {

    private Coordinates coordinates;
    private String id;

    public Airport(Coordinates coordinates, String id) {
        this.coordinates = coordinates;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return coordinates.equals(airport.coordinates) &&
                id.equals(airport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, id);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
