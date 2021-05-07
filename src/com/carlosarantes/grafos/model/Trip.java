package com.carlosarantes.grafos.model;

import com.carlosarantes.grafos.helper.ImagePositionConverter;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Trip {

    private Airport airportA;
    private Airport airportB;
    private double distance;
    private double cost;
    private int flightHeight;

    public Trip(Airport airportA, Airport airportB, double cost, ImagePositionConverter imagePositionConverter) {

        this.airportA = airportA;
        this.airportB = airportB;

        int x1 = imagePositionConverter.getXFromLongitude(airportA.getCoordinates().getLongitude());
        int y1 = imagePositionConverter.getYFromLatitude(airportA.getCoordinates().getLatitude());

        int x2 = imagePositionConverter.getXFromLongitude(airportB.getCoordinates().getLongitude());
        int y2 = imagePositionConverter.getYFromLatitude(airportB.getCoordinates().getLatitude());

        this.distance = getDistance(x1,y1,x2,y2);
        this.cost = cost;
    }

    @Override
    public String toString() {
        return airportA.getId()+":"+airportB.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.distance, distance) == 0 &&
                Double.compare(trip.cost, cost) == 0 &&
                Objects.equals(airportA, trip.airportA) &&
                Objects.equals(airportB, trip.airportB) &&
                Objects.equals(airportA,trip.airportB) &&
                Objects.equals(airportB, trip.airportA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportA, airportB, distance, cost);
    }

    private static double getDistance(int x1, int y1, int x2, int y2){
        return Point2D.distance(x1, y1, x2, y2);
    }

    public Airport getAirportA() {
        return airportA;
    }

    public void setAirportA(Airport airportA) {
        this.airportA = airportA;
    }

    public Airport getAirportB() {
        return airportB;
    }

    public void setAirportB(Airport airportB) {
        this.airportB = airportB;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getFlightHeight() {
        return flightHeight;
    }

    public void setFlightHeight(int flightHeight) {
        this.flightHeight = flightHeight;
    }
}
