package outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {

    static private KitC kitC;

    @BeforeEach
    void setUp(){
        kitC = new KitC();
    }

    @Test
    void creerEnvironement() {
        kitC.creerEnvironnement();
        File f = new File("/tmp/twisk");
        assertTrue(f.exists() && f.isDirectory());
        f = new File("/tmp/twisk/programmeC.o");
        assertTrue(f.exists() && !f.isDirectory());
        f = new File("/tmp/twisk/def.h");
        assertTrue(f.exists() && !f.isDirectory());
        f = new File("/tmp/twisk/codeNatif.o");
        assertTrue(f.exists() && !f.isDirectory());

    }

    @Test
    void creerFichier() {
        kitC.creerEnvironnement();
        kitC.creerFichier("Test");
        File f = new File("/tmp/twisk/client.c");
        assertTrue(f.exists() && !f.isDirectory());

    }

    @Test
    void compiler() {
        kitC.creerEnvironnement();
        kitC.creerFichier("//Test;");
        kitC.compiler();
        File f = new File("/tmp/twisk/client.o");
        assertTrue(f.exists() && !f.isDirectory());

    }

    @Test
    void creer() {
        kitC.creerEnvironnement();
        kitC.creerFichier("//Test;");
        kitC.compiler();
        kitC.construireLaLibrairie();
        File f = new File("/tmp/twisk/libTwisk.so");
        assertTrue(f.exists() && !f.isDirectory());

    }
}