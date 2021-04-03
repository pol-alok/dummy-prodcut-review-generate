package com.alok.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.math.RoundingMode.HALF_UP;

/**
 * The type Product.
 *
 * @author - Ravikant on 03/04/2021 - 11:05
 */
public class Product {

    private static final DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private boolean isFood;
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final int rating;
    private final LocalDate bestBefore;

    /**
     * Instantiates a new Product.
     *
     * @param isFood     the is food
     * @param id         the id
     * @param name       the name
     * @param price      the price
     * @param rating     the rating
     * @param bestBefore the best before
     */
    public Product(boolean isFood, int id, String name, BigDecimal price, int rating, LocalDate bestBefore) {
        this.isFood = isFood;
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.bestBefore = bestBefore;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price.setScale(2, HALF_UP);
    }

    @Override
    public String toString() {
        return ((isFood) ? "F" : "D") + ", " + id + ", " + name + ", " + getPrice() + ", " + rating + ", " + ((bestBefore != null) ? dateFormat.format(bestBefore) : "");
    }
}
