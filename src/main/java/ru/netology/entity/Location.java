package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final Integer builing;

    public Location(String city, Country country, String street, Integer builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }
}
