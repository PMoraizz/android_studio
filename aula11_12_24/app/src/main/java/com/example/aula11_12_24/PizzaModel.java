package com.example.aula11_12_24;

public class PizzaModel {
    int pizzaImage;
    String pizzaName;
    String pizzaValue;
    String pizzaIngredients;

    public PizzaModel(int pizzaImage, String pizzaName, String pizzaValue, String pizzaIngredients) {
        this.pizzaImage = pizzaImage;
        this.pizzaName = pizzaName;
        this.pizzaValue = pizzaValue;
        this.pizzaIngredients = pizzaIngredients;
    }

    public int getPizzaImage() {
        return pizzaImage;
    }

    public void setPizzaImage(int pizzaImage) {
        this.pizzaImage = pizzaImage;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaValue() {
        return pizzaValue;
    }

    public void setPizzaValue(String pizzaValue) {
        this.pizzaValue = pizzaValue;
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }


}
