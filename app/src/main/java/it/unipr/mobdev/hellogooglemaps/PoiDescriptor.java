package it.unipr.mobdev.hellogooglemaps;

/**
 * Created by Marco Picone (picone.m@gmail.com) 20/03/2020
 * Point of Interest (PoI) data structure containing the basic information for a location on the map
 */
public class PoiDescriptor {

    private String name = null;
    private String description = null;
    private double lat = 0.0;
    private double lng = 0.0;

    public PoiDescriptor() {
    }

    public PoiDescriptor(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PoiDescriptor(String name, String description, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "PoiDescriptor{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoiDescriptor that = (PoiDescriptor) o;
        return Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.lng, lng) == 0;
    }

}
