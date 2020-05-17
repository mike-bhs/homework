package com.homework.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//  Task 2. (5 points) A person stream
//  Create:
//  1. Person class with name and age fields
//  2. A collection of Persons;
//  3. Two instances of Comparator<Person> interface using lambda expressions: first one for
//      comparing by name, second one – by age
//      Then sort them using these comparators.
//      Use forEach method for printing information about all the persons.
//      Try to use method reference if it is possible.
public class ComparisonTask {
    public static void execute() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 29));
        persons.add(new Person("Bruce", 38));
        persons.add(new Person("Adam", 25));
        persons.add(new Person("Stew", 34));

        // Sort by age
        Collections.sort(persons, new PersonAgeComparator());
        System.out.println("Persons sorted by age:");
        for (Person p : persons) {
            System.out.println(p.getName() + " " + p.getAge());
        }

        // Sort by name
        Collections.sort(persons, new PersonNameComparator());
        System.out.println("Persons sorted by name:");
        for (Person p : persons) {
            System.out.println(p.getName() + " " + p.getAge());
        }
    }
}

class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
       this.name = name;
       this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class PersonAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
