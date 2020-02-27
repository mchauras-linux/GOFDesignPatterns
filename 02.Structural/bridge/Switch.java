package com.jpm.structural.bridge;

class SwitchClient{
	public static void main(String[] args) {
		EuropeanSwitch switch1 = new EuropeanSwitch();
		switch1.setEquipment(new TV());
		EuropeanSwitch switch2 = new EuropeanSwitch();
		switch2.setEquipment(new Fan());
		
		switch1.switchOn();
		switch2.switchOff();
		switch1.switchOn();
		switch2.switchOff();		
	}
}

public interface Switch {	
	// Two positions of switch.
	public void switchOn();
	public void switchOff(); 
	public void setEquipment(IEquipment equipment);
}

class EuropeanSwitch implements Switch{	
	private  IEquipment equipment; 	
	@Override
	public void switchOff() {
		System.out.println("Switch has been switched OFF");
		// when the upper part of the switch is pressed 		
		equipment.stop();
	}
	@Override
	public void switchOn() {
		System.out.println("Switch has been switched ON");
		// when the lower part of the switch is pressed	
		equipment.start();
	}
	@Override
	public void setEquipment(IEquipment equipment) {
		this.equipment = equipment; 		
	}		
}

class AmericanSwitch implements Switch{
	private  IEquipment equipment; 
	@Override
	public void switchOn() {
		System.out.println("Switch has been switched ON");
		// when the upper part of the switch is pressed 	
		equipment.start();
	}
	@Override
	public void switchOff() {
		System.out.println("Switch has been switched OFF");
		// when the lower part of the switch is pressed
		equipment.stop();
	}		
	@Override
	public void setEquipment(IEquipment equipment) {
		this.equipment = equipment; 		
	}
}
// implementor
 interface IEquipment {	
	public void start();
	public void stop();
}
//concrete implementor
class Bulb implements IEquipment {	
	@Override	
	public void stop() {
	System.out.println("BULB Switched OFF");
	}
	@Override	
	public void start() {
	System.out.println("BULB Switched ON");
	} 

}
//concrete implementor
class Fan implements IEquipment {
	@Override	
	public void stop() {
	System.out.println("FAN Switched OFF");
	}
	@Override	
	public void start() {
	System.out.println("FAN Switched ON");
	} 
}
// concrete implementor
class TV implements IEquipment {	
	@Override	
	public void stop() {
	System.out.println("TV Switched OFF");
	}
	@Override	
	public void start() {
	System.out.println("TV Switched ON");
	} 

}
/*
Output
Switch has been switched ON
TV Switched ON
Switch has been switched OFF
FAN Switched OFF
Switch has been switched ON
TV Switched ON
Switch has been switched OFF
FAN Switched OFF
*/