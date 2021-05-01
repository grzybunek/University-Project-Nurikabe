import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class drukowaniePlanszy {
    public drukowaniePlanszy(String[] tablicaGry, int rozmiarPlanszy,String plikZapisu){
        int rozCalegoObrazu= rozmiarPlanszy*50;


        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(rozCalegoObrazu, rozCalegoObrazu, BufferedImage.TYPE_BYTE_GRAY);
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, rozCalegoObrazu+1, rozCalegoObrazu+1);
        int x=0;
        int y=0;
        g2d.setColor(Color.black);
        //ustawienie fontu
        Font stringFont = new Font( "SansSerif", Font.PLAIN, 20 );
        g2d.setFont(stringFont);
        for(String znak:tablicaGry){
            if ((znak.equals("B"))||(znak.equals("C"))||(znak.equals("R"))){
                g2d.drawRect(x, y, 50, 50);
            }else{

                g2d.drawRect(x, y, 50, 50);
                g2d.drawString(znak,x+19, y+32);
            }
            if (x<rozCalegoObrazu-50){
                x=x+50;
            }else{
                x=0;
                y=y+50;
            }
            g2d.drawLine(rozCalegoObrazu-1,0,rozCalegoObrazu-1,rozCalegoObrazu-1);
            g2d.drawLine(0,rozCalegoObrazu-1,rozCalegoObrazu-1,rozCalegoObrazu-1);

        }


        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File(plikZapisu+".png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        }catch (Exception e){}



    }

    public static void main(String[] args) {
        String[] wczyt=new String[0];
        try {
            wczyt  = wczytywanie.read("5x5.txt",1)[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        new drukowaniePlanszy(wczyt,25,"sa");
        System.out.println(Arrays.toString(wczyt));
        System.out.println(wczyt.length);
    }

}
