package com.carlosarantes.grafos.helper;

public class ImagePositionConverter {

    private int height, width;

    public ImagePositionConverter(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getYFromLatitude(Double lat){
        double latRad = lat*Math.PI/180;
        double mercatorCorrection = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
        return (int)((this.height/2)-(width*mercatorCorrection/(2*Math.PI)));
    }

    public int getXFromLongitude(Double lon){
        return (int)(lon+180)*(this.width/360)+40;
    }
}
