package observer;

import utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        try {
            notifyObservers();
        } catch (Exception e) {
            Logger.logError("Failed to notify observers: " + e.getMessage());
        }
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
