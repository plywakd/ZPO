/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo3;

import com.mycompany.zpo3v2.Main;
import com.mycompany.zpo3v2.Pizza;
import java.util.List;
import java.util.Map;
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
    @org.junit.Test
    public void testFindCheapestSpicy() {
        System.out.println("findCheapestSpicy");
        List<Pizza> pizzaList = null;
        Pizza expResult = null;
        Pizza result = Main.findCheapestSpicy(pizzaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMostExpensiveVegetarian method, of class Main.
     */
    @org.junit.Test
    public void testFindMostExpensiveVegetarian() {
        System.out.println("findMostExpensiveVegetarian");
        List<Pizza> pizzaList = null;
        Pizza expResult = null;
        Pizza result = Main.findMostExpensiveVegetarian(pizzaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iLikeMeat method, of class Main.
     */
    @org.junit.Test
    public void testILikeMeat() {
        System.out.println("iLikeMeat");
        List<Pizza> pizzaList = null;
        List<Pizza> expResult = null;
        List<Pizza> result = Main.iLikeMeat(pizzaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of groupByPrice method, of class Main.
     */
    @org.junit.Test
    public void testGroupByPrice() {
        System.out.println("groupByPrice");
        List<Pizza> pizzaList = null;
        Map<Integer, List<Pizza>> expResult = null;
        Map<Integer, List<Pizza>> result = Main.groupByPrice(pizzaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatedMenu method, of class Main.
     */
    @org.junit.Test
    public void testFormatedMenu() {
        System.out.println("formatedMenu");
        List<Pizza> pizzaList = null;
        String expResult = "";
        String result = Main.formatedMenu(pizzaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Main.
     */
    @org.junit.Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
