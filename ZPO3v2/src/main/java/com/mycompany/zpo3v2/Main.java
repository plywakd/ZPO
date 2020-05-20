/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo3v2;

import com.mycompany.zpo3v2.Pizza;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Plywak
 */
public class Main {

    public static Pizza findCheapestSpicy(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .anyMatch(i->i.isSpicy()))
                .min((p1,p2)->p1.getIngredients().stream().mapToInt(i->i.getPrice()).sum()-
                              p2.getIngredients().stream().mapToInt(i->i.getPrice()).sum())
                .orElse(null);
    }
    public static Pizza findMostExpensiveVegetarian(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .noneMatch(i->i.isMeat()))
                .max((p1,p2)->p1.getIngredients().stream().mapToInt(i->i.getPrice()).sum()-
                        p2.getIngredients().stream().mapToInt(i->i.getPrice()).sum())
                .orElse(null);
    }
    //sort by amount of meat ingredients
    public static List<Pizza> iLikeMeat(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                    .stream()
                    .anyMatch(i->i.isMeat()))
                .sorted(Comparator.comparing((Pizza p)->p.getIngredients().stream().filter(i->i.isMeat()).count()).reversed())
                .collect(toList());
    }
    public static Map<Integer,List<Pizza>> groupByPrice(List<Pizza> pizzaList){
        return pizzaList.stream()
                .sorted(Comparator.comparing((Pizza p)->p.getName()))
                .collect(groupingBy(p->p.getIngredients().stream().mapToInt(i->i.getPrice()).sum()));
    } 
    //format pizzaName : ing1,ing2 - price newline pizzaName...
    public static String formatedMenu(List<Pizza> pizzaList){
        return pizzaList.stream()
                .map(p->String.format("%s: %s - %d",
                        p.toString(),
                        p.getIngredients().stream().map(i->i.getName()).collect(joining(", ")),
                        p.getIngredients().stream().mapToInt(i->i.getPrice()).sum())).collect(joining("\n"));
    }
    public static void main(String[] args) {
        List<Pizza>pizzaList=new ArrayList<>();
        System.out.println("Select option:\n 1)find cheapest spicy\n 2)find expensive vege\n 3) find meat pizzas\n 4)group all pizzas by price\n 5) full menu\n 6)exit");
        Scanner input=new Scanner(System.in);
        int menuOpt=input.nextInt();
        switch(menuOpt){
            case 1:
                findCheapestSpicy(pizzaList);
                break;
            case 2:
                findMostExpensiveVegetarian(pizzaList);
                break;
            case 3:
                iLikeMeat(pizzaList);
                break;
            case 4:
                groupByPrice(pizzaList);
                break;
            case 5:
                formatedMenu(pizzaList);
                break;
            case 6:
                System.exit(0);
                break;
        }
    }
    
}
