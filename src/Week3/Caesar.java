package Week3;

import java.util.Scanner;

public class Caesar {
     static char encryptChar (char p, int key) {
         int result = (int)p + (key % 26);
         if (result > 'z')
             result = 'a' + (result - 'z') - 1;
         return (char) result;
     }
     
     public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         int key = scan.nextInt();
         String text = scan.next();
         char[] encryptedText = new char[text.length()];
         int i = 0;
         for (char c : text.toCharArray()) {
             encryptedText[i] = encryptChar(c, key);
             i++;
         }
         System.out.println(encryptedText);
     }
}
