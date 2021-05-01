public class sprawdzanie {

    public static String[] sprawdzKwadraty(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
//        if (kliknietaKomorka>=0&&kliknietaKomorka<rozmiartablicy||kliknietaKomorka%rozmiartablicy==0||kliknietaKomorka%rozmiartablicy==rozmiartablicy-1)
//    }
        int kwadrat=rozmiartablicy*rozmiartablicy;
        //pierwsza komorka
        if (kliknietaKomorka==0){

        }
        //ostatnia pierwszego wierszu
        else if (kliknietaKomorka==rozmiartablicy-1){

        }
        //pierwsza ostatniego wieszu
        else if (kliknietaKomorka==kwadrat-rozmiartablicy){

        }
        //ostatnia komórka
        else if (kliknietaKomorka==kwadrat){

        }
        //sprawdzenie gornego wierszu
   else if (kliknietaKomorka<rozmiartablicy){

    }
    //sprawdzenie dolnego wierszu
        else if (kliknietaKomorka>=kwadrat-rozmiartablicy){

        }
        //sprawdzenie lewej kolumny
        else if (kliknietaKomorka%rozmiartablicy==0){

        }
        //sprawdzenie lewej kolumny
        else if(kliknietaKomorka%rozmiartablicy==rozmiartablicy-1){

        }
        //wszystkie pozostałe
        else{
            if (goraPrawo(tablicaGry,rozmiartablicy,kliknietaKomorka)&&gora(tablicaGry,rozmiartablicy,kliknietaKomorka)&&prawo(tablicaGry,rozmiartablicy,kliknietaKomorka)){
                tablicaGry[kliknietaKomorka]="C";
            }
        }



        return tablicaGry;
    }


    public static boolean goraLewo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka-rozmiartablicy-1].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean dolPrawo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka+rozmiartablicy+1].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean dolLewo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka+rozmiartablicy-1].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean gora(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka-rozmiartablicy].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean dol(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka+rozmiartablicy].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean prawo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka+1].equals("C")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean lewo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka-1].equals("C")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean goraPrawo(String[] tablicaGry,int rozmiartablicy,int kliknietaKomorka){
        if (tablicaGry[kliknietaKomorka-rozmiartablicy+1].equals("C")){
            return true;
        }else{
            return false;
        }
    }


}
