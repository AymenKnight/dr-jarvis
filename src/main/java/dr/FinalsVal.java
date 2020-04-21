package dr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import DataClass.Drug;
import DataClass.Patient;
import libs.coronaDb.coCollection;
import libs.coronaDb.coronaDb;
import libs.requestFormer;
final public class FinalsVal {
       static public final SynchronousQueue<coCollection<Drug>> respondD = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Drug>> requestD = new SynchronousQueue<>();
       static public final SynchronousQueue<coCollection<Patient>> respondP = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Patient>> requestP = new SynchronousQueue<>();

       static public final SynchronousQueue<List<Drug>> respondDL = new SynchronousQueue<>();
       static public final SynchronousQueue<List<Patient>> respondPL = new SynchronousQueue<>();

       static public final SynchronousQueue<Object> respondObj = new SynchronousQueue<>();
       static public final requestFormer<Patient> formerP=new requestFormer<>();
       static public final requestFormer<Drug> formerD=new requestFormer<>();

       static public final coronaDb database=new coronaDb("norme");

       public static boolean isNumeric(String strNum) {
              if (strNum == null) {
                     return false;
              }
              try {
                     double d = Double.parseDouble(strNum);
              } catch (NumberFormatException nfe) {
                     return false;
              }
              return true;
       }
       public static String removeSpace(String str) {
              String result = "";
              for (int i = 0; i < str.length(); i++){
                     char c = str.charAt(i);
                     if(c!=' ') {
                            result += c;
                     }
              }
              return result;
       }

}
