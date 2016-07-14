package payment;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class PaymentMethodFactory {
    public static OrderPaymentMethod getPaymentMethod(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "gotówka": return new InCashPayment();
            case "potrącenie": return new SalarySystemPayment();
            default: return null;
        }
    }
}
