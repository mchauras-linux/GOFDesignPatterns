package com.creational.factory;

import java.util.ArrayList;
// concrete Creator
class ToyCreator
{
	private static ArrayList<String> dollBrands = new ArrayList<String>();
	private static ArrayList<String> superHeroBrands = new ArrayList<String>();
	private static ArrayList<String> carBrands = new ArrayList<String>();
	

	static {
		dollBrands.add("Barbie");
		dollBrands.add("Geanie");
		dollBrands.add("Macy");
		dollBrands.add("Rosy");
		superHeroBrands.add("Superman");
		superHeroBrands.add("Spiderman");
		superHeroBrands.add("HeMan");
		superHeroBrands.add("Terminator");
		carBrands.add("Ferrari");
		carBrands.add("Volkswagen");
		carBrands.add("Convertible");
		carBrands.add("ArmyJeep");
	}
	public static ToyMould createToy(String name, int size)
	{
		if (dollBrands.contains(name)) {
			return new DollMould(name, size);
		} else if (superHeroBrands.contains(name)) {
			return new DollMould(name, size);
		} else if (carBrands.contains(name)) {
			return new DollMould(name, size);
		} else {
			return null;
		}
	}
	
	
}

class Client {
	public static void main(String[] args) {
		
		ArrayList<ToyMould> toyBasket = new ArrayList<ToyMould>(); 
		toyBasket.add(ToyCreator.createToy("Barbie", 2));
		toyBasket.add(ToyCreator.createToy("Geanie", 3));
		toyBasket.add(ToyCreator.createToy("Superman", 4));
		toyBasket.add(ToyCreator.createToy("Ferrari", 1));
		toyBasket.add(ToyCreator.createToy("ArmyJeep", 2)); 
		for (ToyMould toy : toyBasket) {
			System.out.println("Selected toy is "+toy.getModel() + " : Size - "+ toy.getSize()+ " : Price : "+ toy.getPrice());
		}
			
	}
}
// Abstract Product
public abstract class ToyMould {
	protected double price;
	protected String model;
	protected int size;
	
	
	public double getPrice() {
		return price;
	}

	public String getModel() {
		return model;
	}

	public int getSize() {
		return size;
	}

	public abstract void inject();

}

// Concrete product
class DollMould extends ToyMould {

	public DollMould(String model, int size) {
		super.model = model;
		super.size = size;
		super.price = size * 45.5;
	}

	@Override
	public void inject() {
		System.out.println("Creates a doll - " + model);

	}
}

// Concrete product
class CarMould extends ToyMould {
	public CarMould(String model, int size) {
		super.model = model;
		super.size = size;
		super.price = size * 32.5;
	}

	@Override
	public void inject() {
		System.out.println("Creates a Monster Toy - " + model);

	}
}

// Concrete product
class SuperHeroMould extends ToyMould {

	public SuperHeroMould(String model, int size) {
		super.model = model;
		super.size = size;
		super.price = size * 49.5;
	}

	@Override
	public void inject() {
		System.out.println("Creates a Superhero Toy - " + model);

	}
}


