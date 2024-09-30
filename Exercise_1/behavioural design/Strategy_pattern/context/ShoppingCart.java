package context;

import strategy.PaymentStrategy;
import utils.Logger;

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        try {
            paymentStrategy.pay(amount);
        } catch (Exception e) {
            Logger.logError("Payment failed: " + e.getMessage());
        }
    }
}
