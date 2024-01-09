package pl.poiw.kalkulatorkaloriiapi.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Items
{
    private String sodium_mg;

    private String sugar_g;

    private String fat_total_g;

    private String cholesterol_mg;

    private String protein_g;

    private String name;

    private String fiber_g;

    private String calories;

    private String serving_size_g;

    private String fat_saturated_g;

    private String carbohydrates_total_g;

    private String potassium_mg;

    public String getSodium_mg ()
    {
        return sodium_mg;
    }

    public void setSodium_mg (String sodium_mg)
    {
        this.sodium_mg = sodium_mg;
    }

    public String getSugar_g ()
    {
        return sugar_g;
    }

    public void setSugar_g (String sugar_g)
    {
        this.sugar_g = sugar_g;
    }

    public String getFat_total_g ()
    {
        return fat_total_g;
    }

    public void setFat_total_g (String fat_total_g)
    {
        this.fat_total_g = fat_total_g;
    }

    public String getCholesterol_mg ()
    {
        return cholesterol_mg;
    }

    public void setCholesterol_mg (String cholesterol_mg)
    {
        this.cholesterol_mg = cholesterol_mg;
    }

    public String getProtein_g ()
    {
        return protein_g;
    }

    public void setProtein_g (String protein_g)
    {
        this.protein_g = protein_g;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFiber_g ()
    {
        return fiber_g;
    }

    public void setFiber_g (String fiber_g)
    {
        this.fiber_g = fiber_g;
    }

    public String getCalories ()
    {
        return calories;
    }

    public void setCalories (String calories)
    {
        this.calories = calories;
    }

    public String getServing_size_g ()
    {
        return serving_size_g;
    }

    public void setServing_size_g (String serving_size_g)
    {
        this.serving_size_g = serving_size_g;
    }

    public String getFat_saturated_g ()
    {
        return fat_saturated_g;
    }

    public void setFat_saturated_g (String fat_saturated_g)
    {
        this.fat_saturated_g = fat_saturated_g;
    }

    public String getCarbohydrates_total_g ()
    {
        return carbohydrates_total_g;
    }

    public void setCarbohydrates_total_g (String carbohydrates_total_g)
    {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public String getPotassium_mg ()
    {
        return potassium_mg;
    }

    public void setPotassium_mg (String potassium_mg)
    {
        this.potassium_mg = potassium_mg;
    }

    @Override
    public String toString()
    {
        return "Items [sodium_mg = "+sodium_mg+", sugar_g = "+sugar_g+", fat_total_g = "+fat_total_g+", cholesterol_mg = "+cholesterol_mg+", protein_g = "+protein_g+", name = "+name+", fiber_g = "+fiber_g+", calories = "+calories+", serving_size_g = "+serving_size_g+", fat_saturated_g = "+fat_saturated_g+", carbohydrates_total_g = "+carbohydrates_total_g+", potassium_mg = "+potassium_mg+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(sodium_mg, items.sodium_mg) && Objects.equals(sugar_g, items.sugar_g) && Objects.equals(fat_total_g, items.fat_total_g) && Objects.equals(cholesterol_mg, items.cholesterol_mg) && Objects.equals(protein_g, items.protein_g) && Objects.equals(name, items.name) && Objects.equals(fiber_g, items.fiber_g) && Objects.equals(calories, items.calories) && Objects.equals(serving_size_g, items.serving_size_g) && Objects.equals(fat_saturated_g, items.fat_saturated_g) && Objects.equals(carbohydrates_total_g, items.carbohydrates_total_g) && Objects.equals(potassium_mg, items.potassium_mg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sodium_mg, sugar_g, fat_total_g, cholesterol_mg, protein_g, name, fiber_g, calories, serving_size_g, fat_saturated_g, carbohydrates_total_g, potassium_mg);
    }
}
