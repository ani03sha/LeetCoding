package org.redquark.leetcoding.design;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DesignAFoodRatingSystem {

    static class FoodRatings {

        // Map to store food item and its rating
        private final Map<String, Integer> foodToRatingMappings;
        // Map to store food with their cuisines
        private final Map<String, String> foodToCuisineMappings;
        // Map to store cuisine and its food items, sorted on ratings/names
        private final Map<String, TreeSet<AbstractMap.SimpleEntry<Integer, String>>> cuisineToFoodMappings;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            this.foodToRatingMappings = new HashMap<>();
            this.foodToCuisineMappings = new HashMap<>();
            this.cuisineToFoodMappings = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                this.foodToRatingMappings.put(foods[i], ratings[i]);
                this.foodToCuisineMappings.put(foods[i], cuisines[i]);
                this.cuisineToFoodMappings.computeIfAbsent(cuisines[i], _ -> new TreeSet<>((a, b) -> {
                    // Compare ratings first
                    if (Objects.equals(a.getKey(), b.getKey())) {
                        // If ratings are same, compare names
                        return a.getValue().compareTo(b.getValue());
                    } else {
                        // Otherwise, compare ratings
                        return Integer.compare(b.getKey(), a.getKey());
                    }
                })).add(new AbstractMap.SimpleEntry<>(ratings[i], foods[i]));
            }
        }

        public void changeRating(String food, int newRating) {
            // Fetch the cuisine name for the food item
            final String cuisine = this.foodToCuisineMappings.get(food);
            // Remove the food item from the cuisine mapping
            this.cuisineToFoodMappings.get(cuisine)
                    .remove(new AbstractMap.SimpleEntry<>(this.foodToRatingMappings.get(food), food));
            // Update the food item rating
            this.foodToRatingMappings.put(food, newRating);
            // Add the food item back to the cuisine mapping with new rating
            this.cuisineToFoodMappings.get(cuisine)
                    .add(new AbstractMap.SimpleEntry<>(newRating, food));
        }

        public String highestRated(String cuisine) {
            // Fetch the food items for the given cuisine
            TreeSet<AbstractMap.SimpleEntry<Integer, String>> foodItems = this.cuisineToFoodMappings.get(cuisine);
            // If there are no food items for the given cuisine, return null
            if (foodItems == null || foodItems.isEmpty()) {
                return null;
            }
            // Return the highest rated food item
            return foodItems.first().getValue();
        }
    }

    public static void main(String[] args) {
        final FoodRatings foodRatings = new FoodRatings(
                new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"},
                new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"},
                new int[]{9, 12, 8, 15, 14, 7}
        );
        System.out.println(foodRatings.highestRated("korean")); // return "kimchi"
        // "kimchi" is the highest rated korean food with a rating of 9.
        System.out.println(foodRatings.highestRated("japanese")); // return "ramen"
        // "ramen" is the highest rated japanese food with a rating of 14.
        foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
        System.out.println(foodRatings.highestRated("japanese")); // return "sushi"
        // "sushi" is the highest rated japanese food with a rating of 16.
        foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
        System.out.println(foodRatings.highestRated("japanese")); // return "ramen"
    }
}
