public class FlexibleCheckoutSystem {

    public static void main(String[] args) {

        PricingPolicy[] policies = {
                new OriginalPricePolicy(),
                new VipDiscountPolicy(),
                new FullReductionPolicy()
        };

        NotificationChannel[] channels = {
                new EmailChannel(),
                new SmsChannel(),
                new ConsoleChannel()
        };

        String orderId = "ORD001";
        double price = 2500;

        for (PricingPolicy policy : policies) {

            for (NotificationChannel channel : channels) {

                System.out.println("\n====================");

                CheckoutResult result =
                        checkout(
                                orderId,
                                price,
                                policy,
                                channel);

                System.out.println(result);
            }
        }
    }

    public static CheckoutResult checkout(
            String orderId,
            double originalPrice,
            PricingPolicy policy,
            NotificationChannel channel) {

        double finalPrice =
                policy.calculatePrice(originalPrice);

        boolean notified =
                channel.sendNotification(
                        orderId,
                        finalPrice);

        return new CheckoutResult(
                orderId,
                originalPrice,
                finalPrice,
                notified);
    }
}

/* =========================
   Pricing Policy
   ========================= */

interface PricingPolicy {

    double calculatePrice(double price);
}

// 原價
class OriginalPricePolicy
        implements PricingPolicy {

    @Override
    public double calculatePrice(double price) {
        return price;
    }
}

// VIP 八五折
class VipDiscountPolicy
        implements PricingPolicy {

    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}

// 滿2000折300
class FullReductionPolicy
        implements PricingPolicy {

    @Override
    public double calculatePrice(double price) {

        if (price >= 2000) {
            return price - 300;
        }

        return price;
    }
}

/* =========================
   Notification Channel
   ========================= */

interface NotificationChannel {

    boolean sendNotification(
            String orderId,
            double finalPrice);
}

// Email
class EmailChannel
        implements NotificationChannel {

    @Override
    public boolean sendNotification(
            String orderId,
            double finalPrice) {

        System.out.println(
                "[Email] Order "
                        + orderId
                        + " Final Price = "
                        + finalPrice);

        return true;
    }
}

// SMS
class SmsChannel
        implements NotificationChannel {

    @Override
    public boolean sendNotification(
            String orderId,
            double finalPrice) {

        System.out.println(
                "[SMS] Order "
                        + orderId
                        + " Final Price = "
                        + finalPrice);

        return true;
    }
}

// Console
class ConsoleChannel
        implements NotificationChannel {

    @Override
    public boolean sendNotification(
            String orderId,
            double finalPrice) {

        System.out.println(
                "[Console] Order "
                        + orderId
                        + " Final Price = "
                        + finalPrice);

        return true;
    }
}

/* =========================
   Checkout Result
   ========================= */

class CheckoutResult {

    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationStatus;

    public CheckoutResult(
            String orderId,
            double originalPrice,
            double finalPrice,
            boolean notificationStatus) {

        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {

        return "Order ID: " + orderId
                + "\nOriginal Price: " + originalPrice
                + "\nFinal Price: " + finalPrice
                + "\nNotification Status: "
                + notificationStatus;
    }
}