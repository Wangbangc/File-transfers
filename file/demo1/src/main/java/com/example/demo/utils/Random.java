package com.example.demo.utils;

public class Random {
    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();
        int num = rand.nextInt(900000000) + 100000000;
        System.out.println(num);
    }
}