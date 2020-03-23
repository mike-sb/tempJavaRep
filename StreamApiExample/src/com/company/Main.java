package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();

        long count = cars.stream().filter(t->t.getCost() > 500000).count();
        Optional<Car> optionalCar = cars.stream().filter(t->t.getYear() == 2019).findFirst();
        if (optionalCar.isPresent())
        {
            Car car = optionalCar.get();
        }
        cars.stream().forEach(t-> System.out.println(t.getModel()));
        cars.stream().forEach(System.out::println);
    }
}
