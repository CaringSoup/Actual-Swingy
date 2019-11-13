package game_state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    ChoiceHandler cHandler = new ChoiceHandler();
    UI ui = new UI();
    VisibilityManager vm = new VisibilityManager(ui);
    Story story = new Story(this, ui, vm);

    String nextPosition1, nextPosition2, nextPosition3, nextPosition4, nextPosition5, nextPosition6;

    public static void main(String[] args) {
        new Game();
    }

    public Game(){

        ui.createUI(cHandler);
        story.defaultSetup();
        vm.showTitleScreen();
    }

    public class TitleScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch(yourChoice) {
                case "start":
                    ui.createGameScreen("Start");
                    break;
                case "load":
                    ui.createGameScreen("Load");
                    break;
            }

        }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch (yourChoice) {

                case "start": vm.titleToTown(); story.townGate(); break;
                case "c1": story.selectPosition(nextPosition1); break;
                case "c2": story.selectPosition(nextPosition2); break;
                case "c3": story.selectPosition(nextPosition3); break;
                case "c4": story.selectPosition(nextPosition4); break;
                case "c5": story.selectPosition(nextPosition5); break;
                case "c6": story.selectPosition(nextPosition6); break;
            }

        }
    }
}
