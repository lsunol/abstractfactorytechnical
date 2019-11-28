package instantcredit;

import instantcredit.pizzas.CheesePizza;
import instantcredit.pizzas.PepperoniPizza;
import instantcredit.pizzas.Pizza;
import org.junit.Test;

public class Case_02 {

    @Test
    public void heyIWantedACheesePizza() {
        Pizza pizza = orderPizza("cheese");
        System.out.println(pizza.toString());
    }

    Pizza orderPizza(String type) {

        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
