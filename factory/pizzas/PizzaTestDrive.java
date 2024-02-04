package headfirst.designpatterns.factory.pizzas;

public class PizzaTestDrive {
 
    public static void main(String[] args) {
            SimplePizzaFactory factory = new SimplePizzaFactory();
            PizzaStore store = new PizzaStore(factory);

            Pizza pizza1;  //program to interface(supertype) and not implementation
            pizza1 = store.orderPizza("cheese");
            System.out.println("We ordered a " + pizza1.getName() + "\n");
            System.out.println(pizza1);

            pizza1 = store.orderPizza("veggie");
            System.out.println("We ordered a " + pizza1.getName() + "\n");
            System.out.println(pizza1);
            
           
            
    }
}
