package WeltdesSchwarmes;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

/*
 * Beschreibung:
 * Als Erweiterung kommt hinzu, dass wir ein Canvas-Objekt zum zeichnen
 * übergeben können. Dazu müssen wir den init-Teil selbst aktiv im Konstruktor
 * ausführen und start() auf den renderLoop() und das Abmelden reduzieren.
 */
public abstract class LWJGLBasisFenster {
    private int WIDTH, HEIGHT;
    private String TITLE;

    public LWJGLBasisFenster() {
	this("BasisFenster", 640, 480);
    }

    public LWJGLBasisFenster(int width, int height) {
	this("BasisFenster", width, height);
    }

    public LWJGLBasisFenster(String title, int width, int height) {
	WIDTH = width;
	HEIGHT = height;
	TITLE = title;
    }

    public int getWidth() {
	return WIDTH;
    }

    public int getHeight() {
	return HEIGHT;
    }

    public void initDisplay(Canvas c) throws IOException {
	try {
	    Display.setParent(c);
	} catch (LWJGLException e) {
	    e.printStackTrace();
	}

	initDisplay();
    }

    public void initDisplay() throws IOException {
	try {
	    Display.setIcon(new ByteBuffer[] {
		    new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("src/Images/icon16.png")), false,
			    false, null),
		    new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("src/Images/icon32.png")), false,
			    false, null) });
	    Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
	    Display.setTitle(TITLE);
	    Display.create();

	} catch (LWJGLException e) {
	    e.printStackTrace();
	}
    }

    protected abstract ByteBuffer createBuffer(BufferedImage read);

    public abstract void renderLoop();

    public void start() {
	renderLoop();

	Display.destroy();
	System.exit(0);
    }
}
