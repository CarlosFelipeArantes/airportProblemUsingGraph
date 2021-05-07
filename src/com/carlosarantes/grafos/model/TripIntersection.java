package com.carlosarantes.grafos.model;

import java.util.Objects;

public class TripIntersection {

    private Trip tripA;
    private Trip tripB;

    public TripIntersection(Trip a, Trip b) {
        this.tripA = a;
        this.tripB = b;
    }

    @Override
    public String toString() {
        return tripA.toString()+"-"+tripB.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripIntersection that = (TripIntersection) o;
        return (Objects.equals(tripA, that.tripA) && Objects.equals(tripB, that.tripB)) || (Objects.equals(tripA, that.tripB) && Objects.equals(tripB, that.tripA));
    }

    public Trip getTripA() {
        return tripA;
    }

    public Trip getTripB() {
        return tripB;
    }
}
