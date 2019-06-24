package WeltdesSchwarmes;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;

import math.LineareAlgebra;
import math.Vektor2D;

public class RenderFisch {

    private static int i = 0;
    private static float bodyPos = 0;

    private RenderFisch() {
    }

    public static void renderFisch(float x, float y, int off, float winkel, int radius, float r, float g, float b) {

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

	glLoadIdentity();
	glTranslated(x, y, 0);
	glRotatef(winkel + 90f, 0, 0, 1);

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
}
