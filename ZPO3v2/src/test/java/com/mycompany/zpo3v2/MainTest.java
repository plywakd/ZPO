/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo3v2;

import static com.mycompany.zpo3v2.Pizza.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Plywak
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findCheapestSpicy method, of class Main.
     */
    @Test
    public void testFindCheapestSpicy() {
        System.out.println("findCheapestSpicy");
        List<Pizza> pizzaList = Arrays.asList(TABASCO, SOPRANO, CARUSO);;
        Pizza expResult = CARUSO;
        Pizza result = Main.findCheapestSpicy(pizzaList);
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostExpensiveVegetarian method, of class Main.
     */
    @Test
    public void testFindMostExpensiveVegetarian() {
        System.out.println("findMostExpensiveVegetarian");
        List<Pizza> pizzaList = Arrays.asList(HAVAI, VEGETARIANA, TABASCO);
        Pizza expResult = VEGETARIANA;
        Pizza result = Main.findMostExpensiveVegetarian(pizzaList);
        assertEquals(expResult, result);
    }

    /**
     * Test of iLikeMeat method, of class Main.
     */
    @Test
    public void testILikeMeat() {
        System.out.println("iLikeMeat");
        List<Pizza> pizzaList =Arrays.asList(MARGHERITA, HAVAI,AMORE,FARMER);
        List<Pizza> expResult =Arrays.asList(FARMER,HAVAI,AMORE);
        List<Pizza> result = Main.iLikeMeat(pizzaList);
        assertEquals(expResult, result);
    }

    /**
     * Test of groupByPrice method, of class Main.
     */
    @Test
    public void testGroupByPrice() {
        System.out.println("groupByPrice");
        List<Pizza> pizzaList =Arrays.asList(AMORE, FOUR_CHEESE, VEGETARIANA, TABASCO, CAPRESE);
        Map<Integer, List<Pizza>> expResult = new HashMap<>();
			expResult.put(16, new ArrayList<>(Arrays.asList(AMORE)));
			expResult.put(19, new ArrayList<>(Arrays.asList(CAPRESE,FOUR_CHEESE)));
			expResult.put(20, new ArrayList<>(Arrays.asList(VEGETARIANA)));
                        expResult.put(22, new ArrayList<>(Arrays.asList(TABASCO)));
        Map<Integer, List<Pizza>> result = Main.groupByPrice(pizzaList);
        expResult.forEach((k,v) -> v.sort((p1, p2) -> p1.getName().compareTo(p2.getName())));
        assertEquals(expResult, result);
    }

    /**
     * Test of formatedMenu method, of class Main.
     */
    @Test
    public void testFormatedMenu() {
        System.out.println("formatedMenu");
        List<Pizza> pizzaList = Arrays.asList(Pizza.values());
        String expResult = "Marrgherita: cienkie ciasto, sos pomidorowy, ser - 13\n" +
                            "Capri: cienkie ciasto, sos pomidorowy, ser, szynka, pieczarki - 17\n" +
                            "Havai: cienkie ciasto, sos pomidorowy, ser, szynka, ananas - 17\n" +
                            "Caruso: cienkie ciasto, sos pomidorowy, kiełbasa, papryka peperoni - 15\n" +
                            "Mama Mia: cienkie ciasto, sos pomidorowy, ser, cebula, pieczarki, bekon - 18\n" +
                            "Soprano: grube ciasto, sos pomidorowy, ser, szynka, pieczarki, cebula, bekon, pieprz - 23\n" +
                            "Calabrese: grube ciasto, sos pomidorowy, ser, szynka, pieczarki, kiełbasa, cebula, oliwki - 24\n" +
                            "Vegetariana: cienkie ciasto, sos pomidorowy, ser, cebula, fasola, kukurydza, brokuły, rukola - 20\n" +
                            "Caprese: grube ciasto, sos pomidorowy, mozarella, ser feta, pomidor, bazylia - 19\n" +
                            "Pascetore: cienkie ciasto, sos pomidorowy, ser, tuńczyk, cebula - 16\n" +
                            "Cztery sery: cienkie ciasto, sos pomidorowy, ser, mozarella, ser feta, ser pleśniowy - 19\n" +
                            "Tabasco: grube ciasto, sos pomidorowy, ser, szynka, salami, papryka peperoni, kukurydza, tabasco - 22\n" +
                            "Amore: cienkie ciasto, sos pomidorowy, ser, kurczak, pomidor - 16\n" +
                            "Farmerska: grube ciasto, sos pomidorowy, ser, kurczak, bekon, cebula, kukurydza - 22";
        String result = Main.formatedMenu(pizzaList);
        assertEquals(expResult, result);

    }

    
    
}
