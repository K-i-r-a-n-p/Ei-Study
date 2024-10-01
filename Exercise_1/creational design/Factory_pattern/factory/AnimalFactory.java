package factory;

import utils.Logger;

public class AnimalFactory {
    public Animal getAnimal(String type) throws Exception {
        if (type == null || type.isEmpty()) {
            throw new Exception("Animal type cannot be null or empty.");
        }

        switch (type.toLowerCase()) {
            case "dog":
                return new Dog();
            case "cat":
                return new Cat();
            default:
                throw new Exception("Unknown animal type: " + type);
        }
    }
}
