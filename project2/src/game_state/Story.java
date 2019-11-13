package game_state;

import artifacts.Weapon_Knife;
import artifacts.Weapon_DoubleSided_Axe;
import villans.Monster_Goblin;
import villans.Monster_Troll;
import villans.SuperMonster;

import javax.swing.plaf.nimbus.State;
import java.util.Scanner;

public class Story {

    public static Story me;
    Game game;
    UI ui;
    VisibilityManager vm;
    public Player player = new Player();
    SuperMonster monster;

    int silverRing;

    public Story(Game g, UI userInterface, VisibilityManager vManager) {
        me = this;
        game = g;
        ui = userInterface;
        vm = vManager;
    }


    public void defaultSetup() {

        player.hp = 50;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        silverRing = 0;
    }


    public void selectPosition(String nextPosition) {

        switch(nextPosition) {
            case "townGate": townGate(); break;
            case "talkGuard": talkGuard(); break;
            case "attackGuard": attackGuard(); break;
            case "crossRoad": crossRoad(); break;
            case "north": north(); break;
            case "east": east(); break;
            case "west": west(); break;
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "monsterAttack": monsterAttack(); break;
            case "win": win(); break;
            case "lose": lose(); break;
            case "ending": ending(); break;
            case "toTitle": toTitle(); break;
            case "save": ui.saveData(); break;
        }
    }

    public void townGate() {

        ui.mainTextArea.setText("You are standing at the gate of the town. \nThere is a guard standing in front of the gate. \n\nWhat do you do?");

        ui.choice1.setVisible(true);
        ui.choice2.setVisible(true);
        ui.choice3.setVisible(true);
        ui.choice4.setVisible(true);
        ui.choice5.setVisible(true);
        ui.choice6.setVisible(true);

        ui.choice1.setText("Approach the guard");
        ui.choice2.setText("Attack the guard");
        ui.choice3.setText("Leave the gate");
        ui.choice4.setVisible(false);
        ui.choice5.setText("Save game");
        ui.choice6.setText("Quit game");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
        game.nextPosition5 = "save";
        game.nextPosition6 = "toTitle";

    }

    public void talkGuard() {

        if (silverRing == 0) {
            ui.mainTextArea.setText("Guard: Hello there "+player.name+". I really do hope that you are able to find the Queens ring from whoever... or whatever it was that took it. I'll be waiting right here for your return.");

            ui.choice1.setText("Go back");
            ui.choice2.setVisible(false);
            ui.choice3.setVisible(false);
            ui.choice4.setVisible(false);

            game.nextPosition1 = "townGate";
        }
        else if (silverRing == 1) {
            ending();
        }
    }
    public void attackGuard() {

        ui.mainTextArea.setText("Guard: Fuck you infidel!\n\nThe guard strikes you with his sword and you fall to the ground. He tells you to leave immediately! You pick yourself up and walk away. (You have taken 9 damage)");
        player.hp = player.hp - 9;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.choice1.setText("Go back");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
        ui.choice5.setVisible(true);

        game.nextPosition1 = "townGate";
    }
    public void crossRoad() {

        ui.mainTextArea.setText("You find yourself standing at a crossroad. If you go South you will go head straight back into town and will have to face the guard once more. \n\nWhich direction will you go?");

        ui.choice1.setVisible(true);
        ui.choice2.setVisible(true);
        ui.choice3.setVisible(true);
        ui.choice4.setVisible(true);
        ui.choice5.setVisible(true);

        ui.choice1.setText("Go North");
        ui.choice2.setText("Go East");
        ui.choice3.setText("Go South");
        ui.choice4.setText("Go West");

        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";

    }
    public void north() {

        ui.mainTextArea.setText("You found a river that looks refreshing. You clean up your bloody wound and then drink and bathe in the water. (You have recovered 6 HP).");

        player.hp = player.hp + 6;
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Head back south");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        game.nextPosition1 = "crossRoad";
    }
    public void east() {
        ui.mainTextArea.setText("You walked into a mysterious yet magical forrest. You find a double sided Axe lying next to ruins.\n\n(Double-sided axe acquired)");
        player.currentWeapon = new Weapon_DoubleSided_Axe();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        ui.choice1.setText("Go west");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        game.nextPosition1 = "crossRoad";
    }
    public void west() {

        int i = new java.util.Random().nextInt(100) + 1;

        if (i < 90) {
            monster = new Monster_Goblin();
        }
        else {
            monster = new Monster_Troll();
        }

        ui.mainTextArea.setText("You have encounter a " + monster.name + "!");

        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
    }
    public void fight() {

        ui.mainTextArea.setText("The " + monster.name + " has " + monster.hp + "HP. What do you do?");

        ui.choice2.setVisible(true);

        ui.choice1.setText("Attack");
        ui.choice2.setText("Run");

        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";

    }
    public void playerAttack() {

        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        if (playerDamage >= 1)
            ui.mainTextArea.setText("You attacked the " + monster.name + " and dealt " + playerDamage + " damage!");
        else
            ui.mainTextArea.setText("You fuck-face you missed, you missed a " + monster.name + "... Like HOW THE FUCK is that even possible?!");

        monster.hp = monster.hp - playerDamage;

        ui.choice1.setText("(Next)");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        if (monster.hp >= 1) {
            game.nextPosition1 = "monsterAttack";
        }
        else {
            game.nextPosition1 = "win";
        }
    }
    public void monsterAttack() {

        int monsterDamage = new java.util.Random().nextInt(monster.attack);

        ui.mainTextArea.setText(monster.attackMessage + "The monster attacked you and dealt " + monsterDamage + " damage to you!");

        player.hp = player.hp - monsterDamage;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.choice1.setText("(Go back)");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        if (player.hp > 0) {
            game.nextPosition1 = "fight";
        }
        else if (player.hp < 1) {
            game.nextPosition1 = "lose";
        }
    }
    public void win() {

        ui.mainTextArea.setText("You have defeated the " + monster.name + "!\nThe monster dropped a ring \n\n(Silver Ring acquired)");

        silverRing = 1;

        ui.choice1.setText("Go east");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);

        game.nextPosition1 = "crossRoad";
    }
    public void lose() {

        ui.mainTextArea.setText("You have been slain!\n\n<GAME OVER>");

        ui.choice1.setText("Title screen");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
        ui.choice5.setVisible(false);

        game.nextPosition1 = "toTitle";
    }
    public void ending() {

        ui.mainTextArea.setText("Guard: Oh I see you have killed the Goblin! Thank you so much, you are a true hero!" + "\nOn behalf of myself and the rest of the town, welcome to Kozmo Town!\n\n<THE END>");

        ui.choice1.setText("Return to title screen");
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
        ui.choice5.setVisible(false);

        game.nextPosition1 = "toTitle";
    }
    public void toTitle() {

        defaultSetup();
        vm.showTitleScreen();
    }
}
