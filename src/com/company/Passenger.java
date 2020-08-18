package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Passenger extends Thread {

    private String ticket;
    Cashier cashier;
    CountDownLatch countDownLatch;
    Semaphore semaphore;

    public Passenger(Semaphore semaphore,CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.semaphore = semaphore;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        ticket = cashier.getTicket();
        System.out.println(this.getName() + " покупает билет на " + cashier.getName());
            sleep(3000);
        System.out.println(this.getName() + " приобрел билет");
        semaphore.release();

        System.out.println(this.getName() + " садится в автобус");
        countDownLatch.countDown();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
