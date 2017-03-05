package org.orangeflamingo.namesandsongs.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {

    private MainController mainController = new MainController();

    @Test
    public void testDummy() throws Exception {
        assertEquals("Zanger", mainController.dummySong().getArtist());
        assertEquals("Voornaam in titel", mainController.dummySong().getTitle());
    }

}