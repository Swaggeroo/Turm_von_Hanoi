import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startGUI {
    private JTextField plattenField;
    private JTextField hoeheField;
    private JTextField delayField;
    private JTextField breiteField;
    private JButton startButton;
    private JButton cancelButton;
    private JPanel jPannel;
    private static JFrame gui;

    public startGUI() {

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setVisible(false);
                int platten = Integer.parseInt(plattenField.getText());
                int hoehe = Integer.parseInt(hoeheField.getText());
                int breite = Integer.parseInt(breiteField.getText());
                int delay = Integer.parseInt(delayField.getText());
                Thread thread = new Thread(()->{
                    System.out.println("start");
                    Turme turme = new Turme(platten,delay,hoehe,breite);
                });
                thread.start();
            }
        });
    }

    public static void main(String[] args) {
        gui = new JFrame("Turm von Hanoi");
        gui.setContentPane(new startGUI().jPannel);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}
