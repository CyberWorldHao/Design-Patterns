/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_Week_2_Mini_Duck_Simulator;

/**
 *
 * @author CWH
 */
interface FlyBehavior {

    public void fly();
}

class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}

class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}

interface QuackBehavior {

    public void quack();
}

class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}

class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}

abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

}

class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

class RedHeadDuck extends Duck {

    @Override
    public void display() {
        //look like a redhead
    }

}

class RubberDuck extends Duck {

    @Override
    public void display() {
        //look like a rubberduck
    }
}

class DecoyDuck extends Duck {

    @Override
    public void display() {
        //decoy duck
    }
}

public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
        System.out.println("");
        System.out.println("BUMP !!!");
        mallard.setFlyBehavior(new FlyNoWay());
        System.out.println("Mallard duck can't longer fly because the mallard duck breaks one of its wings . . .");
        System.out.println("");
        mallard.performQuack();
        mallard.performFly();
    }

}
