import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class zapis {

    public static void zapisywanieTablicyDoPliku(String[] tablica, String plik) {
      //  File plikZapisu=new File("plik");
//        try{
//            String temp="";
//            if (!plik.contains(".")){
//               String[] nplik= plik.split(".");
//                temp=nplik[1];
//            }
        try{
        PrintWriter plikZapisu = new PrintWriter(plik);
        String tablicaDoZapisu= Arrays.toString(tablica).replace("[","").replace("]","").replace(" ","");
        plikZapisu.println("Plansza użytkownika:"+tablicaDoZapisu);
        plikZapisu.close();
        }catch (Exception e){}
    }

    public static void main(String[] args) {
        String B="B";
        String[] tablica={B,String.valueOf(1), B, B, B, B, B, B, String.valueOf(1), B, B, B, String.valueOf(3), B, String.valueOf(4), B, B, B, B, B, B, B, B, B, B};
        zapisywanieTablicyDoPliku(tablica,"plikzapisu");
    }

}
