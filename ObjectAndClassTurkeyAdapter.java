/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6.Exercise;

/**
 *
 * @author CWH
 */
interface Duck {

    public void quack();

    public void fly();
}

interface Turkey {

    public void gobble();

    public void fly();
}

abstract class TurkeyAbstractClass {
    
    Turkey turkey = new WildTurkey();

    public void gobble(){};

    public void fly(){};
}

class MallardDuck implements Duck {

    public void quack() {
        System.out.println("Quack");
    }

    public void fly() {
        System.out.println("I'm flying");
    }
}

class WildTurkey implements Turkey {

    public void gobble() {
        System.out.println("Gobble gobble");
    }

    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}

class ObjectTurkeyAdapter implements Duck {

    Turkey turkey;

    public ObjectTurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    public void quack() {
        turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}

class ClassTurkeyAdapter extends TurkeyAbstractClass implements Duck {

    public void quack() {
       
        gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}

public class ObjectAndClassTurkeyAdapter {

    public static void main(String[] args) {
        ///////////////////     ObjectTurkeyAdapter     ///////////////////
        
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck objectTurkeyAdapter = new ObjectTurkeyAdapter(turkey);

        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");
        testDuck(duck);

        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(objectTurkeyAdapter);
        
        
        
        ///////////////////     ClassTurkeyAdapter     ///////////////////
        
        Duck classTurkeyAdapter = new ClassTurkeyAdapter();

        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(classTurkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
