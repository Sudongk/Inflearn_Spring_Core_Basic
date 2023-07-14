package spring.core.basic.singleton;

public class StatefulService {

    private int price; // 상태를 가짐

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
