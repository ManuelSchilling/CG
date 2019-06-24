package WeltdesSchwarmes;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

//

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

//

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import math.Vektor2D;

public class AgentSystem extends LWJGLBasisFenster {
    private ObjektManager agentenSpielwiese;
    private double runningAverageFrameTime = 1 / 60, avgRatio = 0.75;
    private long last = System.nanoTime();

    final private static int WIDTH = 1920, HEIGHT = 900;

    //ShaderCode
    private static String vertexShaderSource = ""

	    + "void main() {" + " gl_FrontColor = gl_Color;"
	    + "   gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;" + "}";

    private static String fragShaderSource = ""

	    + "void main() { " + "gl_FragColor = gl_Color;" + "}";

    public AgentSystem() {
	super("Die Welt der bunten Fische", WIDTH, HEIGHT);
	try {
	    initDisplay();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	agentenSpielwiese = ObjektManager.getExemplar();
	erzeugeAgenten(50);
    }

    private void erzeugeAgenten(int anz) {
	Random rand = ThreadLocalRandom.current();

	for (int i = 0; i < anz; i++) {
	    Agent agent = new Agent(new Vektor2D(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)),
		    new Vektor2D(rand.nextFloat() * 20, 0), 10, rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	    agent.setVerhalten(new VerhaltenAgent(agent));
	    
	    agent.setMass(agent.MASS);
	    //konstanten oder variablen Radius festlegen
	    agent.setRadius(10);
	    agent.setObjektManager(agentenSpielwiese);
	    agentenSpielwiese.registrierePartikel(agent);
	}
    }

    public int getCurrFPS() {
	return (int) (1 / runningAverageFrameTime);
    }

    //

    private static ByteBuffer infoBuffer = BufferUtils.createByteBuffer(1024);
    private static IntBuffer errorBuffer = BufferUtils.createIntBuffer(1);

    public static void testShaderProgram(int shader) {
	errorBuffer.rewind();
	glGetProgram(shader, GL_LINK_STATUS, errorBuffer);
	System.out.println(errorBuffer.get(0) == GL_TRUE ? "OK" : "ERROR");
	int error = errorBuffer.get(0);
	errorBuffer.put(0, 1024);
	glGetProgramInfoLog(shader, errorBuffer, infoBuffer);
	if (error != GL_TRUE) {
	    byte bytes[] = new byte[1024];
	    infoBuffer.get(bytes).rewind();
	    System.err.println(new String(bytes, 0, errorBuffer.get(0)));
	}
    }

    private void prepareShader() {
	int myProgram = glCreateProgram();

	int vertShader = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vertShader, vertexShaderSource);
	glCompileShader(vertShader);
	System.out.println(glGetShaderInfoLog(vertShader, 1024));
	glAttachShader(myProgram, vertShader);

	int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fragShader, fragShaderSource);
	glCompileShader(fragShader);
	System.out.println(glGetShaderInfoLog(fragShader, 1024));
	glAttachShader(myProgram, fragShader);

	glLinkProgram(myProgram);
	glUseProgram(myProgram);

	testShaderProgram(myProgram);
    }

    //
    @Override
    public void renderLoop() {
	
	prepareShader(); //Shader an/aus
	
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

	    }
	    Display.update();
	}
    }

    public static void main(String[] args) {
	new AgentSystem().start();
    }

    @Override
    protected ByteBuffer createBuffer(BufferedImage read) {
	return null;
    }
}
