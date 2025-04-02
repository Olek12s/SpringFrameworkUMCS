package org.example.model2;

import java.io.Serializable;

public abstract class Vehicle implements Serializable
{
    protected String brand;
    protected String model;
    protected int year;
    protected int price;
    protected boolean isRented;
    protected int ID;

    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public int getYear() {return year;}
    public int getPrice() {return price;}
    public boolean isRented() {return isRented;}
    public void setRented(boolean rented) {isRented = rented;}
    public void setID(int ID) {this.ID = ID;}

    public Vehicle(String brand, String model, int year, int price)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getCategory() {
        return null;
    }

    public int getID() {return ID;}

    public String toCSV()
    {
        return brand + "; " + model + "; " + year + "; " + price + "; " + isRented + "; " + ID;
    }

    @Override
    public String toString()
    {
        return "org.example.model.Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", isRented=" + isRented +
                ", ID=" + ID +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return ID == vehicle.ID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(ID);
    }
}
