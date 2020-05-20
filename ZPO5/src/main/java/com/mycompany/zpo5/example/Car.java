/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo5.example;

import static com.mycompany.zpo5.example.CarType.*;


public class Car {
    
    private String brand;
    private String model;
    private final Enum type;
    private int engineSize;
    private int horsepower;
    public Car(){
        this.brand="unkown";
        this.model="unknown";
        this.type=HATCHBACK;
        this.engineSize=1;
        this.horsepower=1;
    }
    public Car(String brand,String model,Enum type,int engineSize,int horsepower){
        this.brand=brand;
        this.model=model;
        this.type=type;
        this.engineSize=engineSize;
        this.horsepower=horsepower;
    }
    
    private double PowerToEngineRatio(){
        double powerRatio=this.horsepower/this.engineSize;
        return powerRatio;
    }
    
    public String drive(){
        return "Car is driving, enjoy";
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
    
}
