package com.company;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static ArrayList<Passenger> passengerArrayList = new ArrayList<>();

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        Semaphore semaphore = new Semaphore(4);
        Cashier cashier1 = new Cashier("Касса 1");
        Cashier cashier2 = new Cashier("Касса 2");
        Cashier cashier3 = new Cashier("Касса 3");
        Cashier cashier4 = new Cashier("Касса 4");

        for (int i = 1; i <= 100 ; i++) {
            Passenger passenger = new Passenger(semaphore,countDownLatch);
            passenger.setName("Пассажир " + i);
            passengerArrayList.add(passenger);
        }

        for (int i = 0; i < 100 ; i+=4) {
            passengerArrayList.get(i).setCashier(cashier1);
            passengerArrayList.get(i+1).setCashier(cashier2);
            passengerArrayList.get(i+2).setCashier(cashier3);
            passengerArrayList.get(i+3).setCashier(cashier4);
        }

        for (int i = 0; i < 100; i++) {
            passengerArrayList.get(i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Выдвигаемся в Ош");
    }

}
