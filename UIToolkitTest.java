/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_Lab4;

import java.util.Scanner;

/**
 *
 * @author CWH
 */
// Chong Wei Hao 		17203259/1		 Tutorial Group 3
interface ScrollBar {

    public void setDirection();
}

interface Window {

    public void setTitle(String title);
}

interface AbstractWidgetFactory {

    public ScrollBar createScrollBar();

    public Window createWindow();

}

class MotifWidgetFactory implements AbstractWidgetFactory {

    @Override
    public ScrollBar createScrollBar() {
        return new MotifScrollBar();
    }

    @Override
    public Window createWindow() {
        return new MotifWindow();
    }

}

class PMWidgetFactory implements AbstractWidgetFactory {

    @Override
    public ScrollBar createScrollBar() {
        return new PMScrollBar();
    }

    @Override
    public Window createWindow() {
        return new PMWindow();
    }

}

class PMWindow implements Window {

    @Override
    public void setTitle(String title) {
        System.out.println("Presentation Manager Window and the title of the window is " + title);
    }
}

class MotifWindow implements Window {

    @Override
    public void setTitle(String title) {
        System.out.println("Motif Window and the title of the window is : " + title);
    }
}

class PMScrollBar implements ScrollBar {

    @Override
    public void setDirection() {
        System.out.println("Presentation Manager ScrollBar direction is horizontal");
    }
}

class MotifScrollBar implements ScrollBar {

    @Override
    public void setDirection() {
        System.out.println("Motif ScrollBar direction is vertical");
    }
}

public class UIToolkitTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractWidgetFactory widgetFactoy;
        int widgetNum = 0;
        OUTER:
        while (widgetNum != 3) {
            widgetFactoy = new PMWidgetFactory();
            System.out.println("Type 1 for Motif, 2 for Presentation Manager, 3 to exit: ");
            try {
                widgetNum = Integer.parseInt(scanner.next());
                switch (widgetNum) {
                    case 3:
                        break OUTER;
                    case 1:
                        widgetFactoy = new MotifWidgetFactory();
                    case 2:
                        widgetFactoy.createWindow().setTitle("New Window");
                        widgetFactoy.createScrollBar().setDirection();
                        break;
                    default:
                        System.out.println("Invalid choice. Try again");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Try again");
            }
        }
    }
}
