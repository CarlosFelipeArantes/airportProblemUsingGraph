package com.carlosarantes.grafos.helper;

import com.carlosarantes.grafos.config.ImageConfig;
import com.carlosarantes.grafos.model.Airport;
import com.carlosarantes.grafos.model.Coordinates;
import com.carlosarantes.grafos.model.Trip;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    private ImagePositionConverter imagePositionConverter;

    public InputFileReader(ImageConfig imageConfig) {
        this.imagePositionConverter = new ImagePositionConverter(imageConfig.getHeight(),imageConfig.getWidth());
    }

    public List<Airport> readAirportsListFromInput(String filePath) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(0);
        int airportsQty = Integer.parseInt(file.readLine());

        List<Airport> airportList = new ArrayList<>();

        for(int i = 0 ; i < airportsQty ; i++){

            String [] line = file.readLine().split(" ");
            airportList.add(
                    new Airport(
                            new Coordinates(
                                    Double.parseDouble(line[1]),
                                    Double.parseDouble(line[2])
                            ),
                            line[0])
            );

        }
        return airportList;
    }

    public List<Trip> readTripsListFromInput(String filePath, List<Airport> airports) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(0);
        int tripsQty = Integer.parseInt(file.readLine());

        List<Trip> tripList = new ArrayList<>();

        for(int i = 0 ; i < tripsQty ; i++){

            String [] line = file.readLine().split(" ");

            airports.forEach(a -> {

                if(a.getId().equals(line[0])){

                    airports.forEach(b -> {

                        if(b.getId().equals(line[1])){
                            tripList.add(
                                    new Trip(
                                            a,b,Double.parseDouble(line[2]),imagePositionConverter
                                    )
                            );
                        }
                    });
                }
            });
        }
        return tripList;
    }

}
