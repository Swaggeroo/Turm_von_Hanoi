import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Turme {
    int platten = 3;
    ArrayList<ArrayList<Integer>> turm = new ArrayList<>();
    double zuege = 0;
    Canvas canvas;
    int waitTime = 0;
    Color[] farben;
    int hoheCan;
    int breiteCan;

    public Turme(int platten,int waitTime,int hoheCan, int breiteCan) {
        System.out.println("Turm ini");
        this.platten = platten;
        this.waitTime = waitTime;
        this.hoheCan = hoheCan;
        this.breiteCan = breiteCan;
        farben = new Color[platten];
        Random rand = new Random();
        for (int i = 0; i<platten;i++){
            farben[i] = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        }
        for (int i = 0; i<3; i++){
            turm.add(new ArrayList<>());
        }
        for (int i = platten; i>0; i--){
            turm.get(0).add(i);
        }
        System.out.println("Turm ini Abgeschlossen");
        System.out.println(turm);
        canvas = new Canvas("Türme von Hanoi",breiteCan,hoheCan, Color.WHITE);
        zeichne();
        turmBau(platten,0,1,2);
    }

    private void turmBau(int anzahl, int von, int durch, int nach){
        if (anzahl>0) {
            turmBau(anzahl-1,von,nach,durch);
            bewegen(von,nach);
            turmBau(anzahl-1,durch,von,nach);
        }
    }

    private void bewegen(int von, int nach){
        int zahl = turm.get(von).get(turm.get(von).size()-1);
        turm.get(von).remove(turm.get(von).size()-1);
        turm.get(nach).add(zahl);
        zuege++;
        System.out.println(zuege+": "+turm);
        try {
            Thread.sleep(waitTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        zeichne();
    }

    private void zeichne(){
        canvas.erase();
        int hoehe = hoheCan/platten;
        int maxBreite = breiteCan/3;
        int breiteProZahl = maxBreite/platten;
        for (int i = 0; i < 3; i++){
            for (int u = 0; u<turm.get(i).size();u++){
                int plattenNummer = turm.get(i).get(u);
                int breite = breiteProZahl*plattenNummer;
                int x = maxBreite/2+maxBreite*i-breite/2;
                int y = hoheCan-hoehe*u;
                canvas.setForegroundColor(farben[plattenNummer-1]);
                canvas.fillRectangle(x,y-hoehe,breite,hoehe);
            }
        }
        canvas.drawString(zuege+"",0,10);
    }


}
