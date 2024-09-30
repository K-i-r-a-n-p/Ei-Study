package main;

import context.ShoppingCart;
import strategy.CreditCardPayment;
import strategy.PayPalPayment;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(100);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);

        // Test invalid amount
        cart.checkout(-50);
    }
}
