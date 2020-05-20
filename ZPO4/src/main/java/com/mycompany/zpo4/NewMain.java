/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Plywak
 */
public class NewMain {
    static List<Thread> producers=new ArrayList<>();
    static List<Thread> consumers=new ArrayList<>();
    static BlockingQueue<Item> itemsToProduce=new LinkedBlockingQueue(100);
    static BlockingQueue<Item> itemsToConsume=new LinkedBlockingQueue(100);
    static BlockingQueue<Item> items=new LinkedBlockingQueue(100);
    
    public static void createItems(BlockingQueue itemQueue){
        for(int i=0;i<100;i++){
            itemQueue.add(new Item());
        }
    }
    
    public static void createProducers(int numOfProducers) {
        for(int i=0;i<numOfProducers;i++){
            Thread thrd=new Thread(()->{
                Item item=null;
                while(!itemsToProduce.isEmpty()){
                    try {
                        item=itemsToProduce.take();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(item!=null){
                        item.produceMe();
                        itemsToConsume.add(item);
                    }
                }
            });
            producers.add(thrd);
        }
    }
    
    public static void createConsumers(int numOfConsumers) {
        for(int i=0;i<numOfConsumers;i++){
            Thread thrd=new Thread(()->{
                Item item=null;
                while(!itemsToConsume.isEmpty() || producers.stream().anyMatch(p->p.isAlive())){
                    try {
                        item=itemsToConsume.take();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(item!=null){
                        item.consumeMe();
                    }
                }
            });
            consumers.add(thrd);
        }
    }
    
    public static void threadPollItems(){
        AtomicInteger ai=new AtomicInteger(0);
        ExecutorService es=Executors.newFixedThreadPool(7);
        long timeStart=System.currentTimeMillis();
        Runnable task=()->{
            while(ai.get()<100){
                ai.incrementAndGet();
                Item item=null;
                try {
                    item=items.take();
                } catch (InterruptedException ex) {
                    System.out.println("cant take item from items queue");
                }
                if(item!=null){
                    item.produceMe();
                    item.consumeMe();
                }
            }
        };
        for(int i=0;i<7;i++){
            es.execute(task);
        }
        es.shutdown();
        try {
            es.awaitTermination(3, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeStop=System.currentTimeMillis();
        long timeDiff=timeStop-timeStart;
        System.out.println("Time of operation in seconds:"+timeDiff/1000);
    }
    
    public static void itemsParallelStream(){
        long timeStart=System.currentTimeMillis();
        items.parallelStream().forEach(Item::produceMe);
        items.stream().parallel().forEach(Item::consumeMe);
        long timeStop=System.currentTimeMillis();
        long timeDiff=timeStop-timeStart;
        System.out.println("Time of operation in seconds:"+timeDiff/1000);
    }
    
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Wybierz opcje: 1 - Threads \n 2 - ThreadPool \n 3 - ParallelStream");
        String opt=input.nextLine();
        switch(opt){
            case"1":
                createItems(itemsToProduce);
                createProducers(4);
                createConsumers(3);
                long timeStart=System.currentTimeMillis();
                producers.stream().forEach(p->p.start());
                consumers.stream().forEach(c->c.start());
                while(producers.stream().anyMatch(p->p.isAlive()) || consumers.stream().anyMatch(c->c.isAlive())){}
                long timeStop=System.currentTimeMillis();
                long timeDiff=timeStop-timeStart;
                System.out.println("Time of operation in seconds:"+timeDiff/1000);
                break;
            case"2":
                createItems(items);
                threadPollItems();
                break;
            case"3":
                createItems(items);
                itemsParallelStream();
                break;
        }
        
    }
}
