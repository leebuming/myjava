import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        // Ingredient test
        Ingredient rice = new Ingredient("rice", 1.4);
        Ingredient chicken = new Ingredient("chicken", 1.9);
        Ingredient oliveOil = new Ingredient("olive oil", 9.0);
        Ingredient onion = new Ingredient("onion", 0.4);

        System.out.println(rice.kilocaloriesPerGram()); // Prints 1.4

        // Recipe test
        Recipe chickenAndRiceSoup = new Recipe("chicken and rice soup");
        chickenAndRiceSoup.addIngredient(rice, 100);
        chickenAndRiceSoup.addIngredient(chicken, 450);
        chickenAndRiceSoup.addIngredient(oliveOil, 14);
        chickenAndRiceSoup.addIngredient(onion, 150);

        System.out.println(chickenAndRiceSoup.totalKilocalories()); // Prints 1181
    }
}

class Ingredient {
    // Fields
    private final String name;
    private final double kilocaloriesPerGram;

    // Constructor
    public Ingredient(String name, double kilocaloriesPerGram) {
        this.name = name;
        this.kilocaloriesPerGram = kilocaloriesPerGram;
    }

    // Getters
    public String name() {
        return name;
    }

    public double kilocaloriesPerGram() {
        return kilocaloriesPerGram;
    }
}

class Recipe {
    // A small helper class to store (ingredient, grams)
    private static class Entry {
        Ingredient ingredient;
        int grams;

        Entry(Ingredient ingredient, int grams) {
            this.ingredient = ingredient;
            this.grams = grams;
        }
    }

    // Fields
    private final String name;
    private final ArrayList<Entry> entries;

    // Constructor
    public Recipe(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
    }

    // Add an ingredient with grams
    public void addIngredient(Ingredient ingredient, int grams) {
        if (grams < 0) {
            throw new IllegalArgumentException("grams must be non-negative");
        }
        entries.add(new Entry(ingredient, grams));
    }

    // Getter
    public String name() {
        return name;
    }

    // Total kilocalories (rounded to int)
    public int totalKilocalories() {
        double sum = 0.0;
        for (Entry e : entries) {
            sum += e.ingredient.kilocaloriesPerGram() * e.grams;
        }
        return (int) Math.round(sum);
    }
}