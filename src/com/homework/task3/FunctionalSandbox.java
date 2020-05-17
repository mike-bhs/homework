package com.homework.task3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

//  Task 3. (10 points) Functional Interface Sandbox
//  1. Implement each of main Java Standard Library functional interfaces (supplier, predicate etc.)
//  using lambda expressions.
//  2. Create your own functional interface and add several implementations using both lambda expressions and inner anonymous classes.
//  3. Add few default methods to it and use them.
//  4. Add few static methods to it and use them.
public class FunctionalSandbox {
    public static void execute() {
        Supplier<String> stringSupplier = () -> {
            System.out.println("Supplier returns lazy string ...");
            return "magic string";
        };

        stringSupplier.get();

        Consumer<String> stringConsumer = (string) -> {
            System.out.println("Consumer received " + string);
        };

        Predicate<String> plumPredicate = (string) -> string.equalsIgnoreCase("plum");

        List<String> fruits = Arrays.asList("Apple", "Orange", "Peach", "Plum");
        fruits.forEach(stringConsumer);

        System.out.println("Seems like those fruits are kinda plums:");
        fruits.stream().filter(plumPredicate).forEach(System.out::println);

        System.out.println("Lets see what lambda has to say ...");
        sayGreetings(() -> System.out.println("Hello from lambda"));

        System.out.println("Lets see what anonymous class has to say ...");
        sayGreetings(new Greeter() {
            @Override
            public void greet() {
                System.out.println("Anonymous greeting");
            }
        });

        System.out.println("Lets see what default interface method has to say ...");
        sayHello(() -> System.out.println("Will not greet you"));

        System.out.println("That's it, I'll tell goodbye in french ...");
        Greeter.auRevoir();
    }

    static void sayGreetings(Greeter g) {
        g.greet();
    }

    static void sayHello(Greeter g) {
        g.hello();
    }
}

@FunctionalInterface
interface Greeter {
    void greet();

    default void hello() {
        System.out.println("Hello");
    }

    static void auRevoir() {
        System.out.println("You're expecting au revoir, but I'm saying adios");
    }
}
