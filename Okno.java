package com.company;

import com.sun.jdi.Accessible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Okno extends JFrame implements ActionListener {
    JPanel p1,p2;
    JButton bRestart;
    JButton[] pole;
    boolean ruch = false;
    boolean gameOver = false;
    String ktorePole = "";


    Okno(){
        setSize(450,520);
        setTitle("Serwer");
        setLocation(1200,220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        p1 = new JPanel(new GridLayout(3,3));
        p1.setPreferredSize(new Dimension(420,420));

        pole = new JButton[9];
        for(int i = 0; i < 9 ; i++){
            pole[i] = new JButton();
            pole[i].setName(String.valueOf(i));
            pole[i].setText("");
            pole[i].setBackground(Color.lightGray);
            pole[i].setFont(new Font("TimesRoman",Font.BOLD,75));
            pole[i].addActionListener(this);
            p1.add(pole[i]);
        }
        add(p1);

        p2 = new JPanel();
        bRestart = new JButton("Restart");
        bRestart.addActionListener(this);
        p2.add(bRestart);
        add(p2);

    }

    public void restart(){
        for (int i = 0; i<9; i++){
            pole[i].setText("");
            pole[i].setBackground(Color.lightGray);
            pole[i].setEnabled(true);
            gameOver = false;
        }

    }

    public void over(){
        gameOver = true;
        System.out.println("Koniec gry");
        for (int i = 0; i<9; i++){
            pole[i].setEnabled(false);
        }
    }

    public void send (String message) throws IOException, ClassNotFoundException{

        try{
            if (Polacz.oos != null ){
                System.out.println(Polacz.oos);
                Polacz.oos.writeObject(message);
            }
        } catch (IOException e){
            System.out.println("Brak klientow");
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==bRestart) {
            System.out.println("Restart");
            restart();
            try {
                send("restart");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        if (gameOver == false){
            if (((JButton) e.getSource()).getText().equalsIgnoreCase("")){

                if(ruch == true){
                    ((JButton) e.getSource()).getText();
                    ktorePole = ((JButton)e.getSource()).getName();
                    System.out.println(ktorePole);
                    try {
                        send(ktorePole);

                    }  catch (IOException ex){
                    ex.printStackTrace();
                        System.out.println("Uruchom serwer i zrestartuj klienta.");
                } catch (ClassNotFoundException ex){
                    ex.printStackTrace();
                        System.out.println("zle polaczenie");
                }
                ruch =! ruch;
            }
        }
        if(pole[0].getText() == pole[1].getText() && pole[1].getText() == pole[2].getText() && pole[2].getText() != "") {
            pole[0].setBackground(Color.red);
            pole[1].setBackground(Color.red);
            pole[2].setBackground(Color.red);
            over();
        }
        if(pole[3].getText() == pole[4].getText() && pole[4].getText() == pole[5].getText() && pole[5].getText() != "") {
            pole[3].setBackground(Color.red);
            pole[4].setBackground(Color.red);
            pole[5].setBackground(Color.red);
            over();
        }
        if(pole[6].getText() == pole[7].getText() && pole[7].getText() == pole[8].getText() && pole[8].getText() != "") {
            pole[6].setBackground(Color.red);
            pole[7].setBackground(Color.red);
            pole[8].setBackground(Color.red);
            over();
        }
        if(pole[0].getText() == pole[3].getText() && pole[3].getText() == pole[6].getText() && pole[6].getText() != "") {
            pole[0].setBackground(Color.red);
            pole[3].setBackground(Color.red);
            pole[6].setBackground(Color.red);
            over();
        }
        if(pole[1].getText() == pole[4].getText() && pole[4].getText() == pole[7].getText() && pole[7].getText() != "") {
            pole[1].setBackground(Color.red);
            pole[4].setBackground(Color.red);
            pole[7].setBackground(Color.red);
            over();}
        }
        if(pole[2].getText() == pole[5].getText() && pole[5].getText() == pole[8].getText() && pole[8].getText() != "") {
            pole[2].setBackground(Color.red);
            pole[5].setBackground(Color.red);
            pole[8].setBackground(Color.red);
            over();
        }
        if(pole[0].getText() == pole[4].getText() && pole[4].getText() == pole[8].getText() && pole[8].getText() != "") {
            pole[0].setBackground(Color.red);
            pole[4].setBackground(Color.red);
            pole[8].setBackground(Color.red);
            over();
        }
        if(pole[2].getText() == pole[4].getText() && pole[4].getText() == pole[6].getText() && pole[6].getText() != "") {
            pole[2].setBackground(Color.red);
            pole[4].setBackground(Color.red);
            pole[6].setBackground(Color.red);
            over();
        }
    }


}
