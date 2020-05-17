package com.homework.task4;

import com.sun.jdi.ThreadReference;

//Task 4. (5 points) Custom Functional Interface
//  1. Write your own functional interface ThreeFunction (it takes three arguments and produce result).
//  2. Implement this with two different lambdas
//  3. Provide client code with usage of this lambdas
public class CustomFunctionalInterface {
    public static void execute() {
        pythagoreanTheoremClient((double a, double b, double c) -> Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2));
        pythagoreanTheoremClient((double a, double b, double c) -> a + b == c);
    }

    static void pythagoreanTheoremClient(ThreeFunction tf) {
        if (tf.isPythagoreanTheorem(3, 4, 5)) {
            System.out.println("You've implemented the theorem successfully");
        } else {
            System.out.println("Looks like th theorem you've implemented doesn't work");
        }
    }
}

interface ThreeFunction {
    boolean isPythagoreanTheorem(double a, double b, double c);
}
