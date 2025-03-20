import java.util.Random;
import java.util.Scanner;

public class Utils {
    public boolean isValid(String input,int sizeOfBoard) {

        if (input.length() >3 || input.length() < 2) {
            return false;
        }
        char col = input.charAt(0);
      String row = input.substring(1);
       if(col < 'A' || col >  ('A' + sizeOfBoard)) {
           return false;
       }
     int row1 = Integer.parseInt(row);
       if(row1 < 1 || row1 > sizeOfBoard) {
           return false;
       }
       return true;


    }






        }









