package com.carlosarantes.grafos;

import com.carlosarantes.grafos.config.ImageConfig;
import com.carlosarantes.grafos.graph.Graph;
import com.carlosarantes.grafos.graph.Vertex;
import com.carlosarantes.grafos.helper.GraphicsPrinter;
import com.carlosarantes.grafos.helper.InputFileReader;
import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.FlightControlMap;
import com.carlosarantes.grafos.model.Trip;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.carlosarantes.grafos.config.Variables.AIRPORT_LIST_FILE_PATH;
import static com.carlosarantes.grafos.config.Variables.TRIP_LIST_FILE_PATH;

public class Main {

    public static void main(String[] args) throws IOException {

        ImageConfig imageConfig = new ImageConfig();
        GraphicsPrinter graphicsPrinter = new GraphicsPrinter(imageConfig);

        InputFileReader inputFileReader = new InputFileReader(imageConfig);
        List<Airport> airportList = inputFileReader.readAirportsListFromInput(AIRPORT_LIST_FILE_PATH);
        List<Trip> tripList = inputFileReader.readTripsListFromInput(TRIP_LIST_FILE_PATH,airportList);

        graphicsPrinter.printVisual(airportList,tripList);

        FlightControlMap flightControlMap = new FlightControlMap(tripList,imageConfig);

        JFrame frame2 = new JFrame();
        frame2.setSize(600, 75);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button= new JButton("Calculate shortest distance");
        JButton button2= new JButton("Calculate cheapest price");
        JButton button3= new JButton("Get flights heights");


        frame2.setLayout(new FlowLayout());
        frame2.add(button);
        frame2.add(button2);
        frame2.add(button3);

        frame2.setVisible(true);
        frame2.setAlwaysOnTop(true);

        button3.addActionListener(e -> {

            flightControlMap.setFlightsHeights();

            AtomicReference<String> result = new AtomicReference<>();

            result.set("");

            flightControlMap.getTrips().forEach(
                    trip -> result.set(
                            result.get().concat(trip.toString() + "-" + trip.getFlightHeight() + "\n")
                    )
            );

            JOptionPane.showMessageDialog(null,
                    result, //mensagem
                    "Flights heights", // titulo da janela
                    JOptionPane.INFORMATION_MESSAGE);

        });

        button.addActionListener(e -> {

            final JLabel label = new JLabel();

            String result = (String)JOptionPane.showInputDialog(
                    frame2,
                    "Insert airports",
                    "Swing Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "firstAirport-secondAirport"
            );

            String [] split = result.split("-");

            String airportA = split[0], airportB = split[1];

            Graph graph = new Graph(airportList,tripList);

            graph = new Graph(airportList,tripList);

            Vertex shortestDistance = graph.getShortestPath(airportA,airportB);

            JOptionPane.showMessageDialog(null,
                    "Value of shortest distance is: " + shortestDistance.getDijkstraValue(), //mensagem
                    "Information Panel", // titulo da janela
                    JOptionPane.INFORMATION_MESSAGE);

        });

        button2.addActionListener(e -> {

            final JLabel label = new JLabel();

            String result = (String)JOptionPane.showInputDialog(
                    frame2,
                    "Insert airports",
                    "Swing Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "firstAirport-secondAirport"
            );

            String [] split = result.split("-");

            String airportA = split[0], airportB = split[1];

            Graph graph = new Graph(airportList,tripList);

            graph = new Graph(airportList,tripList);

            Vertex cheapestPrice = graph.getCheapestPath(airportA,airportB);

            JOptionPane.showMessageDialog(null,
                    "Value of cheapest price is: " + cheapestPrice.getDijkstraValue(), //mensagem
                    "Information Panel", // titulo da janela
                    JOptionPane.INFORMATION_MESSAGE);

        });


        // FLIGHT MAP

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(imageConfig.getWidth(), imageConfig.getHeight());

        JLabel lbl = new JLabel();
        ImageIcon icon = new ImageIcon(imageConfig.getBufferedImage());
        lbl.setIcon(icon);

        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
