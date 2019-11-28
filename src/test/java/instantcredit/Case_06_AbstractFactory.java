package instantcredit;

import instantcredit.ingredients.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class Case_06_AbstractFactory {

    @Test
    public void play() {

        PizzaStore nyStore = new NYPizzaStore();
        Pizza nyCheesePizza = nyStore.orderPizza("cheese");
        System.out.println(nyCheesePizza);

    }

    private class NYPizzaStore extends PizzaStore {

        protected Pizza createPizza(String item) {
            Pizza pizza = null;
            PizzaIngredientFactory ingredientFactory =
                    new NYPizzaIngredientFactory();

            if (item.equals("cheese")) {

                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("New York Style Cheese Pizza");

//            } else if (item.equals("veggie")) {
//
//                pizza = new VeggiePizza(ingredientFactory);
//                pizza.setName("New York Style Veggie Pizza");
//
//            } else if (item.equals("clam")) {
//
//                pizza = new ClamPizza(ingredientFactory);
//                pizza.setName("New York Style Clam Pizza");
//
//            } else if (item.equals("pepperoni")) {
//
//                pizza = new PepperoniPizza(ingredientFactory);
//                pizza.setName("New York Style Pepperoni Pizza");
//
            }
            return pizza;
        }

    }

    private abstract class PizzaStore {

        protected abstract Pizza createPizza(String item);

        public Pizza orderPizza(String type) {
            Pizza pizza = createPizza(type);
            System.out.println("--- Making a " + pizza.getName() + " ---");
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    }

    //The abstract factory
    private interface PizzaIngredientFactory {

        Dough createDough();
        Sauce createSauce();
        Cheese createCheese();
        Veggies[] createVeggies();
        Pepperoni createPepperoni();
        Clams createClam();

    }

    class NYPizzaIngredientFactory implements PizzaIngredientFactory {

        public Dough createDough() {
            return new ThinCrustDough();
        }

        public Sauce createSauce() {
            return new MarinaraSauce();
        }

        public Cheese createCheese() {
            return new ReggianoCheese();
        }

        public Veggies[] createVeggies() {
            Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
            return veggies;
        }

        public Pepperoni createPepperoni() {
            return new SlicedPepperoni();
        }

        public Clams createClam() {
            return new FreshClams();
        }

    }

    class CheesePizza extends Pizza {
        PizzaIngredientFactory ingredientFactory;

        public CheesePizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }

        void prepare() {
            System.out.println("Preparing " + name);
            dough = ingredientFactory.createDough();
            sauce = ingredientFactory.createSauce();
            cheese = ingredientFactory.createCheese();
        }
    }

    private abstract class Pizza {

        String name;

        Dough dough;
        Sauce sauce;
        Veggies veggies[];
        Cheese cheese;
        Pepperoni pepperoni;
        Clams clam;

        abstract void prepare();

        void bake() {
            System.out.println("Bake for 25 minutes at 350");
        }

        void cut() {
            System.out.println("Cutting the pizza into diagonal slices");
        }

        void box() {
            System.out.println("Place pizza in official PizzaStore box");
        }

        void setName(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        public String toString() {
            StringBuffer result = new StringBuffer();
            result.append("---- " + name + " ----\n");
            if (dough != null) {
                result.append(dough);
                result.append("\n");
            }
            if (sauce != null) {
                result.append(sauce);
                result.append("\n");
            }
            if (cheese != null) {
                result.append(cheese);
                result.append("\n");
            }
            if (veggies != null) {
                for (int i = 0; i < veggies.length; i++) {
                    result.append(veggies[i]);
                    if (i < veggies.length-1) {
                        result.append(", ");
                    }
                }
                result.append("\n");
            }
            if (clam != null) {
                result.append(clam);
                result.append("\n");
            }
            if (pepperoni != null) {
                result.append(pepperoni);
                result.append("\n");
            }
            return result.toString();
        }
    }
}
