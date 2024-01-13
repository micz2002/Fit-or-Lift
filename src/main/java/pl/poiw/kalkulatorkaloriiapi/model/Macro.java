package pl.poiw.kalkulatorkaloriiapi.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Macro {
    StringProperty name;
    FloatProperty amount;

    public Macro(String nazwa, Float ilosc) {
        this.name = new SimpleStringProperty(nazwa);
        this.amount = new SimpleFloatProperty(ilosc);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getAmount() {
        return amount.get();
    }

    public void setAmount(float amount) {
        this.amount.set(amount);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public FloatProperty amountProperty(){
        return amount;
    }

}
