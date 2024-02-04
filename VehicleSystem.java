/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_Lab2;

import java.util.Scanner;

/**
 *
 * @author CWH
 */
// Chong Wei Hao 		17203259/1		 Tutorial Group 3
interface MovingWay {

    public abstract void move();
}

class Driving implements MovingWay {

    @Override
    public void move() {
        System.out.println("I am driving.");
    }

}

class Flying implements MovingWay {

    @Override
    public void move() {
        System.out.println("I am flying.");
    }

}

abstract class VehicleSystem {

    MovingWay movingWay;
    protected boolean changeMovingWay;

    public void performMove() {
        movingWay.move();
    }

    public abstract void startEngine();

    public void setMovingWay(MovingWay mw) {
        movingWay = mw;
    }

    public boolean getChangeMovingWay() {
        return changeMovingWay;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Object[] vehicles = {new StreetRacer(), new FormulaOne(), new AirCraft()};
        int vehicleNum = 0, movingNum = 0;

        OUTER:
        while (vehicleNum != 4) {
            movingNum = 0;
            System.out.println("Type 1 for StreetRacer, 2 for FormulaOne, 3 for AirCraft, 4 to exit:");
            try {
                vehicleNum = Integer.parseInt(scanner.next());
                switch (vehicleNum) {
                    case 4:
                        break OUTER;
                    case 1:
                    case 2:
                    case 3:
                        ((VehicleSystem) vehicles[vehicleNum - 1]).startEngine();
                        ((VehicleSystem) vehicles[vehicleNum - 1]).performMove();
                        do {
                            System.out.println("Type 1 if you want me to drive, 2 to fly, 3 to exit choosing my moving behavior:");
                            try {
                                movingNum = Integer.parseInt(scanner.next());
                                switch (movingNum) {
                                    case 3:
                                        break;
                                    case 1:
                                        ((VehicleSystem) vehicles[vehicleNum - 1]).setMovingWay(new Driving());
                                        ((VehicleSystem) vehicles[vehicleNum - 1]).startEngine();
                                        ((VehicleSystem) vehicles[vehicleNum - 1]).performMove();
                                        break;
                                    case 2:
//                                        if (vehicles[vehicleNum - 1] instanceof AirCraft) {
                                        if (((VehicleSystem) vehicles[vehicleNum - 1]).getChangeMovingWay()) {
                                            ((VehicleSystem) vehicles[vehicleNum - 1]).setMovingWay(new Flying());
                                            ((VehicleSystem) vehicles[vehicleNum - 1]).startEngine();
                                            ((VehicleSystem) vehicles[vehicleNum - 1]).performMove();
                                        } else {
                                            throw new NumberFormatException("Invalid choice of moving behavior. Try again");
                                        }
                                        break;
                                    default:
                                        throw new NumberFormatException("Invalid choice of moving behavior. Try again");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("Invalid choice of moving behavior. Try again");
                            }
                        } while (movingNum != 3);
                        break;
                    default:
                        throw new NumberFormatException("Invalid choice of type of vehicle. Try again");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice of type of vehicle. Try again");
            }
        }
    }

}

class StreetRacer extends VehicleSystem {

    public StreetRacer() {
        movingWay = new Driving();
        changeMovingWay = false;
    }

    @Override
    public void startEngine() {
        System.out.print("I am a StreetRacer. ");
    }

}

class FormulaOne extends VehicleSystem {

    public FormulaOne() {
        movingWay = new Driving();
        changeMovingWay = false;
    }

    @Override
    public void startEngine() {
        System.out.print("I am a FormulaOne. ");
    }

}

class AirCraft extends VehicleSystem {

    public AirCraft() {
        movingWay = new Flying();
        changeMovingWay = true;
    }

    @Override
    public void startEngine() {
        System.out.print("I am a AirCraft. ");
    }

}
