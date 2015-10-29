package test.java.mx.iteso;

import main.java.mx.iteso.ChocolateBoiler;
import main.java.mx.iteso.boiler.Boiler1;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Arreola on 10/29/2015.
 */
public class BoilerTest {
    public ChocolateBoiler boiler1;

    @Before
    public void setUp() {
        //Asegurarnos de trabajar con un Boiler nuevo
        boiler1 = Boiler1.getUniqueInstance();
        boiler1.boiled = false;
        boiler1.empty = true;
    }

    @Test
    public void fillTest() {
        boiler1.fill();
        assertEquals(false, boiler1.isEmpty());
        assertEquals(false, boiler1.isBoiled());
    }

    @Test
    public void drainTest() {
        boiler1.empty = false;
        boiler1.boiled = true;
        boiler1.drain();
        assertEquals(true, boiler1.isEmpty());
    }

    @Test
    public void boilTest() {
        boiler1.empty = false;
        boiler1.boiled = false;
        boiler1.boil();
        assertEquals(true, boiler1.isBoiled());
    }

    @Test
    public void isEmptyTest () {
        assertEquals(true, boiler1.isEmpty());
    }

    @Test
    public void isBoiled() {
        assertEquals(false, boiler1.isBoiled());
    }

    //Test del multi-thread

    public class Thread implements Runnable {
        @Override
        public void run() {
            boiler1 = Boiler1.getUniqueInstance();
            if(boiler1.isEmpty()) {
                boiler1.fill();
            }else if(!boiler1.isBoiled()) {
                boiler1.boil();
            }else if(boiler1.isBoiled()) {
                boiler1.drain();
            }
        }
    }

    @Test
    public void multithreadTest(){
        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread thread3 = new Thread();

        thread1.run();
        thread2.run();
        thread3.run();

        assertEquals(true, boiler1.isEmpty());
    }

}
