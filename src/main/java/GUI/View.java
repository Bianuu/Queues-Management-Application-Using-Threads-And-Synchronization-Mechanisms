package GUI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public JTextField campGhisee;
    public JTextField campClienti;
    public JTextField camptimpSosireMin;
    public JTextField camptimpSosireMax;
    public JTextField camptimpServireMin;
    public JTextField camptimpServireMax;
    public JTextField camptimpSimulareMax;

    public JTextArea zonaDesfasurator;

    JPanel panouContinut;
    JPanel panouStart;
    JPanel panouGhisee;
    JLabel etichetaGhisee;
    JPanel panouClienti;
    JLabel etichetaClienti;
    JPanel panoutimpSosireMin;
    JLabel etichetatimpSosireMin;
    JPanel panoutimpSosireMax;
    JLabel etichetatimpSosireMax;
    JPanel panoutimpServireMin;
    JLabel etichetatimpServireMin;
    JPanel panoutimpServireMax;
    JLabel etichetatimpServireMax;
    JPanel panoutimpSimulareMax;
    JLabel etichetatimpSimulareMax;

    JButton butonStart;
    JPanel panouIesire;
    JPanel panouDesfasurator;
    JLabel etichetaDesfasurator;

    JScrollPane panouScrollDesfasurator;

    Controller controler = new Controller(this);

    public View(String nume) {
        super(nume);
        setSize(1000, 1000);
        this.panouContinut = new JPanel(new GridLayout(2, 1));
        this.setContentPane(panouContinut);


        ///intrari

        panouStart = new JPanel();
        panouStart.setLayout(new GridLayout(4, 2));

        panouGhisee = new JPanel();
        panouGhisee.setLayout(new GridLayout(1, 2));
        etichetaGhisee = new JLabel(" Numarul de Ghisee ");
        panouGhisee.add(etichetaGhisee);
        campGhisee = new JTextField();
        panouGhisee.add(campGhisee);
        panouStart.add(panouGhisee);

        panouClienti = new JPanel();
        panouClienti.setLayout(new GridLayout(1, 2));
        etichetaClienti = new JLabel(" Numarul de Clienti ");
        panouClienti.add(etichetaClienti);
        campClienti = new JTextField();
        panouClienti.add(campClienti);
        panouStart.add(panouClienti);

        panoutimpSosireMin = new JPanel();
        panoutimpSosireMin.setLayout(new GridLayout(1, 2));
        etichetatimpSosireMin = new JLabel(" Timp Sosire(min) ");
        panoutimpSosireMin.add(etichetatimpSosireMin);
        camptimpSosireMin = new JTextField();
        panoutimpSosireMin.add(camptimpSosireMin);
        panouStart.add(panoutimpSosireMin);

        panoutimpSosireMax = new JPanel();
        panoutimpSosireMax.setLayout(new GridLayout(1, 2));
        etichetatimpSosireMax = new JLabel(" Timp Sosire(max) ");
        panoutimpSosireMax.add(etichetatimpSosireMax);
        camptimpSosireMax = new JTextField();
        panoutimpSosireMax.add(camptimpSosireMax);
        panouStart.add(panoutimpSosireMax);

        panoutimpServireMin = new JPanel();
        panoutimpServireMin.setLayout(new GridLayout(1, 2));
        etichetatimpServireMin = new JLabel(" Timp Servire(min) ");
        panoutimpServireMin.add(etichetatimpServireMin);
        camptimpServireMin = new JTextField();
        panoutimpServireMin.add(camptimpServireMin);
        panouStart.add(panoutimpServireMin);

        panoutimpServireMax = new JPanel();
        panoutimpServireMax.setLayout(new GridLayout(1, 2));
        etichetatimpServireMax = new JLabel(" Timp Servire(max) ");
        panoutimpServireMax.add(etichetatimpServireMax);
        camptimpServireMax = new JTextField();
        panoutimpServireMax.add(camptimpServireMax);
        panouStart.add(panoutimpServireMax);

        panoutimpSimulareMax = new JPanel();
        panoutimpSimulareMax.setLayout(new GridLayout(1, 2));
        etichetatimpSimulareMax = new JLabel(" Timp de Simulare(max)");
        panoutimpSimulareMax.add(etichetatimpSimulareMax);
        camptimpSimulareMax = new JTextField();
        panoutimpSimulareMax.add(camptimpSimulareMax);
        panouStart.add(panoutimpSimulareMax);

        butonStart = new JButton("START");
        butonStart.setActionCommand("Start");
        butonStart.addActionListener(controler);
        panouStart.add(butonStart);

        // Date pre-scrise in GUI pentru testare mai rapida
        //T1
        /*campClienti.setText("4");
        campGhisee.setText("2");
        camptimpSimulareMax.setText("60");
        camptimpSosireMin.setText("2");
        camptimpSosireMax.setText("30");
        camptimpServireMin.setText("2");
        camptimpServireMax.setText("4");*/

        //T2
        campClienti.setText("50");
        campGhisee.setText("5");
        camptimpSimulareMax.setText("60");
        camptimpSosireMin.setText("2");
        camptimpSosireMax.setText("40");
        camptimpServireMin.setText("1");
        camptimpServireMax.setText("7");

        //T3
       /* campClienti.setText("1000");
        campGhisee.setText("20");
        camptimpSimulareMax.setText("200");
        camptimpSosireMin.setText("10");
        camptimpSosireMax.setText("100");
        camptimpServireMin.setText("3");
        camptimpServireMax.setText("9");*/

        panouContinut.add(panouStart);


        ///iesiriInit

        panouIesire = new JPanel();
        panouIesire.setLayout(new GridLayout(1, 1));

        panouDesfasurator = new JPanel();
        panouDesfasurator.setLayout(new BoxLayout(panouDesfasurator, BoxLayout.Y_AXIS));

        etichetaDesfasurator = new JLabel(" Desfasurator ");
        panouDesfasurator.add(etichetaDesfasurator);

        zonaDesfasurator = new JTextArea();
        zonaDesfasurator.setEditable(false);
        panouDesfasurator.add(zonaDesfasurator);

        panouScrollDesfasurator = new JScrollPane(zonaDesfasurator);
        panouDesfasurator.add(panouScrollDesfasurator);

        panouIesire.add(panouDesfasurator);
        panouContinut.add(panouIesire);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}