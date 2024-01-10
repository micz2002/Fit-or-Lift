package pl.poiw.kalkulatorkaloriiapi.model.meal;

public class MealDB {
    private static Meal meal;
    public static Meal getMeal() {
        return meal;
    }

    public static void setMeal(Meal meal) {
        MealDB.meal = meal;
    }
}
