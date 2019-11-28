package instantcredit;

import org.junit.Test;

public class Case_01 {

    @Test
    public void letsEatAPizza() {
        orderPizza();
    }

    public static Pizza orderPizza() {
        Pizza pizza = new Pizza();

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    private static class Pizza {
        public void prepare() {}
        public void bake() {}
        public void cut() {}
        public void box() {}
    }
}
