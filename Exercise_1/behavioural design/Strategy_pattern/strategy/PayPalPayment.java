package strategy;

import utils.Logger;

public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Invalid payment amount.");
        }
        Logger.logInfo("Paid " + amount + " using PayPal: " + email);
    }
}
