package factory;

import utils.Logger;

public class Cat implements Animal {
    @Override
    public void makeSound() {
        Logger.logInfo("Cat meows.");
    }
}
