package instantcredit;

import instantcredit.pizzas.CheesePizza;
import instantcredit.pizzas.PepperoniPizza;
import instantcredit.pizzas.Pizza;
import org.junit.Test;

public class Case_03_SimpleFactory {

    PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());

    @Test
    public void play() {
        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println(pizza.toString());
    }

    private class PizzaStore {

        SimplePizzaFactory pizzaFactory;

        public PizzaStore(SimplePizzaFactory pizzaFactory) {
            this.pizzaFactory = pizzaFactory;
        }

        public Pizza orderPizza(String type) {

            Pizza pizza;

            pizza = pizzaFactory.createPizza(type);

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }
    }

    private class SimplePizzaFactory {
        public Pizza createPizza(String type) {
            Pizza pizza = null;

            if (type.equals("cheese")) {
                pizza = new CheesePizza();
            } else if (type.equals("pepperoni")) {
                pizza = new PepperoniPizza();
            }
            return pizza;
        }
    }
}
