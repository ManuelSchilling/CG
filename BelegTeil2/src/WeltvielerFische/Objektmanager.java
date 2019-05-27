package WeltvielerFische;

import java.util.HashMap;

import WelteineseinsamenFisches.Fisch;

public class Objektmanager {
   private HashMap<Integer, Fisch> fische;

   // ****************************************************
   // ObjektManager als Singleton realisieren
   private static Objektmanager exemplar = new Objektmanager();

   private Objektmanager() {
	   fische = new HashMap<Integer, Fisch>();
   }

   public static Objektmanager getExemplar() {
      return exemplar;
   }

   public Object clone() throws CloneNotSupportedException {
      throw new CloneNotSupportedException("Clonen ist nicht erlaubt");
   }
   // ***************************************************
   
   public void registriereFisch(Fisch obj) {
	   fische.put(new Integer(obj.id), obj);
   }

   public void entferneFisch(Fisch obj) {
	   fische.remove(obj);
   }
   
   public Fisch getFisch(int objID) {
      return fische.get(new Integer(objID));
   }
   
   public HashMap<Integer, Fisch> getFischMap() {
      return fische;
   }
   
   public int getFischSize() {
      return fische.size();
   }
}