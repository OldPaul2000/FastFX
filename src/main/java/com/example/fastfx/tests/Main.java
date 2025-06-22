package com.example.fastfx.tests;

public class Main {
    public static void main(String[] args) {


        Person person1 = new Person(25, "Paul");

        MyLambda l1 = person -> {
            System.out.println(person.getName());
            System.out.println(person.getAge());
        };

        l1.printInfo(person1);

    }
}
