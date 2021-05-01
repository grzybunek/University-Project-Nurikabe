import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class wczytywanie {

    static String[][] read(String nazwa, int liczba) throws IOException {
        File file = new File(nazwa);
        String p = file.getAbsolutePath();
        Path path = Paths.get(p);



        FileReader fr=new FileReader(String.valueOf(path));
        BufferedReader br=new BufferedReader(fr);
        String str= null;
        String[] z = null;
        String[] r = null;
        int cnt=0;

        while((str=br.readLine())!=null)
        {
            if(str.contains("Zadanie"+String.valueOf(liczba)+":"))
            {   str = str.replace("Zadanie"+String.valueOf(liczba)+":","");
                z= str.split(",");

            }else if(str.contains("Rozwiazanie"+String.valueOf(liczba)+":"))
            {
                str=str.replace("Rozwiazanie"+String.valueOf(liczba)+":", "");
                r=str.split(",");
            }else if (str.contains("Plansza użytkownika:")){
            str = str.replace("Plansza użytkownika:","");
            z = str.split(",");
            r = str.split(",");}
        }
        String[][] arrays = new String[][] {z,r};
        return arrays;
    }

    static String rozmiar(String[] tablica) {
        double bok = Math.sqrt(tablica.length);
        if ((bok== Math.floor(bok)) && !Double.isInfinite(bok)) {
            int val = (int) bok;
            String rozmiar = "Rozmiar: " + String.valueOf(val) + "x" + String.valueOf(val);
            return rozmiar;
        }
        else{
            return "Błąd";
        }
    }

}