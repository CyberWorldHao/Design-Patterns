/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5.Exercise;

/**
 *
 * @author CWH
 */
abstract class Beverage {

    String description = "Unknown Beverage";
    String size = "Unknown Size";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public void setSize(String size) {
        this.size = size.toUpperCase();
    }

    public String getSize() {
        return size;
    }
}

abstract class CondimentDecorator extends Beverage {

    public abstract String getDescription();

    public abstract void setSize(String size);

    public abstract String getSize();
}

////// Concrete Class - Coffee Type //////
class DarkRoast extends Beverage {

    public DarkRoast(String size) {
        setSize(size);
        description = getSize() + " Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }

}

class Decaf extends Beverage {

    public Decaf(String size) {
        setSize(size);
        description = getSize() + " Decaf Coffee";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}

class Espresso extends Beverage {

    public Espresso(String size) {
        setSize(size);
        description = getSize() + " Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

class HouseBlend extends Beverage {

    public HouseBlend(String size) {
        setSize(size);
        description = getSize() + " House Blend Coffee";
    }

    public double cost() {
        return .89;
    }
}
/////////////////////////////////////////

///////// Condiment Decorations /////////
class Milk extends CondimentDecorator {

    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        double tempCost = 0;
        switch (beverage.getSize()) {
            case "TALL":
                tempCost = .10 + beverage.cost();
                break;
            case "GRANDE":
                tempCost = .15 + beverage.cost();
                break;
            case "VENTI":
                tempCost = .20 + beverage.cost();
                break;
        }
        return tempCost;
    }

    @Override
    public void setSize(String size) {
        this.beverage.size = size;
    }

    @Override
    public String getSize() {
        return beverage.getSize();
    }
}

class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        double tempCost = 0;
        switch (beverage.getSize()) {
            case "TALL":
                tempCost = .15 + beverage.cost();
                break;
            case "GRANDE":
                tempCost = .20 + beverage.cost();
                break;
            case "VENTI":
                tempCost = .25 + beverage.cost();
                break;
        }
        return tempCost;
    }

    @Override
    public void setSize(String size) {
        this.beverage.size = size;
    }

    @Override
    public String getSize() {
        return beverage.getSize();
    }
}

class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        double tempCost = 0;
        switch (beverage.getSize()) {
            case "TALL":
                tempCost = .20 + beverage.cost();
                break;
            case "GRANDE":
                tempCost = .25 + beverage.cost();
                break;
            case "VENTI":
                tempCost = .30 + beverage.cost();
                break;
        }
        return tempCost;
    }

    @Override
    public void setSize(String size) {
        this.beverage.size = size;
    }

    @Override
    public String getSize() {
        return beverage.getSize();
    }
}

class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        double tempCost = 0;
        switch (beverage.getSize()) {
            case "TALL":
                tempCost = .10 + beverage.cost();
                break;
            case "GRANDE":
                tempCost = .15 + beverage.cost();
                break;
            case "VENTI":
                tempCost = .20 + beverage.cost();
                break;
        }
        return tempCost;
    }

    @Override
    public void setSize(String size) {
        this.beverage.size = size;
    }

    @Override
    public String getSize() {
        return beverage.getSize();
    }
}
//////////////////////////////////////////

public class DecoratorStarbuzzCoffeeSystem {

    public static void main(String args[]) {
        Beverage beverage = new Espresso("tAlL");
        beverage = new Milk(beverage);
        System.out.println(beverage.getDescription()
                + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast("Grande");
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
                + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend("venti");
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println( beverage3.getDescription()
                + " $" + beverage3.cost());
    }
}
