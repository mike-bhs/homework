package com.homework.task1;

//  Task 1. (5 points) A bit of concurrency
//  Create several instances of Runnable interface with different behavior using lambda
//  expressions. Run these lambdas via Thread API.

public class ConcurrencyTask {
    public static void execute() {
        new Thread(() -> System.out.println("Runnable using lambda #1")).start();
        new Thread(() -> System.out.println("Runnable using lambda #3")).start();
        new Thread(() -> System.out.println("Runnable using lambda #2")).start();
    }
}
