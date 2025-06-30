package com.example.fastfx.tests;

public class Main {
    public static void main(String[] args) {


//        Map<Integer, MathOperation> operations = new HashMap<>();
//
//        MathOperation sum = (a, b) -> a + b;
//        MathOperation product = (a, b) -> a * b;
//        MathOperation diff = (a, b) -> a - b;
//
//        operations.put(1, sum);
//        operations.put(2, product);
//        operations.put(3 , diff);
//
//        System.out.println(operations.get(1).operation(3,3));
//        System.out.println(operations.get(2).operation(3,3));
//        System.out.println(operations.get(3).operation(3,3));
//        System.out.println(operations.get(4).operation(3,3));


//        Person person1 = new Person(25, "Paul");
//
//        MyLambda l1 = person -> {
//            System.out.println(person.getName());
//            System.out.println(person.getAge());
//        };
//
//        l1.printInfo(person1);

//        List<Integer> nums = new ArrayList<>();
//        nums.add(0, 4);
//        nums.add(0, 3);
//        nums.add(2, 5);
//        nums.add(0, 1);
//        nums.add(1, 2);
//
//
//        System.out.println(nums.size());
//        System.out.println(nums);

        InsertionSort insertionSort = new InsertionSort();

        insertionSort.sort(new int[]{5,2,4,3});
        System.out.println(insertionSort.getSortedNumbers());


    }
}
