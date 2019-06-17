package WelteineseinsamenFisches;

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

import org.lwjgl.opengl.Display;


public class  WeltEinesEinsamesFisches extends LWJGLBasisFenster {
	private Fisch fisch;

	public WeltEinesEinsamesFisches() throws IOException {
		super("Welt eines einsamen Fisches", 640, 480);
		initDisplay();
		fisch = new Fisch(320, 240);
	}

	@Override
	public void renderLoop() {
		glEnable(GL_DEPTH_TEST);

		while (!Display.isCloseRequested()) {
			glClearColor(0.1f, 0.2f, 0.3f, 1);
			glClear(GL_COLOR_BUFFER_BIT);
			// ist ja 2d
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, 640, 480, 0, 0, 1);
			glMatrixMode(GL_MODELVIEW);
			glDisable(GL_DEPTH_TEST);

			// einen Flummi anzeigen
			fisch.render();

			// Physik updaten
			fisch.update();

			Display.update();
		}
		Display.destroy();
	}

	public static void main(String[] args) {
		try {
		    new WeltEinesEinsamesFisches().start();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	@Override
	protected ByteBuffer createBuffer(BufferedImage read) {
	    // TODO Auto-generated method stub
	    return null;
	}
}