		package WeltdesSchwarmes;

import java.util.Random;

import math.Vektor2D;
import math.Weg2DDynamisch;

public class Agent extends BewegendesObjekt {
	private static int objCounter = 0;
	public ObjektManager objektManager;
	public float r,g,b;
	public Agent(Vektor2D position, Vektor2D velocity, int radius, float r, float g, float b) {
		super(position, velocity);
		this.id = ++objCounter;

		setMass(1);
		setMaxSpeed(150);
		setMaxTurnRate(20);
		setSwarmDistanz(120);
		this.r=r;
		this.g=g;
		this.b=b;
		setWegHistorie(new Weg2DDynamisch(20));
	}
	public void setObjektManager(ObjektManager objektManager) {
		this.objektManager = objektManager;
	}
	@Override
	public void render(){
		RenderFisch.renderSwarmObjectWithForces((float) position.x, (float) position.y, (int)RADIUS, velocity, getLastAcceleration(),this.r, this.g, this.b);
	}
}
