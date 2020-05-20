/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo3v2;

import static com.mycompany.zpo3v2.Ingredients.ARUGULA;
import static com.mycompany.zpo3v2.Ingredients.BASIL;
import static com.mycompany.zpo3v2.Ingredients.BEAN;
import static com.mycompany.zpo3v2.Ingredients.BECON;
import static com.mycompany.zpo3v2.Ingredients.BLUE_CHEESE;
import static com.mycompany.zpo3v2.Ingredients.BROCCOLI;
import static com.mycompany.zpo3v2.Ingredients.CHEESE;
import static com.mycompany.zpo3v2.Ingredients.CHICKEN;
import static com.mycompany.zpo3v2.Ingredients.CORN;
import static com.mycompany.zpo3v2.Ingredients.FETA;
import static com.mycompany.zpo3v2.Ingredients.HAM;
import static com.mycompany.zpo3v2.Ingredients.MOZARELLA;
import static com.mycompany.zpo3v2.Ingredients.MUSHROOMS;
import static com.mycompany.zpo3v2.Ingredients.OLIVES;
import static com.mycompany.zpo3v2.Ingredients.ONION;
import static com.mycompany.zpo3v2.Ingredients.PEPERONI;
import static com.mycompany.zpo3v2.Ingredients.PEPPER;
import static com.mycompany.zpo3v2.Ingredients.PINEAPPLE;
import static com.mycompany.zpo3v2.Ingredients.SALAMI;
import static com.mycompany.zpo3v2.Ingredients.SUASAGE;
import static com.mycompany.zpo3v2.Ingredients.TABASCO_SUACE;
import static com.mycompany.zpo3v2.Ingredients.THICK_CRUST;
import static com.mycompany.zpo3v2.Ingredients.THIN_CRUST;
import static com.mycompany.zpo3v2.Ingredients.TOMATO;
import static com.mycompany.zpo3v2.Ingredients.TOMATO_SUACE;
import static com.mycompany.zpo3v2.Ingredients.TUNA;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Plywak
 */
public enum Pizza {
    MARGHERITA("Marrgherita", THIN_CRUST, TOMATO_SUACE, CHEESE),
    CAPRI("Capri", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,MUSHROOMS),
    HAVAI("Havai", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,PINEAPPLE),
    CARUSO("Caruso", THIN_CRUST, TOMATO_SUACE, SUASAGE,PEPERONI),
    MAMA_MIA("Mama Mia", THIN_CRUST, TOMATO_SUACE, CHEESE, ONION,MUSHROOMS, BECON),
    SOPRANO("Soprano", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,MUSHROOMS, ONION, BECON, PEPPER),
    CALABRESE("Calabrese", THICK_CRUST, TOMATO_SUACE, CHEESE,HAM, MUSHROOMS, SUASAGE, ONION, OLIVES),
    VEGETARIANA("Vegetariana", THIN_CRUST, TOMATO_SUACE, CHEESE,ONION, BEAN, CORN, BROCCOLI, ARUGULA),
    CAPRESE("Caprese", THICK_CRUST, TOMATO_SUACE, MOZARELLA,FETA, TOMATO, BASIL),
    PASCETORE("Pascetore", THIN_CRUST, TOMATO_SUACE, CHEESE,TUNA, ONION),
    FOUR_CHEESE("Cztery sery", THIN_CRUST, TOMATO_SUACE, CHEESE,MOZARELLA, FETA, BLUE_CHEESE),
    TABASCO("Tabasco", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,SALAMI, PEPERONI, CORN, TABASCO_SUACE),
    AMORE("Amore", THIN_CRUST, TOMATO_SUACE, CHEESE, CHICKEN,TOMATO),
    FARMER("Farmerska", THICK_CRUST, TOMATO_SUACE, CHEESE,CHICKEN, BECON, ONION, CORN);
    
    private final String name;
    private final List<Ingredients> ingredients;
    
    private Pizza(String name, Ingredients... ingredients) {
        this.name = name;
        this.ingredients = Arrays.asList(ingredients);
    }
    public String getName() {
        return name;
    }
    public List<Ingredients> getIngredients() {
        return ingredients;
    }
    @Override
    public String toString() {
        return name;}
}