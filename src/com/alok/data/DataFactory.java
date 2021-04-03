package com.alok.data;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.ResourceBundle;

import static java.math.RoundingMode.HALF_UP;

/**
 * The type Data factory.
 *
 * @author - Ravikant on 03/04/2021 - 10:49
 */
public class DataFactory {

    private static final ResourceBundle config = ResourceBundle.getBundle("com.alok.config");

    private static final Path productFolder = Paths.get(config.getString("products.folder"));
    private static final Path reviewFolder = Paths.get(config.getString("reviews.folder"));

    private static final String[] foodsName = new String[]{"Apple pie", "Apples raw with peel 2 3/4 diam", "Apricot nectar canned", "Asparagus raw cuts and tips", "Avocado Calif 1/2 lb with refuse", "Bagel plain", "Bamboo shoots canned and sliced", "Banana raw without peel", "Beef - Liver", "Beef - Steak lean and fat", "Beef dried chipped", "Beef gravy canned", "Beer Light (12 fl oz)", "Beer Regular (12 fl oz)", "Beets cooked sliced or diced", "Blackberries raw", "Blackeyed peas frozen drained", "Blueberries raw", "Bouillon", "Bread crumbs dry grated", "Broccoli - Cooked raw spears", "Brussel sprouts cooked raw", "Burrito beef and bean", "Butter - Stick", "Buttermilk", "Canadian style Bacon", "Cantaloupe", "Carbonated Cola (12 fl oz)", "Carbonated Diet Cola (12 fl oz)", "Carrot cake cream cheese", "Carrot juice", "Carrots whole raw", "Cauliflower - Cooked raw", "Celery pascal large stalk", "Cheese - Blue", "Cheese - Cheddar cut pieces", "Cheese - Cream", "Cheese - Monterey Jack", "Cheese - Swiss", "Cheese crackers", "Cheese Gouda", "Cheese Mozzarella whole milk", "Cherries sweet raw without pits", "Chicken - Roasted whole", "Chicken breast fried w/batter", "Chili powder", "Chocolate Chip cookies commercial", "Clams - raw", "Coffee Brewed", "Coffee Instant", "Cole slaw", "Corn Chips", "Corn cooked raw on cob", "Corn dog", "Cottage Creamed small curd", "Cream sour cultured", "Croissant 4 1/2 x 4 x 1 3/4 in", "Cucumber with peel", "Custard baked", "Dandelion greens (raw)", "Dates whole without pits", "Duck meat only roasted", "Egg Nog commercial", "Egg noodles cooked", "Eggplant cooked", "Enchilada", "English muffin plain", "English walnuts chopped", "Fishsticks", "Frankfurter beef and pork", "Frankfurter turkey", "French fries fried in veg oil", "French fries oven heated", "Gin Rum Vodka Whiskey 80 proof", "Gingerbread", "Graham crackers", "Grape Drink Canned", "Grape Nuts cereal", "Ground beef lean", "Ground beef regular", "Ham luncheon meat canned", "Honeydew", "Ice Cream Vanilla", "Ice Milk Vanilla", "Kefir", "Lamb - Chops lean and fat", "Lamb leg roasted lean and fat", "Lard", "Lemonade concentrate", "Lettuce Butterhead/Boston", "Lima Beans thick seeded", "Lucky Charms cereal", "Macaroni cooked firm stage hot", "Mango raw edible part", "Margarine - Regular hard", "Margarine Spread (60% fat) hard", "Marshmallows", "Melba toast plain", "Milk - 2% Low Fat", "Milk - Whole", "Mixed grain toasted", "Muffins Blueberry commercial", "Mushrooms raw sliced", "Nectarines raw without pits", "Oil - Corn", "Oil - Olive", "Oil - Peanut", "Onions raw chopped", "Orange (12 fl oz)", "Orange juice", "Oysters - raw Pacific", "Oysters Eastern raw", "Peach pie piece", "Peaches juice pack", "Peaches raw whole", "Peanut Butter", "Pears raw Bartlett", "Peas green canned drained", "Pepper black", "Pickles dill medium", "Pine nuts/pinyon dried", "Pineapple raw chunks dices", "Pineapple juice canned", "Pinto Beans cooked from dry", "Plantains cooked boiledsliced", "Plums", "Popcorn air popped plain", "Pork - Chops Broiled lean & fat", "Pork Bacon medium slices", "Pork rib roasted lean and fat", "Potato chips", "Potatoes mashed", "Pretzels thin sticks", "Prune juice bottled or canned", "Pudding canned Chocolate", "Raisin bread slice", "Raisins seedless", "Raw white", "Raw whole without shell", "Raw Yolk", "Rhubarb cooked added sugar", "Rice brown cooked", "Root Beer (12 fl oz)", "Rye wafers whole grain", "Rye sliced", "Safflower", "Salad Dressing - Blue Cheese", "Salad Dressing - French Regular", "Salad Dressing - Italian Regular", "Salad Dressing 1000 Island", "Salad Dressing Mayonnaise", "Salmon - Broiled or baked", "Salmon canned pink solids", "Saltine crackers", "Scrambled with milk and butter", "Sherbert (2% fat)", "Skim Milk", "Snack cakes chocolate", "Soybean", "Spaghetti in sauce w/cheese", "Spaghetti cooked firm stage hot", "Spinach raw chopped", "Strawberries thawed measure", "Tea Brewed", "Tomatoes raw whole", "Trix cereal", "Trout broiled w/butter & lemon", "Tuna oil packed", "Turkey - Roasted whole slices", "Veal cutlet braised or broiled", "Vegetable juice cocktail canned", "Water chestnuts canned sliced", "Wheat bread sliced", "Wheat cracker thin", "White bread sliced", "White rice raw dry", "Yellow cake", "Yogurt lowfat fruit added", "Yogurt whole milk"};

