public class DeliveryStrategySystem {

    public static void main(String[] args) {

        OrderService order1 =
                new OrderService(
                        "A001",
                        1500,
                        new HomeDelivery());

        OrderService order2 =
                new OrderService(
                        "A002",
                        800,
                        new StorePickup());

        OrderService order3 =
                new OrderService(
                        "A003",
                        500,
                        new SelfPickup());

        System.out.println("=== 訂單一 ===");
        order1.showDeliveryInfo();

        System.out.println("\n=== 訂單二 ===");
        order2.showDeliveryInfo();

        System.out.println("\n=== 訂單三 ===");
        order3.showDeliveryInfo();

        // 測試切換配送方式
        System.out.println("\n=== 更換配送方式 ===");

        order1.setDeliveryMethod(
                new StorePickup());

        order1.showDeliveryInfo();
    }
}

// DeliveryMethod Interface
interface DeliveryMethod {

    double calculateShippingFee(double orderAmount);

    String getDescription();
}

// 宅配
class HomeDelivery
        implements DeliveryMethod {

    @Override
    public double calculateShippingFee(
            double orderAmount) {

        return 100;
    }

    @Override
    public String getDescription() {

        return "宅配到府，預計 1~2 天送達";
    }
}

// 超商取貨
class StorePickup
        implements DeliveryMethod {

    @Override
    public double calculateShippingFee(
            double orderAmount) {

        return 60;
    }

    @Override
    public String getDescription() {

        return "超商取貨，預計 2~3 天到店";
    }
}

// 自取
class SelfPickup
        implements DeliveryMethod {

    @Override
    public double calculateShippingFee(
            double orderAmount) {

        return 0;
    }

    @Override
    public String getDescription() {

        return "門市自取，當日即可領取";
    }
}

// 使用 Composition
class OrderService {

    private String orderId;
    private double orderAmount;
    private DeliveryMethod deliveryMethod;

    public OrderService(
            String orderId,
            double orderAmount,
            DeliveryMethod deliveryMethod) {

        this.orderId = orderId;
        this.orderAmount = Math.max(0, orderAmount);
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(
            DeliveryMethod deliveryMethod) {

        this.deliveryMethod = deliveryMethod;
    }

    public void showDeliveryInfo() {

        System.out.println(
                "訂單編號：" + orderId);

        System.out.println(
                "訂單金額：" + orderAmount);

        System.out.println(
                "運費：" +
                deliveryMethod.calculateShippingFee(
                        orderAmount));

        System.out.println(
                "配送說明：" +
                deliveryMethod.getDescription());
    }
}
