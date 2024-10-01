package main;

import decorator.BasicCoffee;
import decorator.Coffee;
import decorator.MilkDecorator;
import decorator.SugarDecorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // Start with basic coffee
        Coffee coffee = new BasicCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        // Add milk to the coffee
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        // Add sugar to the coffee
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