    private static final String[] reviews = new String[]{"Very bad", "Very small - expected more", "Could have been better", "I like it", "This is the best one I ever had"};

    private static int SEQ = 101;

    private boolean isPrime(long num) {
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;
        return true;
    }

    private BigDecimal getRandomPrice() {
        BigDecimal max = new BigDecimal(30);
        BigDecimal randFromDouble = BigDecimal.valueOf(Math.random());
        return randFromDouble.multiply(max).setScale(2, HALF_UP);
    }

    private LocalDate getRandomDate(Random rd) {
        LocalDate start = LocalDate.now();
        long days = ChronoUnit.DAYS.between(start, LocalDate.of(2021, Month.MAY, 1));
        if (!isPrime(days)) {
            return start.plusDays(rd.nextInt((int) days + 1));
        } else {
            return null;
        }
    }

    private Product buildProduct() {
        Random rd = new Random();
        boolean isF = rd.nextBoolean();
        String name = foodsName[rd.nextInt(foodsName.length)];
        BigDecimal price = getRandomPrice();
        int rating = rd.nextInt(5 - 1 + 1) + 1;
        LocalDate bestBefore = getRandomDate(rd);
        return new Product(isF, SEQ++, name, price, rating, bestBefore);
    }

    private Review buildReview() {
        Random rd = new Random();
        int rating = rd.nextInt(4);
        String txt = reviews[rating];
        return new Review(rating, txt);
    }

    /**
     * Generate reviews.
     *
     * @throws IOException the io exception
     */
    public void generateReviews() throws IOException {
        SEQ = 101;
        for (int i = 0; i < 62; i++) {
            Path productFile = reviewFolder.resolve(MessageFormat.format(config.getString("review.file"), SEQ++));
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(Files.newOutputStream(productFile, StandardOpenOption.CREATE), StandardCharsets.UTF_8))) {
                Random rd = new Random();
                for (int j = 0; j < (rd.nextInt(5 - 1 + 1) + 1); j++) {
                    Review review = buildReview();
                    out.append(review.toString()).append(System.lineSeparator());
                }
            }
        }
    }

    /**
     * Generate products.
     *
     * @throws IOException the io exception
     */
    public void generateProducts() throws IOException {
        for (int i = 0; i <62;i++) {
            Product product = buildProduct();
            Path productFile = productFolder.resolve(MessageFormat.format(config.getString("product.file"), product.getId()));
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(Files.newOutputStream(productFile, StandardOpenOption.CREATE), StandardCharsets.UTF_8))) {
                out.append(product.toString()).append(System.lineSeparator());
            }
        }
    }
}
