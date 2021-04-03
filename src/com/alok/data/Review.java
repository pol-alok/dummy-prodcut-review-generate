package com.alok.data;

/**
 * The type Review.
 *
 * @author - Ravikant on 03/04/2021 - 13:48
 */
public class Review {
    private final int rating;
    private final String txt;

    /**
     * Instantiates a new Review.
     *
     * @param rating the rating
     * @param txt    the txt
     */
    public Review(int rating, String txt) {
        this.rating = rating;
        this.txt = txt;
    }

    @Override
    public String toString() {
        return rating+", "+txt;
    }
}
