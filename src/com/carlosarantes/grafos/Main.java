package com.carlosarantes.grafos;

import com.carlosarantes.grafos.config.ImageConfig;
import com.carlosarantes.grafos.helper.GraphicsPrinter;
import com.carlosarantes.grafos.helper.InputFileReader;
import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.FlightControlMap;
import com.carlosarantes.grafos.model.Trip;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.carlosarantes.grafos.config.Variables.AIRPORT_LIST_FILE_PATH;
import static com.carlosarantes.grafos.config.Variables.TRIP_LIST_FILE_PATH;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(
                new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Mercator_projection_Square.JPG/1200px-Mercator_projection_Square.JPG")
        );

        Graphics graphics = bufferedImage.getGraphics();

        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        ImageConfig imageConfig = new ImageConfig(height,width);

        GraphicsPrinter graphicsPrinter = new GraphicsPrinter(imageConfig, graphics);

        InputFileReader inputFileReader = new InputFileReader(imageConfig);

        List<Airport> airportList = inputFileReader.readAirportsListFromInput(AIRPORT_LIST_FILE_PATH);
        List<Trip> tripList = inputFileReader.readTripsListFromInput(TRIP_LIST_FILE_PATH,airportList);

        graphics.setColor(Color.BLACK);

        airportList.forEach(airport -> {
            graphicsPrinter.printAirport(airport);
        });

        tripList.forEach(trip ->
                graphicsPrinter.printAirportLine(trip.getAirportA(),trip.getAirportB())
        );

        FlightControlMap flightControlMap = new FlightControlMap(tripList,imageConfig);

        flightControlMap.configureFlightHeights();

        ImageIcon icon = new ImageIcon(bufferedImage);

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());

        JLabel lbl = new JLabel();
        lbl.setIcon(icon);

        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
