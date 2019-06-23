package WeltdesSchwarmes;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;

import math.LineareAlgebra;
import math.Vektor2D;
import math.Weg2DDynamisch;

public class RenderFisch {

	private static int i = 0;
	private static float bodyPos = 0;

	private RenderFisch() {
	}

	public static void renderFisch(float x, float y, int off, float winkel, int radius, float r, float g, float b) {

		/* FISCH ANIMATION
		System.out.println("radius: " + radius);
		int fishmove = radius * 100;
		int anteil = fishmove / 4;
		if (i % fishmove <= anteil)
			bodyPos += 0.02;
		else if (i % fishmove <= anteil * 2)
			bodyPos -= 0.02;
		else if (i % fishmove <= anteil * 3)
			bodyPos -= 0.02;
		else if (i % fishmove < fishmove)
			bodyPos += 0.02;
*/

		glLoadIdentity();
		glTranslated(x, y, 0);
		glRotatef(winkel + 90f, 0, 0, 1);
		
	

		/* ALTER TEST CODE
		 * int i = 0; for (int j = 0; j < wegHistorie.getSize() ; j=j+5) { float anteil
		 * = 1-((float)j/wegHistorie.getSize()); glColor4f(r*anteil, g*anteil, b*anteil,
		 * 1f); renderKreis(x-(float)wegHistorie.getElement(j).x, i, 5, radius);
		 * //renderKreis(((float)wegHistorie.getElement(j).x-(float)wegHistorie.
		 * getElement(j+1).x), i+((float)wegHistorie.getElement(j).y
		 * -(float)wegHistorie.getElement(j-1).y), 5, radius);
		 * 
		 * i=i+radius; }
		 * //renderKreis(x-(float)wegHistorie.getElement(0).x,y-(float)wegHistorie.
		 * getElement(0).y,5, radius);
		 * renderKreis(x-(float)wegHistorie.getElement(0).x,y-(float)wegHistorie.
		 * getElement(0).y,5, radius); glColor3d(0.5, 0.5, b); float
		 * dPosX=x-(float)wegHistorie.getElement(0).x; float
		 * dPosY=y-(float)wegHistorie.getElement(0).y;
		 * System.out.println("Kreis1: x="+dPosX+"  y="+dPosY); if(dPosX <0)
		 * dPosX=Math.abs(dPosX);
		 * renderKreis((x-(float)wegHistorie.getElement(1).x)*10,radius+
		 * y-(float)wegHistorie.getElement(0).y,5, radius);
		 * renderKreis((x-(float)wegHistorie.getElement(2).x)*10,radius*2+
		 * y-(float)wegHistorie.getElement(0).y,5, radius);
		 * renderKreis((x-(float)wegHistorie.getElement(3).x)*10,radius*3+
		 * y-(float)wegHistorie.getElement(0).y,5, radius);
		 * //renderKreis(Math.abs(x-(float)wegHistorie.getElement(5).x),y-(float)
		 * wegHistorie.getElement(5).y,5, radius);
		 * dPosX=x-(float)wegHistorie.getElement(5).x;
		 * dPosY=y-(float)wegHistorie.getElement(5).y;
		 * System.out.println("Kreis2: x="+dPosX+"  y="+dPosY);
		 */

		glColor3d(r, g, b);
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(0f, -(radius / 2));
		glVertex2f(radius / 2, -radius - radius / 2);
		glVertex2f(-radius / 2, -radius - radius / 2);
		glEnd();

		glColor3d(r - 0.1f, g - 0.1f, b - 0.1f);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle = 0; angle < 360; angle += 5) {
			glVertex2f(bodyPos + (float) Math.sin(angle) * radius, (float) Math.cos(angle) * radius);
		}
		glEnd();

		glColor3d(r - 0.4f, g - 0.4f, b - 0.4f);
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(0f, radius / 5);
		glVertex2f(bodyPos + radius * 2, bodyPos + radius);
		glVertex2f(bodyPos + radius, bodyPos + radius * 2);
		glEnd();

		glColor3d(r - 0.5, g - 0.5f, b - 0.5);
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(0f, radius / 5);
		glVertex2f(bodyPos - radius * 2, bodyPos + radius);
		glVertex2f(bodyPos - radius, bodyPos + radius * 2);
		glEnd();

		glColor3d(r - 0.2f, g - 0.2f, b - 0.2f);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle = 0; angle < 360; angle += 5) {
			glVertex2f(bodyPos + (float) Math.sin(angle) * (radius - radius / 4),
					radius + (float) Math.cos(angle) * (radius - radius / 4));
		}
		glEnd();

		glColor3d(r - 0.3f, g - 0.3f, b - 0.3f);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle = 0; angle < 360; angle += 5) {
			glVertex2f(bodyPos + (float) Math.sin(angle) * (radius - radius / 3),
					radius * 2 + (float) Math.cos(angle) * (radius - radius / 3));
		}
		glEnd();

		glColor3d(r - 0.4f, g - 0.4f, b - 0.4f);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle = 0; angle < 360; angle += 5) {
			glVertex2f((float) Math.sin(angle) * (radius - radius / 2),
					radius * 3 + (float) Math.cos(angle) * (radius - radius / 2));
		}
		glEnd();

		glColor3d(r - 0.5f, g - 0.5f, b - 0.5f);
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(0f, radius * 3);
		glVertex2f(radius + bodyPos, radius * 5);
		glVertex2f(0f, radius * 4);
		glVertex2f(-radius + bodyPos, radius * 5);
		glEnd();

		i++;
		if (i == 100 * radius)
			i = 0;
	}

	static void renderSwarmObjectWithForces(float x, float y, int radius, Vektor2D velocity, Vektor2D acceleration,
			float r, float g, float b) {

		int off = radius + 1 + (int) (velocity.length() / 5);
		double winkel = LineareAlgebra.angleDegree(velocity, new Vektor2D(1, 0));

		if (velocity.y < 0)
			winkel = 180 + (180 - winkel);

		renderFisch(x, y, off, (float) winkel, radius, r, g, b);

	}

	public static void renderObjectWithPath(float x, float y, float r, float g, float b, float a, int radius,
			Weg2DDynamisch path) {
		for (int j = path.getSize() - 1; j >= 0; j--) {
			float anteil = 1 - ((float) j / path.getSize());
			glColor4f(r * anteil, g * anteil, b * anteil, 1f);
			renderKreis((float) path.getElement(j).x, (float) path.getElement(j).y, 5, radius);
		}

		glColor4f(r, g, b, a);
		renderKreis(x, y, 5, radius);
	}

	public static void renderKreis(float x, float y, float step, float radius) {
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(x, y);
		for (int angle = 0; angle < 360; angle += step)
			glVertex2f(x + (float) Math.sin(angle) * radius, y + (float) Math.cos(angle) * radius);
		glEnd();
	}

}
