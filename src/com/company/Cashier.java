package com.company;

public class Cashier {
    
    private final String ticket = "Билет в Ош";
    public static int amountOfCahier;
    private String name;

    public Cashier(String name) {
        amountOfCahier ++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTicket() {
        return ticket;
    }

}
