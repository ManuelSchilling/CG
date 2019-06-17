package WeltdesSchwarmes;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.opengl.Display;

import WelteineseinsamenFisches.LWJGLBasisFenster;
import math.Vektor2D;

public class AgentSystem extends LWJGLBasisFenster {
	private ObjektManager agentenSpielwiese;
	private double runningAverageFrameTime = 1 / 60, avgRatio = 0.75;
	private long last = System.nanoTime();
	
	final private static int WIDTH=2000, HEIGHT=1000; 

	
	final private static int ANZAHL=50; 
	final static int ABSTAND=10; 
	
	
	public AgentSystem() throws IOException {
		super("Fischschwarm", WIDTH, HEIGHT);
		initDisplay();
		agentenSpielwiese = ObjektManager.getExemplar();
		erzeugeAgenten(ANZAHL);
	}

	private void erzeugeAgenten(int anz) {
		Random rand = ThreadLocalRandom.current();
int[] ix = new int[ANZAHL], iy=new int[ANZAHL];
		for (int i = 0; i < anz; i++) {
		    ix[i]=rand.nextInt(WIDTH);
		    iy[i]=rand.nextInt(HEIGHT);
		    
		    for(int n = 0; n<i; n++) {
		    if (Math.abs(ix[n]-ix[i])<=ABSTAND && Math.abs(iy[n]-iy[i])<=ABSTAND)
			 ix[i]=ix[i]-ABSTAND*2;	iy[i]=iy[i]-ABSTAND*2;
			
	
		    }
			Agent agent = new Agent(
					new Vektor2D(ix[i], iy[i]),
					new Vektor2D(rand.nextFloat()*20, 0), 10, rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			agent.setVerhalten(new VerhaltenAgent(agent));
			//
				agent.setMass(agent.MASS);
				agent.setRadius(10);
			//	agent.setMass(agent.getRadius() / 10);
				
				
			//
			agent.setObjektManager(agentenSpielwiese);
			agentenSpielwiese.registrierePartikel(agent);
		}
	}

	public int getCurrFPS() {
		return (int) (1 / runningAverageFrameTime);
	}

	@Override
	public void renderLoop() {
		glEnable(GL_DEPTH_TEST);

		while (!Display.isCloseRequested()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			long now = System.nanoTime();
			double diff = (now - last) / 1e9;
			runningAverageFrameTime = avgRatio * runningAverageFrameTime + (1 - avgRatio) * diff;
			last = now;

			glClearColor(0.1f, 0.2f, 0.3f, 1);
			glClear(GL_COLOR_BUFFER_BIT);

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, WIDTH, HEIGHT, 0, 0, 1);
			glMatrixMode(GL_MODELVIEW);
			glDisable(GL_DEPTH_TEST);

			for (int i = 1; i <= agentenSpielwiese.getAgentSize(); i++) {
				Agent aktAgent = agentenSpielwiese.getAgent(i);
								
				aktAgent.render();
				aktAgent.update(diff);
				//
				
				//Display.setTitle("Debug:" + aktAgent.getAcceleration() +" "+ aktAgent.getAccelerationInRespectToMass());
				
				//
			}
			Display.update();
		}
	}

	public static void main(String[] args) throws IOException {
		new AgentSystem().start();
	}

	@Override
	protected ByteBuffer createBuffer(BufferedImage read) {
	    // TODO Auto-generated method stub
	    return null;
	}
}
