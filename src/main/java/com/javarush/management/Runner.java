package com.javarush.management;

import com.javarush.repository.MovieManager;

public class Runner {

    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        System.out.println(manager.getPaymentById((short) 2).getRental().getStaff());
    }
}
