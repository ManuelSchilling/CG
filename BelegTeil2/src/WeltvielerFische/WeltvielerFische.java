package WeltvielerFische;

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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.opengl.Display;


import WelteineseinsamenFisches.Fisch;

public class WeltvielerFische extends LWJGLBasisFenster {
	private Objektmanager fische;

	public WeltvielerFische() {
		super("Welt der Flummies", 640, 480);
		initDisplay();
		fische = Objektmanager.getExemplar();
		erzeugeFische(500);
	}

	private void erzeugeFische(int anz) {
		Random rand = ThreadLocalRandom.current();
		for (int i = 0; i < anz; i++) {
			fische.registriereFisch(new Fisch(rand.nextInt(640), rand.nextInt(480),
					rand.nextInt(3) + 1, rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
		}
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

			for (int i = 1; i <= fische.getFischSize(); i++) {
				Fisch aktFisch = fische.getFisch(i);
				aktFisch.render();
				aktFisch.update();
			}

			Display.update();
		}
		Display.destroy();
	}

	public static void main(String[] args) {
		new WeltvielerFische().start();
	}
}
