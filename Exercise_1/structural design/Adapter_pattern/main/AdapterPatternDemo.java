package main;

import adapter.Charger;
import adapter.USCharger;
import adapter.USChargerAdapter;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        // Use the adapter to charge using a US Charger in a European environment
        USCharger usCharger = new USCharger();
        Charger chargerAdapter = new USChargerAdapter(usCharger);

        // Charging through the adapter
        chargerAdapter.charge();
    }
}
