package org.example.model;

public class Motorcycle extends Vehicle
{
    private String category;

    @Override
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {this.category = category;}

    public Motorcycle(String brand, String model, int year, int price, String category)
    {
        super(brand, model, year, price);
        this.category = category;
    }

    public String toCSV()
    {
        return brand + "; " + model + "; " + year + "; " + price + "; " + isRented + "; " + ID + ": " + category;
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
                ", category=" + category +
                '}';
    }
}
