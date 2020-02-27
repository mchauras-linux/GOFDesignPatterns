package com.creational.builder;

import java.util.ArrayList;

class Client
{
	public static void main(String[] args) {
		ChildMealDirector mealdirector = new ChildMealDirector();
		MealBox childMeal = mealdirector.buildChildSpecialMeal();
		System.out.println("Child meal price - "+ 
				mealdirector.calculatePrice(childMeal));		
	}
}

/**
 * DIRECTOR
 */
class ChildMealDirector {	

	public MealBox buildChildSpecialMeal() {
		MealBox mealBox = new MealBox();
		// Build the meal taking help from the builder objects
		Item[] items = { new VegBurgerBuilder(), new FriesBuilder(), 
				new ColaBuilder(), new DollBuilder() };
		for (Item item : items) {
			mealBox.add(item);
		}
		return mealBox;
	}

	public static int calculatePrice(MealBox mealBox) {
		int totalPrice = 0;
		
		for (Item item : mealBox) {
			totalPrice += item.price();
		}		
		return totalPrice;
	}
		
}
// BUILDER interface
interface Item {

	/**
	 * build is the method, as every item will be built and packed in a different way.
	 * E.g.:- The burger will be packed as wrapped in a paper The cold drink
	 * will be given in a glass The  fries will be packed in a card box
	 * and The toy will be put in the bag straight. The class Packing is an
	 * interface for different types of packing for different Items.
	 */
	public Packing build();

	/**
	 * price is the method as all the items burger, cold drink, fries will have
	 * a price. The toy will not have any direct price, it will be given free
	 * with the meal.
	 * 
	 * The total price of the meal will be the combined price of the three
	 * items.
	 * 
	 * @return price, int in rupees.
	 */
	public int price();

}
//Restaurant crew who builds Burger
abstract class BurgerBuilder implements Item {

	public abstract int price();

}
// Restaurant crew who builds VegBurger
class VegBurgerBuilder extends BurgerBuilder {
	public Packing build() {
		return new Wrapper();
	}
	public int price() {
		return 39;
	}
}
// Crew who creates and packs fries into envelop
class FriesBuilder implements Item {
	public Packing build() {	
		return new Envelop();
	}
	public int price() {
		return 25;
	}
}
//Crew who fills up Cola 
class ColaBuilder implements Item {
	public Packing build() {	
		return new CoveredGlass();
	}
	public int price() {
		return 20;
	}
}
//concrete builder
class DollBuilder implements Item {

	public Packing build() {
		return new CoveredGlass();
	}

	public int price() {
		return 5;
	}
}
//Product
interface Packing {

}
//Product
class Envelop implements Packing {

}
//Product
class Wrapper implements Packing {

}
//Product
class CoveredGlass implements Packing {

}
//Product
class MealBox extends ArrayList<Item> implements Packing {

}