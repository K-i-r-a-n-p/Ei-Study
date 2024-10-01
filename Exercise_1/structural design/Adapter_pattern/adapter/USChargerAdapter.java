package adapter;

import utils.Logger;

// Adapter to make US Charger compatible with European Socket
public class USChargerAdapter implements Charger {
    private USCharger usCharger;

    public USChargerAdapter(USCharger usCharger) {
        this.usCharger = usCharger;
    }

    @Override
    public void charge() {
        usCharger.connectToUSSocket();
        Logger.logInfo("Charging using US Charger through the adapter.");
    }
}
