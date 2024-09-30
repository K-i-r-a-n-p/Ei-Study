package observer;

import utils.Logger;

public class PhoneDisplay implements Observer {
    private String phoneOwner;

    public PhoneDisplay(String phoneOwner) {
        this.phoneOwner = phoneOwner;
    }

    @Override
    public void update(float temperature) throws Exception {
        if (temperature < -50 || temperature > 50) {
            throw new Exception("Invalid temperature detected.");
        }
        Logger.logInfo(phoneOwner + "'s phone displays temperature: " + temperature);
    }
}
