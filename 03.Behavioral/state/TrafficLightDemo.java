package com.jp.behavioral.state;

public class TrafficLightDemo {
	public static void main(String[] args) {
		TrafficLight light = new TrafficLight();

		// Normally we would want a constructor doing this but I did it
		// that way in other post so this is just another way.
		light.setState(new RedLight());

		// Now we just change it a bunch and report:
		for (int count = 0; count < 9; count++) {

			light.reportState();
			light.change();
		}

	}
}

class TrafficLight {

	private ITrafficLightState state;

	public void change() {
		state.change(this);
	}

	public void reportState() {
		state.reportState();
	}
	
	public ITrafficLightState getState() {
		return state;
	}

	public void setState(ITrafficLightState state) {
		this.state = state;
	}
	

	
}

interface ITrafficLightState {
	public static final RedLight redLight = new RedLight();
	public static final YellowLight yellowLight = new YellowLight();
	public static final GreenLight greenLight = new GreenLight();
	// It takes the existing light and changes it.
	void change(TrafficLight light);
	// We'll display the color it's on.
	void reportState();
}

class GreenLight implements ITrafficLightState {
	public void change(TrafficLight light) {
		light.setState(yellowLight);
	}
	public void reportState() {
		System.out.println("Green Light");
	}
}

class YellowLight implements ITrafficLightState {
	public void change(TrafficLight light) {
		System.out.println("Capture view on camera just before changing state to Red");
		light.setState(redLight);
	}
	public void reportState() {
		System.out.println("Yellow Light");
	}
}

class RedLight implements ITrafficLightState {
	public void change(TrafficLight light) {
		light.setState(greenLight);
	}
	public void reportState() {
		System.out.println("Red Light");
	}
}
