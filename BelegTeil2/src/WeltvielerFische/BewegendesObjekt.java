package WeltvielerFische;
public abstract class BewegendesObjekt extends BasisObjekt {
  
   
   public BewegendesObjekt(float xPos, float yPos) {
      super(xPos, yPos);
   }

   public abstract void update();
}
