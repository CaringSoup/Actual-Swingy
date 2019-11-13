package game_state;

import javax.swing.*;
import java.awt.*;
import java.io.*;

@SwingContainer
public class UI {

        JFrame window;
        JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, textPanel;
        JLabel titleNameLabel, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel;
        JButton startButton, loadButton, choice1, choice2, choice3, choice4, choice5, choice6;
        JTextArea uName;
        JTextArea mainTextArea;
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 85);
        Font normalFont = new Font("Times New Roman", Font.PLAIN, 23);

        public void createUI(Game.ChoiceHandler cHandler){

            // WINDOW
            window = new JFrame();
            window.setSize(900, 750);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().setBackground(Color.black);
            window.setLayout(null);

            // TITLE SCREEN
            titleNamePanel = new JPanel();
            titleNamePanel.setBounds(window.getWidth()/2 - 300, 100, 600, 500);
            titleNamePanel.setBackground(Color.black);
            titleNameLabel = new JLabel("War Stone");
            titleNameLabel.setForeground(Color.white);
            titleNameLabel.setFont(titleFont);
            titleNamePanel.add(titleNameLabel);

            textPanel = new JPanel();
            textPanel.setBounds(window.getWidth()/2 - 300, 350, 600, 40);
            textPanel.setBackground(Color.white);
            uName = new JTextArea();
            uName.setBackground(Color.white);
            uName.setText("Please give name");
            textPanel.add(uName);

            startButtonPanel = new JPanel();
            startButtonPanel.setBounds(window.getWidth()/2 - 100, 400, 200, 200);
            startButtonPanel.setBackground(Color.black);
            startButton = new JButton("START");
            startButton.setBackground(Color.black);
            startButton.setForeground(Color.black);
            startButton.setFont(normalFont);
            startButton.setFocusPainted(false);
            startButton.addActionListener(cHandler);
            startButton.setActionCommand("start");
            startButtonPanel.add(startButton);

            loadButton = new JButton("LOAD");
            loadButton.setForeground(Color.black);
            loadButton.setFont(normalFont);
            loadButton.setFocusPainted(false);
            loadButton.addActionListener(cHandler);
            loadButton.setActionCommand("load");
            startButtonPanel.add(loadButton);

            window.add(startButtonPanel);
            window.add(textPanel);
            window.add(titleNamePanel);


            // GAME SCREEN
            mainTextPanel = new JPanel();
            mainTextPanel.setBounds(window.getWidth()/2 - 300, 100, 600, 250);
            mainTextPanel.setBackground(Color.black);
            window.add(mainTextPanel);

            mainTextArea = new JTextArea("This is the main text area");
            mainTextArea.setBounds(window.getWidth()/2 - 300, 100, 600, 250);
            mainTextArea.setBackground(Color.black);
            mainTextArea.setForeground(Color.white);
            mainTextArea.setFont(normalFont);
            mainTextArea.setLineWrap(true);
            mainTextArea.setWrapStyleWord(true);
            mainTextArea.setEditable(false);
            mainTextPanel.add(mainTextArea);

            choiceButtonPanel = new JPanel();
            choiceButtonPanel.setBounds(window.getWidth()/2 - 150, 350, 300, 150);
            choiceButtonPanel.setBackground(Color.black);
            choiceButtonPanel.setLayout(new GridLayout(6, 1));
            window.add(choiceButtonPanel);

            choice1 = new JButton("choice1");
            choice1.setBackground(Color.black);
            choice1.setForeground(Color.black);
            choice1.setFont(normalFont);
            choice1.setFocusPainted(false);
            choice1.addActionListener(cHandler);
            choice1.setActionCommand("c1");
            choiceButtonPanel.add(choice1);
            choice2 = new JButton("choice2");
            choice2.setBackground(Color.black);
            choice2.setForeground(Color.black);
            choice2.setFont(normalFont);
            choice2.setFocusPainted(false);
            choice2.addActionListener(cHandler);
            choice2.setActionCommand("c2");
            choiceButtonPanel.add(choice2);
            choice3 = new JButton("choice3");
            choice3.setBackground(Color.black);
            choice3.setForeground(Color.black);
            choice3.setFont(normalFont);
            choice3.setFocusPainted(false);
            choice3.addActionListener(cHandler);
            choice3.setActionCommand("c3");
            choiceButtonPanel.add(choice3);
            choice4 = new JButton("choice4");
            choice4.setBackground(Color.black);
            choice4.setForeground(Color.black);
            choice4.setFont(normalFont);
            choice4.setFocusPainted(false);
            choice4.addActionListener(cHandler);
            choice4.setActionCommand("c4");
            choiceButtonPanel.add(choice4);
            choice5 = new JButton("choice5");
            choice5.setBackground(Color.black);
            choice5.setForeground(Color.black);
            choice5.setFont(normalFont);
            choice5.setFocusPainted(false);
            choice5.addActionListener(cHandler);
            choice5.setActionCommand("c5");
            choiceButtonPanel.add(choice5);
            choice6 = new JButton("choice6");
            choice6.setBackground(Color.black);
            choice6.setForeground(Color.black);
            choice6.setFont(normalFont);
            choice6.setFocusPainted(false);
            choice6.addActionListener(cHandler);
            choice6.setActionCommand("c6");
            choiceButtonPanel.add(choice6);

            playerPanel = new JPanel();
            playerPanel.setBounds(100, 15, 800, 50);
            playerPanel.setBackground(Color.black);
            playerPanel.setLayout(new GridLayout(1, 4));
            window.add(playerPanel);

            hpLabel = new JLabel("HP:");
            hpLabel.setFont(normalFont);
            hpLabel.setForeground(Color.white);
            playerPanel.add(hpLabel);
            hpNumberLabel = new JLabel();
            hpNumberLabel.setForeground(Color.white);
            hpNumberLabel.setFont(normalFont);
            playerPanel.add(hpNumberLabel);
            weaponLabel = new JLabel("Weapon:");
            weaponLabel.setForeground(Color.white);
            weaponLabel.setFont(normalFont);
            playerPanel.add(weaponLabel);
            weaponNameLabel = new JLabel();
            weaponNameLabel.setForeground(Color.white);
            weaponNameLabel.setFont(normalFont);
            playerPanel.add(weaponNameLabel);



            window.setVisible(true);

    }

    public void createGameScreen(String startOrLoad) {

        if (startOrLoad.equals("start")) {
//            playerSetup;
        }
        if (startOrLoad.equals("load")) {
            loadData();
        }
    }

    public void saveData()
    {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("save.txt"));
            bw.write(Story.me.player.hp);
            bw.newLine();
            bw.write(((Story.me.monster != null) ? Story.me.monster.hp : -10));
            bw.newLine();
            bw.write(Story.me.player.currentWeapon.name);
            bw.newLine();
            bw.write(Story.me.player.currentWeapon.damage);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null)
            {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadData() {
        Player p = Story.me.player;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("save.txt"));
            p.hp = Integer.parseInt(br.readLine());
            Story.me.monster.hp = Integer.parseInt(br.readLine());
            p.currentWeapon.name = br.readLine();
            p.currentWeapon.damage = Integer.parseInt(br.readLine());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
