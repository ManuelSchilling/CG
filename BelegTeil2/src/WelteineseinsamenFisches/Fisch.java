package WelteineseinsamenFisches;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Fisch extends BewegendesObjekt {

	private static int objCounter = 0;
	private float radius;
	private float r, g, b;
	private float speed=0.2f;
	public Fisch() {
		this(0, 0, 20.0f, 1f, 1f, 0f);
	}

	public Fisch(float xPos, float yPos) {
	    this(xPos, yPos, 20.0f, 1, 1, 0);
	}

	public Fisch(float xPos, float yPos, float radius, float r, float g, float b) {
      super(xPos, yPos);
      this.radius = radius;
      this.r=r;
      this.g=g;
      this.b=b;
      this.id = ++objCounter;
   }
	@Override
	public void update() {
		
		 yPos=yPos+speed;

	    if (yPos>480 || yPos<0)
	       speed = speed * -1;
	}
	@Override
	public void render() {
		glColor3d(r, g, b);
		glBegin(GL_TRIANGLE_FAN);
		glVertex2f(xPos,yPos-radius/2);
		glVertex2f(xPos+radius/2,yPos-radius-radius/2);
		glVertex2f(xPos-radius/2,yPos-radius-radius/2);
		glEnd();
		glColor3d(r, g-0.1f, b);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle=0; angle<360; angle+=5) {
	       glVertex2f(xPos + (float)Math.sin(angle) * radius, yPos + (float)Math.cos(angle) * radius);
	    }
	    glEnd();
	    glColor3d(r, g-0.4f, b);
		glBegin(GL_TRIANGLE_FAN);
	    glVertex2f(xPos,yPos+radius/5);
	    glVertex2f(xPos+radius*2,yPos+radius);
	    glVertex2f(xPos+radius,yPos+radius*2);
	    glEnd();
	    glColor3d(r, g-0.4f, b);
	    glBegin(GL_TRIANGLE_FAN);
	    glVertex2f(xPos,yPos+radius/5);
	    glVertex2f(xPos-radius*2,yPos+radius);
	    glVertex2f(xPos-radius,yPos+radius*2);
	    glEnd();
	    glColor3d(r, g-0.2f, b);
	    glBegin(GL_TRIANGLE_FAN);
	    for (int angle=0; angle<360; angle+=5) {
			glVertex2f(xPos + (float)Math.sin(angle) * (radius-radius/4), yPos+radius + (float)Math.cos(angle) * (radius-radius/4));
			}
	    glEnd();
	    glColor3d(r, g-0.4f, b);
	    glBegin(GL_TRIANGLE_FAN);
		for (int angle=0; angle<360; angle+=5) {
			glVertex2f(xPos + (float)Math.sin(angle) * (radius-radius/3), yPos+radius*2 + (float)Math.cos(angle) * (radius-radius/3));
		}
		glEnd();
		glColor3d(r, g-0.6f, b);
		glBegin(GL_TRIANGLE_FAN);
		for (int angle=0; angle<360; angle+=5) {
			glVertex2f(xPos + (float)Math.sin(angle) * (radius-radius/2), yPos+radius*3 + (float)Math.cos(angle) * (radius-radius/2));
		}
		glEnd();
		glColor3d(r, g-0.8f, b);
	    glBegin(GL_TRIANGLE_FAN);
	    glVertex2f(xPos,yPos+radius*3);
	    glVertex2f(xPos+radius,yPos+radius*5);
	    glVertex2f(xPos,yPos+radius*4);
	    glVertex2f(xPos-radius,yPos+radius*5);
	    glEnd();
	   
	}
}