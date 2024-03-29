package com.carlosarantes.grafos.helper;

import com.carlosarantes.grafos.config.ImageConfig;
import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.Trip;

import java.awt.*;
import java.util.List;

public class GraphicsPrinter {

    private ImagePositionConverter imagePositionConverter;
    private Graphics graphics;

    public GraphicsPrinter(ImageConfig imageConfig){
        this.imagePositionConverter = new ImagePositionConverter(imageConfig.getHeight(),imageConfig.getWidth());
        this.graphics = imageConfig.getGraphics();
    }

    public void printVisual(List<Airport> airportList, List<Trip> tripList){

        graphics.setColor(Color.BLACK);

        airportList.forEach(airport -> {
            this.printAirport(airport);
        });

        tripList.forEach(trip ->
                this.printAirportLine(trip.getAirportA(),trip.getAirportB())
        );
    }

    private void printAirportLine(Airport a, Airport b){
        graphics.setColor(Color.RED);

        int x1 = imagePositionConverter.getXFromLongitude(a.getCoordinates().getLongitude());
        int y1 = imagePositionConverter.getYFromLatitude(a.getCoordinates().getLatitude());

        int x2 = imagePositionConverter.getXFromLongitude(b.getCoordinates().getLongitude());
        int y2 = imagePositionConverter.getYFromLatitude(b.getCoordinates().getLatitude());

        graphics.drawLine(x1,y1,x2,y2);
    }

    private void printAirport(Airport airport){
        int x = imagePositionConverter.getXFromLongitude(airport.getCoordinates().getLongitude());
        int y = imagePositionConverter.getYFromLatitude(airport.getCoordinates().getLatitude());
        graphics.fillRect(x, y, 10, 10);
        graphics.drawString(airport.getId(),x+12,y + 10);
    }

}
