package model;

public class City {
    private int cityId;
    private String cityName;
    private int zipCode;

    // Constructors
    public City() {
    }

    public City(String cityName, int zipCode) {
        this.cityName = cityName;
        this.zipCode = zipCode;
    }

    // Getters and Setters
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    // toString() method
    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
