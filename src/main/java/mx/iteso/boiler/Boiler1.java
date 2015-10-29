package main.java.mx.iteso.boiler;

import main.java.mx.iteso.ChocolateBoiler;

/**
 * Created by Arreola on 10/29/2015.
 */
public class Boiler1 extends ChocolateBoiler{

    private static ChocolateBoiler uniqueInstance = new Boiler1();

    private Boiler1() {
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getUniqueInstance() {
        return uniqueInstance;
    }

}
