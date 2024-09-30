package main;

import observer.WeatherStation;
import observer.PhoneDisplay;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        PhoneDisplay alicePhone = new PhoneDisplay("Alice");
        PhoneDisplay bobPhone = new PhoneDisplay("Bob");

        station.addObserver(alicePhone);
        station.addObserver(bobPhone);

        station.setTemperature(25.5f);  // Normal
        station.setTemperature(20.5f);    
        station.setTemperature(-60f);   // Invalid, exception thrown
    }
}
