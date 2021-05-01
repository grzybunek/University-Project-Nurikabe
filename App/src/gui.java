import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import static java.awt.GridBagConstraints.*;

public class gui{
    public int rozmiarTablicy;
    public JButton[] przyciskiWyboru = new JButton[6];
    public String[] tablicaGry;
    public String[] rozwiazanaTablicaGry;
    public JButton[] przyciskiDoGry;
    public String wybor="";
    public JButton[] przyciskiLiczb=new JButton[10];
    public int wybranaLiczba=1;
    public String wczytywanyPlik="";


    void initize(){
        //Okno programu
        JFrame program=new JFrame("Nurikabe");
     //   program.setVisible(true);    //przeniesione na koniec metody
        program.setLayout(new BorderLayout());
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        program.setSize(400,400);



        //menu glowne
        GridBagConstraints c = new GridBagConstraints();
        JPanel menuGlowne = new JPanel(new GridBagLayout());
        menuGlowne.setSize(400,400);
        menuGlowne.setVisible(true);
        program.add(menuGlowne,BorderLayout.CENTER);


        //menu wyboru rozmiaru
        JPanel menuWyboruRozmiaru = new JPanel(new GridBagLayout());
        menuWyboruRozmiaru.setSize(400,400);
        menuWyboruRozmiaru.setVisible(false);
        program.add(menuWyboruRozmiaru, BorderLayout.CENTER);

        //panel gry
        JPanel panelGry = new JPanel(new GridBagLayout());
//        panelGry.setSize(400,400);
        panelGry.setVisible(false);
        program.add(panelGry,BorderLayout.CENTER);

        //panel wyboru liczb
        JPanel panelDolny=new JPanel(new GridBagLayout());
       // panelDolny.setSize(400,400);
        panelDolny.setVisible(false);
        program.add(panelDolny, BorderLayout.SOUTH);

        //przyciski do liczb z panelu liczb
        String[] liczby={"1","2","3","4","5","6","7","8","9","10"};
        c.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < liczby.length; i++) {
            przyciskiLiczb[i] = new JButton(liczby[i].toString());
            przyciskiLiczb[i].setVisible(true);
            przyciskiLiczb[i].setFocusPainted(false);
            c.gridx=i;
            przyciskiLiczb[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for (int a=0;a<przyciskiLiczb.length;a++){
                        if (e.getSource()==przyciskiLiczb[a]){
                           wybranaLiczba=a+1;
                        }
                    }
                }
            });;
        }



        //label wybierz liczbe do generatora
        JLabel wybierzLiczbeDoWstawienia= new JLabel("Wybierz Liczbe Do Wstawienia");
        c.fill = GridBagConstraints.HORIZONTAL;

        //przyciski do wyboru koloru
        JButton przyciskBiały=new JButton(" ");
        JButton przyciskCzarny=new JButton(" ");
        przyciskCzarny.setBackground(Color.BLACK);
        c.gridx=4;
        c.gridy=1;
        c.gridx=5;
        przyciskBiały.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
           wybranaLiczba=0;
            }
        });
        przyciskCzarny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                wybranaLiczba=-1;
            }
        });

        //przyciski do panelu dolnego podczas gry
        JButton przyciskZapisuGry=new JButton("Zapisz gre");
        przyciskZapisuGry.setVisible(true);
        przyciskZapisuGry.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //tutaj nastapi zapisanie tablicy do pliku
                zapis.zapisywanieTablicyDoPliku(tablicaGry,"zapisanaGra.txt");
                JOptionPane.showMessageDialog(program,"Zapisano gre!");
            }
        });
        JButton przyciskRozwiazania=new JButton("Pokaz rozwiazanie");
        przyciskRozwiazania.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //tutaj pokaze sie rowiazana tablica
                try{
                    rozwiazanaTablicaGry = wczytywanie.read(wczytywanyPlik, wybranaLiczba)[1];
                    tablicaGry=rozwiazanaTablicaGry;
                    zmienieniePrzyciskowDoGry();
                    panelGry.repaint();
                } catch (Exception e3){
                    JOptionPane.showMessageDialog(program, "Błąd działania", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        przyciskRozwiazania.setVisible(true);

        JButton przyciskNastepnejTablicy=new JButton("Nastepna tablica");
        przyciskNastepnejTablicy.setVisible(true);
        przyciskNastepnejTablicy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (wybranaLiczba<10){
                    wybranaLiczba++;
                    wczytanieTablicy();
                    zmienieniePrzyciskowDoGry();
                    panelGry.repaint();

                }else{
                    wybranaLiczba=0;
                    JOptionPane.showMessageDialog(program,"Nie mamy wiecej tablic tego rozmiaru :(");
                }

            }
        });
        JButton przyciskZapisuTablicy=new JButton("Zapisz tablice");
        przyciskZapisuTablicy.setVisible(true);
        przyciskZapisuTablicy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser fc=new JFileChooser();
                int i=fc.showSaveDialog(program);
                if(i==JFileChooser.APPROVE_OPTION){
                    zapis.zapisywanieTablicyDoPliku(tablicaGry,fc.getCurrentDirectory().toString()+"\\"+fc.getSelectedFile().getName()+".txt");
                }
                }
        });
        //przycisk drukowania tablicy
        JButton przyciskDrukowaniaTablicy=new JButton("Eksportuj jako png");
        przyciskDrukowaniaTablicy.setVisible(true);
        przyciskDrukowaniaTablicy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser fc=new JFileChooser();
                int i=fc.showSaveDialog(program);
                if(i==JFileChooser.APPROVE_OPTION){
                    new drukowaniePlanszy(tablicaGry,rozmiarTablicy,fc.getCurrentDirectory().toString()+"\\"+fc.getSelectedFile().getName());
                }
            }
        });
        JButton przyciskSprawdzania=new JButton("Sprawdzenie");
        przyciskSprawdzania.setVisible(true);
        przyciskSprawdzania.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    rozwiazanaTablicaGry = wczytywanie.read(wczytywanyPlik, wybranaLiczba)[1];
                }catch(Exception e4){}

                if (Arrays.equals(tablicaGry,rozwiazanaTablicaGry)){
                    JOptionPane.showMessageDialog(program, "Rowiązałeś tablice!", "Brawo!", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(program, "Tablica nie jest poprawnie rozwiązana", "Błąd", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });



        //przycisk powrotu do menu
        JButton przyciskPowrot=new JButton("Powrót do menu głównego");
        program.add(przyciskPowrot, BorderLayout.NORTH);
        przyciskPowrot.setVisible(false);
        przyciskPowrot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelGry.setVisible(false);
                menuWyboruRozmiaru.setVisible(false);
                menuGlowne.setVisible(true);
                przyciskPowrot.setVisible(false);
                program.setSize(400,400);
                panelGry.removeAll();
                panelDolny.setVisible(false);
                panelDolny.removeAll();
                wybor="";
                wczytywanyPlik="";
            }
        });

        //przycisk start
        JButton przyciskStart= new JButton("Start nowej gry");
        przyciskStart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                przyciskPowrot.setVisible(true);
                menuGlowne.setVisible(false);
                menuWyboruRozmiaru.setVisible(true);
                //przyciskPowrot.setVisible(true);
                wybranaLiczba=1;
                if (!wybor.equals("wczytywanie")){
                    wybor="gra";
                }
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=1;
        menuGlowne.add(przyciskStart,c);

        //separator
        JSeparator separator1=new JSeparator();
        JSeparator separator2=new JSeparator();
        JSeparator separator3=new JSeparator();
        c.gridx=1;
        c.gridy=2;
        c.ipady=20;
        menuGlowne.add(separator1, c);
        c.gridy=4;
        menuGlowne.add(separator2, c);
        c.gridy=6;
        menuGlowne.add(separator3, c);
        c.ipady=0;

        //przycisk wczytaj ostatni zapis gry
        JButton przyciskWczytajZapis= new JButton("Wczytaj ostatni zapis gry");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        menuGlowne.add(przyciskWczytajZapis,c);
        przyciskWczytajZapis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    String wczytanie;
                    if (wczytywanyPlik.equals("")) {
                        tablicaGry = wczytywanie.read("zapisanaGra.txt", 1)[0];
                    }else{
                        tablicaGry = wczytywanie.read(wczytywanyPlik, 1)[0];
                    }
                    wybor="wczytywanie";
                    String tempRozmiar=wczytywanie.rozmiar(tablicaGry).replace("Rozmiar: ","");
                    przyciskStart.doClick();
                    if (tempRozmiar.equals("5x5")){
                        przyciskiWyboru[0].doClick();
                    } else if (tempRozmiar.equals("7x7")){
                        przyciskiWyboru[1].doClick();
                    }else if (tempRozmiar.equals("10x10")){
                        przyciskiWyboru[2].doClick();
                    }else if (tempRozmiar.equals("12x12")){
                        przyciskiWyboru[3].doClick();
                    }else if (tempRozmiar.equals("15x15")){
                        przyciskiWyboru[4].doClick();
                    }else if (tempRozmiar.equals("20x20")){
                        przyciskiWyboru[5].doClick();
                    }

                } catch (IOException r) {
                    przyciskPowrot.doClick();
                    JOptionPane.showMessageDialog(program, "Nie posiadasz zapisanego stanu gry", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //przycisk wczytaj z pliku
        JButton przyciskWczytajPlik= new JButton("Wczytaj tablice z pliku");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=5;
        menuGlowne.add(przyciskWczytajPlik,c);
        przyciskWczytajPlik.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    int i = fc.showOpenDialog(program);
                    if (i == JFileChooser.APPROVE_OPTION) {
                        wczytywanyPlik = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
                        przyciskWczytajZapis.doClick();
                    }
                } catch(Exception e2){
                    przyciskPowrot.doClick();
                    JOptionPane.showMessageDialog(program, "Podano zły plik", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        //przycisk generuj tablice
        JButton przyciskGeneruj= new JButton("Generuj tablice");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=7;
        menuGlowne.add(przyciskGeneruj,c);
        przyciskGeneruj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menuGlowne.setVisible(false);
                przyciskPowrot.setVisible(true);
                wybor="generowanie";
                wybranaLiczba=0;
                menuWyboruRozmiaru.setVisible(true);
            }
        });



        //label wybierz rozmiar
        JLabel wybierzRozmiar= new JLabel("Wybierz rozmiar tablicy");
        c.fill = GridBagConstraints.HORIZONTAL;
        wybierzRozmiar.setFont(menuWyboruRozmiaru.getFont().deriveFont(20.0f));
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        menuWyboruRozmiaru.add(wybierzRozmiar,c);
        c.gridwidth=1;




        //przyciski wyboru rozmiaru
        String[] wymiary= {"5x5","7x7","10x10","12x12","15x15","20x20"};
        int[] x={0,1,0,1,0,1};
        int[] y={1,1,2,2,3,3};
        for (int i = 0; i < 6; i++) {
            przyciskiWyboru[i] = new JButton(wymiary[i]);
            przyciskiWyboru[i].setFocusPainted(false);
        }
        for (int i = 0; i < przyciskiWyboru.length; i++) {
            c.ipadx=50;
            c.gridx=x[i];
            c.gridy=y[i];
            menuWyboruRozmiaru.add(przyciskiWyboru[i],c);
            c.gridx=0;
            c.gridy=3;
            przyciskiWyboru[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (wybor.equals("generowanie")){
                        c.gridx=3;
                        c.gridy=2;
                        c.gridwidth=5;
                        panelDolny.add(wybierzLiczbeDoWstawienia,c);
                        c.gridwidth=1;
                        c.gridx=4;
                        c.gridy=1;
                        panelDolny.add(przyciskBiały,c);
                        c.gridx=5;
                        panelDolny.add(przyciskCzarny,c);
                        c.gridx=7;
                        c.gridwidth=3;
                        panelDolny.add(przyciskZapisuTablicy,c);
                        c.gridx=0;
                        panelDolny.add(przyciskDrukowaniaTablicy,c);
                        c.gridwidth=1;
                        c.gridy=3;
                        for (int i = 0; i < liczby.length; i++) {
                        c.gridx=i;
                        panelDolny.add(przyciskiLiczb[i],c); }

                        panelDolny.setVisible(true);

                    }else if (wybor.equals("gra")||wybor.equals("wczytywanie")){
                        c.gridx=0;
                        c.gridy=1;
                        panelDolny.add(przyciskZapisuGry, c);
                        c.gridx=1;
                        if (wybor.equals("gra")){
                        panelDolny.add(przyciskRozwiazania,c);
                        c.gridx=2;
                        panelDolny.add(przyciskNastepnejTablicy,c);
                        c.gridx=1;
                        c.gridy=0;
                        panelDolny.add(przyciskSprawdzania,c);
                        c.gridy=1;
                        c.gridx=2;
                        }
                        c.gridx++;
                        panelDolny.add(przyciskDrukowaniaTablicy,c);
                        panelDolny.setVisible(true);

                    }
                    menuWyboruRozmiaru.setVisible(false);

                    if (e.getSource()==przyciskiWyboru[0]){
                        rozmiarTablicy=5;
                        program.setSize(550,550);
                        wczytywanyPlik="5x5.txt";

                    }
                    else if (e.getSource()==przyciskiWyboru[1]){
                        rozmiarTablicy=7;
                        program.setSize(550,550);
                        wczytywanyPlik="7x7.txt";
                    }
                    else if (e.getSource()==przyciskiWyboru[2]){
                        rozmiarTablicy=10;
                        program.setSize(550,550);
                        wczytywanyPlik="10x10.txt";
                    }
                    else if (e.getSource()==przyciskiWyboru[3]){
                        rozmiarTablicy=12;
                        program.setSize(550,550);
                        wczytywanyPlik="12x12.txt";
                    }
                    else if (e.getSource()==przyciskiWyboru[4]){
                        rozmiarTablicy=15;
                        program.setSize(650,650);
                        wczytywanyPlik="15x15.txt";

                    }
                    else if (e.getSource()==przyciskiWyboru[5]){
                        rozmiarTablicy=20;
                        program.setSize(850,850);
                        wczytywanyPlik="20x20.txt";

                    }
                    panelGry.setVisible(true);
                    if (wybor.equals("gra")){
                        wczytanieTablicy();
                    } else if (wybor.equals("generowanie")){
                    stworzTablice();
                    }  else if (wybor.equals("wczytywanie")){
                        wybor="gra";
                    }
                    stworzPrzyciskiDoGry();
                    c.gridx=0;
                    c.gridy=0;
                    int flaga=0;
                            for (int i = 0; i < przyciskiDoGry.length; i++) {
                                String tempTekst=tablicaGry[i];
                                if (wybor.equals("gra")){
                                if (tempTekst.equals("B")){
                                    przyciskiDoGry[i] = new JButton(" ");
                                } else if (tempTekst.equals("C")){
                                    przyciskiDoGry[i] = new JButton(" ");
                                    przyciskiDoGry[i].setBackground(Color.black);
                                } else if (tempTekst.equals("R")){
                                    przyciskiDoGry[i] = new JButton(" ");
                                    przyciskiDoGry[i].setBackground(Color.red);
                                }
                                else {przyciskiDoGry[i] = new JButton(tempTekst);}
                                }else if (wybor.equals("generowanie")){
                                    przyciskiDoGry[i] = new JButton(" ");
                                }
                                //przyciskiDoGry[i] = new JButton(tempTekst);
                                przyciskiDoGry[i].setFocusPainted(false);
                                przyciskiDoGry[i].addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e){
                                        for (int i = 0; i < przyciskiDoGry.length; i++){
                                            if(przyciskiDoGry[i]==e.getSource()){
                                                if (wybor.equals("generowanie")){
                                                    if (wybranaLiczba==0){
                                                        przyciskiDoGry[i].setBackground(null);
                                                        przyciskiDoGry[i].setText(" ");
                                                        tablicaGry[i]="B";
                                                    }else if(wybranaLiczba==-1){
                                                        przyciskiDoGry[i].setBackground(Color.BLACK);
                                                        przyciskiDoGry[i].setText(" ");
                                                        tablicaGry[i]="C";
                                                    }else {
                                                        przyciskiDoGry[i].setBackground(null);
                                                        przyciskiDoGry[i].setText(String.valueOf(wybranaLiczba));
                                                        tablicaGry[i]=String.valueOf(wybranaLiczba);

                                                    }
                                                } else if(wybor.equals("gra")){
                                                    if (tablicaGry[i].equals("B")){
                                                        przyciskiDoGry[i].setBackground(Color.black);
                                                        tablicaGry[i]="C";
                                                        //tutaj jeszcze ma byc sprawdzenie żeby ewentualnie zmienić na czerwony(R)
                                                    }else if (tablicaGry[i].equals("C")||tablicaGry[i].equals("R")){
                                                        przyciskiDoGry[i].setBackground(null);
                                                        tablicaGry[i]="B";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });;
                                panelGry.add(przyciskiDoGry[i],c);
                                c.gridx=c.gridx+1;
                                if (flaga==rozmiarTablicy-1){
                                    c.gridx=0;
                                    c.gridy=c.gridy+1;
                                    flaga=-1;
                                }
                                flaga++;
                             }
                }
            });
        }
        c.ipadx=0;
        program.setVisible(true);
    }

    public void stworzTablice(){
        String[] tablicaGry=new String[rozmiarTablicy*rozmiarTablicy];
        for(int i=0;i<tablicaGry.length;i++){
            tablicaGry[i]="B";
        }
        this.tablicaGry=tablicaGry;
    }

    public void stworzPrzyciskiDoGry(){
        JButton[] panelGry=new JButton[tablicaGry.length];
        this.przyciskiDoGry=panelGry;
    }
    public void wczytanieTablicy(){
        String[][] wczyt;
        try {
            wczyt = wczytywanie.read(wczytywanyPlik, wybranaLiczba);
            tablicaGry=wczyt[0];
            rozwiazanaTablicaGry=wczyt[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void zmienieniePrzyciskowDoGry(){
        for (int i=0;i<tablicaGry.length;i++){
            if (tablicaGry[i].equals("B")){
                przyciskiDoGry[i].setBackground(null);
                przyciskiDoGry[i].setText(" ");
            }
            else if (tablicaGry[i].equals("R")){
                przyciskiDoGry[i].setBackground(Color.red);
                przyciskiDoGry[i].setText(" ");
            } else if (tablicaGry[i].equals("C")) {
                przyciskiDoGry[i].setBackground(Color.black);
                przyciskiDoGry[i].setText(" ");
            } else{
                przyciskiDoGry[i].setBackground(null);
                przyciskiDoGry[i].setText(tablicaGry[i]);

            }
        }
    }
   public gui(){
        initize();
    }

    public static void main(String[] args) {
            new gui();
    }

}
