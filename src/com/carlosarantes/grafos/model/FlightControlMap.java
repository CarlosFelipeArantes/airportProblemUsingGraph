package com.carlosarantes.grafos.model;

import com.carlosarantes.grafos.config.ImageConfig;
import com.carlosarantes.grafos.helper.ImagePositionConverter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class FlightControlMap {

    private List<TripIntersection> intersections;
    private Map<String,Integer> airportOccurrencesMap;
    private ImagePositionConverter imagePositionConverter;
    private List<Trip> trips;

    public FlightControlMap(List<Trip> trips, ImageConfig imageConfig) {

        this.trips = trips;

        this.imagePositionConverter = new ImagePositionConverter(imageConfig.getHeight(),imageConfig.getWidth());

        intersections = new ArrayList<>();

        airportOccurrencesMap = new HashMap<>();

        this.trips.forEach(tripA ->
            trips.forEach(tripB -> {
                if(!tripA.equals(tripB)){

                    Airport a1 = tripA.getAirportA();
                    int a1x = imagePositionConverter.getXFromLongitude(a1.getCoordinates().getLongitude());
                    int a1y = imagePositionConverter.getYFromLatitude(a1.getCoordinates().getLatitude());

                    Airport a2 = tripA.getAirportB();
                    int a2x = imagePositionConverter.getXFromLongitude(a2.getCoordinates().getLongitude());
                    int a2y = imagePositionConverter.getYFromLatitude(a2.getCoordinates().getLatitude());

                    Airport a3 = tripB.getAirportA();
                    int a3x = imagePositionConverter.getXFromLongitude(a3.getCoordinates().getLongitude());
                    int a3y = imagePositionConverter.getYFromLatitude(a3.getCoordinates().getLatitude());

                    Airport a4 = tripB.getAirportB();
                    int a4x = imagePositionConverter.getXFromLongitude(a4.getCoordinates().getLongitude());
                    int a4y = imagePositionConverter.getYFromLatitude(a4.getCoordinates().getLatitude());

                    Line2D l1 = new Line2D.Float(a1x, a1y, a2x, a2y);
                    Line2D l2 = new Line2D.Float(a3x, a3y, a4x, a4y);

                    if(l1.intersectsLine(l2)){
                        if(a1x!= a2x && a1x!=a3x && a1x != a4x && a1y!=a2y && a1y != a3y && a1y != a4y && a2x != a3x && a2x != a4x && a2y != a3y && a2y != a4y){
                            TripIntersection tripIntersection = new TripIntersection(tripA,tripB);
                            if(!intersections.contains(tripIntersection))
                                intersections.add(new TripIntersection(tripA,tripB));
                        }
                    }
                }
            })
        );

        intersections.forEach(tripIntersection->{

            if(airportOccurrencesMap.containsKey(tripIntersection.getTripA().toString())){

                airportOccurrencesMap.replace(
                        tripIntersection.getTripA().toString(),
                        airportOccurrencesMap.get(tripIntersection.getTripA().toString()),
                        airportOccurrencesMap.get(tripIntersection.getTripA().toString())+1
                );

            }else{

                airportOccurrencesMap.put(tripIntersection.getTripA().toString(),1);

            }

            if(airportOccurrencesMap.containsKey(tripIntersection.getTripB().toString())){

                airportOccurrencesMap.replace(
                        tripIntersection.getTripB().toString(),
                        airportOccurrencesMap.get(tripIntersection.getTripB().toString()),
                        airportOccurrencesMap.get(tripIntersection.getTripB().toString())+1
                );

            }else{

                airportOccurrencesMap.put(tripIntersection.getTripB().toString(),1);
            }

        });
        System.out.println();
    }

    public void configureFlightHeights() {
        intersections.forEach(
                intersection->{
                    Trip A = intersection.getTripA();
                    Trip B = intersection.getTripB();
                }
        );
    }

    public static Optional<Point> getIntersectionPoint(double a1, double b1, double a2, double b2) {

        if (a1 == a2) {
            return Optional.empty();
        }

        double x = (b2 - b1) / (a1 - a2);
        double y = a1 * x + b1;

        Point point = new Point();
        point.setLocation(x, y);
        return Optional.of(point);
    }

    public List<TripIntersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<TripIntersection> intersections) {
        this.intersections = intersections;
    }



}
