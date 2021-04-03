package com.alok;

import com.alok.data.DataFactory;

import java.io.IOException;

/**
 * The type Main.
 *
 * @author - Ravikant on 03/04/2021 - 13:57
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        DataFactory df = new DataFactory();
        df.generateProducts();
        df.generateReviews();
    }
}
