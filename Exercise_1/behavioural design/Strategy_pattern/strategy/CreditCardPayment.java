package strategy;

import utils.Logger;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Invalid payment amount.");
        }
        Logger.logInfo("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}
