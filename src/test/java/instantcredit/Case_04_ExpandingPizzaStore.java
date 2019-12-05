package instantcredit;

import instantcredit.pizzas.*;
import org.junit.Test;

public class Case_04_ExpandingPizzaStore {

    private PizzaStore nyPizzaStore = new NYPizzaStore();
    private PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

    @Test
    public void play() {
        System.out.println(nyPizzaStore.orderPizza("cheese"));
        System.out.println(chicagoPizzaStore.orderPizza("cheese"));
    }

    private class NYPizzaStore extends PizzaStore {

        @Override
        Pizza createPizza(String type) {
            if (type.equals("cheese")) return new NYStyleCheesePizza();
            else if (type.equals("pepperoni")) return new NYStylePepperoniPizza();
            return null;
        }

    }
    private class ChicagoPizzaStore extends PizzaStore {

        @Override
        Pizza createPizza(String type) {
            if (type.equals("cheese")) return new ChicagoStyleCheesePizza();
            else if (type.equals("pepperoni")) return new ChicagoStylePepperoniPizza();
            return null;
        }

    }

    private abstract class PizzaStore {

        public Pizza orderPizza(String type) {

            Pizza pizza = createPizza(type);

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }

        abstract Pizza createPizza(String type);
    }
}




