package instantcredit;

import instantcredit.ingredients.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class Case_06_StylingPizzas_ChicagoPizzaStore {

    @Test
    public void play() {

        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza nyCheesePizza = nyStore.orderPizza("cheese");
        System.out.println(nyCheesePizza);

        Pizza chicagoCheesePizza = chicagoStore.orderPizza("cheese");
        System.out.println(chicagoCheesePizza);

        System.out.println(nyStore.orderPizza("pepperoni"));
        System.out.println(chicagoStore.orderPizza("pepperoni"));

    }


    private class NYPizzaStore extends PizzaStore {

        PizzaIngredientFactory ingredientFactory =
                new NYPizzaIngredientFactory();

        protected Pizza createPizza(String item) {
            Pizza pizza = null;

            if (item.equals("cheese")) {

                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("New York Style Cheese Pizza");

            } else if (item.equals("pepperoni")) {

                pizza = new PepperoniPizza(ingredientFactory);
                pizza.setName("New York Style Pepperoni Pizza");

            }
            return pizza;
        }

    }

    public class ChicagoPizzaStore extends PizzaStore {

        protected Pizza createPizza(String item) {
            Pizza pizza = null;
            PizzaIngredientFactory ingredientFactory =
                    new ChicagoPizzaIngredientFactory();

            if (item.equals("cheese")) {

                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("Chicago Style Cheese Pizza");

            } else if (item.equals("pepperoni")) {

                pizza = new PepperoniPizza(ingredientFactory);
                pizza.setName("Chicago Style Pepperoni Pizza");

            }
            return pizza;
        }
    }

    private abstract class PizzaStore {

        abstract Pizza createPizza(String item);

        final Pizza orderPizza(String type) {
            Pizza pizza = createPizza(type);
            System.out.println("--- Making a " + pizza.getName() + " ---");
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    }






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

    public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory
    {

        public Dough createDough() {
            return new ThickCrustDough();
        }

        public Sauce createSauce() {
            return new PlumTomatoSauce();
        }

        public Cheese createCheese() {
            return new MozzarellaCheese();
        }

        public Veggies[] createVeggies() {
            Veggies veggies[] = { new BlackOlives(),
                    new Spinach(),
                    new Eggplant() };
            return veggies;
        }

        public Pepperoni createPepperoni() {
            return new SlicedPepperoni();
        }

        public Clams createClam() {
            return new FrozenClams();
        }
    }










    private class CheesePizza extends Pizza {
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

    private class PepperoniPizza extends Pizza {
        PizzaIngredientFactory ingredientFactory;

        public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }

        void prepare() {
            System.out.println("Preparing " + name);
            dough = ingredientFactory.createDough();
            sauce = ingredientFactory.createSauce();
            cheese = ingredientFactory.createCheese();
            veggies = ingredientFactory.createVeggies();
            pepperoni = ingredientFactory.createPepperoni();
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
