public class TransportFareSystem {

    public static void main(String[] args) {

        Transport[] transports = {

                new Bus("紅線公車"),
                new Taxi("市區計程車"),
                new Bus("藍線公車"),
                new Taxi("機場計程車")
        };

        int distance = 10;

        System.out.println("距離：" + distance + " 公里\n");

        for (Transport transport : transports) {

            System.out.println(
                    "路線：" + transport.getRouteName());

            System.out.println(
                    "票價：" +
                    transport.calculateFare(distance)
                    + " 元");

            System.out.println();
        }
    }
}

// 抽象類別
abstract class Transport {

    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public abstract double calculateFare(int distance);
}

// Bus 子類別
class Bus extends Transport {

    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {

        // 公車：每公里 2 元
        return distance * 2;
    }
}

// Taxi 子類別
class Taxi extends Transport {

    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {

        // 計程車：起跳 70 元 + 每公里 5 元
        return 70 + distance * 5;
    }
}