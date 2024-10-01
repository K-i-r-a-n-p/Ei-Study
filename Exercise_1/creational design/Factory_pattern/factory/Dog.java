package factory;

import utils.Logger;

public class Dog implements Animal {
    @Override
    public void makeSound() {
        Logger.logInfo("Dog barks.");
    }
}
