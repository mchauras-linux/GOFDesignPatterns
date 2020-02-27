package com.creational.abstractfactory;

//Factory creator - an indirect way of instantiating the factories
class CerealFactoryMaker{
	private static CerealAbstractFactory pf=null;
	static CerealAbstractFactory getFactory(String choice){
		if(choice.equals("Kelloggs")){
			pf = new KelloggsConcreteFactory();
		}else if(choice.equals("Quaker")){
				pf = new QuakerConcreteFactory();
			} return pf;
	}
}

//Client
public class TestCereal {
	
	public static void main(String[] args) {
		CerealAbstractFactory pf= CerealFactoryMaker.getFactory("Kelloggs");
		Cereal kellogsCereal =pf.createCereal();
		NutritionBar kellogsNB = pf.createNutritionBar();
	
		CerealAbstractFactory qpf= CerealFactoryMaker.getFactory("Quaker");
		Cereal quakerCereal =qpf.createCereal();
		NutritionBar quakerNB = qpf.createNutritionBar();
	}
}

// Abstract Product A
abstract class NutritionBar{
	public abstract void getBarIngredients();
	public abstract void getNutritionValuePerServing();
}
// Abstract Product B
abstract class Cereal{
	public abstract void getCerealIngredients();

}

//ConcreteProductA
class KelloggsCereal extends Cereal{	
	public void getCerealIngredients() { }; 
}

//ConcreteProductA
class QuakerCereal extends Cereal{	
	public void getCerealIngredients() { };
}
//ConcreteProductB
class KelloggsNutritionBar extends NutritionBar{	
	public void getBarIngredients() { };
	public void getNutritionValuePerServing() { };
}
//ConcreteProductB
class QuakerNutritionBar extends NutritionBar{	
	public void getBarIngredients() { };
	public void getNutritionValuePerServing() { };
}

// Abstract Factory
abstract class CerealAbstractFactory{
	abstract Cereal createCereal();
	abstract NutritionBar createNutritionBar();
}
// Concrete Factory 1
class KelloggsConcreteFactory extends CerealAbstractFactory{
	Cereal createCereal(){
		return new KelloggsCereal();
	}
	NutritionBar createNutritionBar(){
		return new KelloggsNutritionBar();
	}
}
//Concrete Factory 2
class QuakerConcreteFactory extends CerealAbstractFactory{
	Cereal createCereal(){
		return new QuakerCereal();
	}
	NutritionBar createNutritionBar(){
		return new QuakerNutritionBar();
	}
}




 

