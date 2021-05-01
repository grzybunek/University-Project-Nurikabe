import java.io.IOException;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {

        //System.out.println(wczytywanie.read("test.csv"));
        //String wczyt = wczytywanie.read("test.csv");
        //String[] ar = wczyt.split(",");

        String[] wczyt = new String[0];
        String[] roz = new String[0];
        try {
            wczyt = wczytywanie.read("test.txt",1)[0];
            roz=wczytywanie.read("test.txt",1)[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(wczyt));
        System.out.println(Arrays.toString(roz));
        System.out.println(wczytywanie.rozmiar(wczyt));


        //tu se testuje żeby mi tworzyło obiekt za każdego x i za każdą cyferkę w liście.

        //String[] lista = new String[5];

        Cell[] Cells = new Cell[5];

        String[] lista = {"x","x","5","x","x"};

        for (int i = 0; i < lista.length; i++) {

            if (lista[i] == "x") {

                Cells[i] = new Cell(false, 0);

            } else {

                Cells[i] = new Cell( true, Integer.parseInt(lista[i]));

            }

        }

        System.out.println(Arrays.toString(Cells));

        System.out.println(Cells[0].getValue());
        System.out.println(Cells[2].getValue());

/**
        int n = 10;               // liczba komórek (rozmiarTablicy)
        Cell[] Cells = new Cell[n];
        for(int i=0; i<n; i++) {
            Cells[i] = new Cell(false, 99);
            System.out.println(Cells[i].getValue());
            Cells[i].setFixed(true);
            System.out.println(Cells[i].isFixed());
            Cells[i].setValue(7);
            System.out.println(Cells[i].getValue());
            Cells[i].setFixed(false);
            System.out.println(Cells[i].isFixed());
            Cells[i].setValue(5);
            System.out.println(Cells[i].getValue());
            System.out.println(Cells[i].isFixed());
            Cells[i].setValue(5);
            System.out.println(Cells[i].getValue());
            Cells[i].setValue(87);
            System.out.println(Cells[i].getValue());

        }

 */

/**
        Cell[] Cells = new Cell[10];

        for (int i = 0; i < Cells.length; i++) {

            System.out.println("Kolejna komórka to: " + lista[i]);

            //if (Cells[i] == "x") {

                Cells[i] = new Cell(false, 99);

            }

        }
*/

    }
}
