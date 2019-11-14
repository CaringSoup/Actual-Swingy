package game_state;

public class VisibilityManager {

    UI ui;

    public VisibilityManager(UI userInterface) {

        ui = userInterface;
    }

    public void showTitleScreen() {

        // SHOWING TITLE SCREEN
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);
        ui.textPanel.setVisible(true);

        // HIDE GAME SCREEN
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
    }

    public void titleToTown() {

        // HIDE TITLE SCREEN
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
        ui.textPanel.setVisible(false);

        // SHOWING GAME SCREEN
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
        Story.me.player.name = ui.uName.getText();
    }
}
