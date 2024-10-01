package main;

import factory.Animal;
import factory.AnimalFactory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();

        try {
            Animal dog = animalFactory.getAnimal("dog");
            dog.makeSound();

            Animal cat = animalFactory.getAnimal("cat");
            cat.makeSound();

            // Testing invalid input
            Animal unknown = animalFactory.getAnimal("elephant"); // Should throw an exception
            unknown.makeSound();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
