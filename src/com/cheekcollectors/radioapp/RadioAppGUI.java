package com.cheekcollectors.radioapp;

import com.cheekcollectors.radioapp.eventhandlers.FrequencyTypeEvent;
import com.cheekcollectors.radioapp.eventhandlers.PowerButtonEvent;
import com.cheekcollectors.radioapp.eventhandlers.SeekButtonEvent;
import com.cheekcollectors.radioapp.eventhandlers.SetSelectFavoriteEvent;
import com.cheekcollectors.radioapp.fileio.RadioDataManager;
import com.cheekcollectors.radioapp.nodes.FavoriteButton;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.radio.SeekDirection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioAppGUI extends Application {

    private static final String APP_TITLE = "Radio Application";
    private static Radio radio;
    private static TextField statusPane;
    private static FavoriteMode favoriteMode;

    public static void main(String[] args) {
        launch(args);
    }

    public static Radio getRadio() {
        return radio;
    }

    public static void updateStatus(String status) {
        statusPane.setText(status);
    }

    public static FavoriteMode getFavoriteMode() {
        return favoriteMode;
    }

    public static void setFavoriteMode(FavoriteMode favoriteMode) {
        RadioAppGUI.favoriteMode = favoriteMode;
    }

    public void start(Stage primaryStage) throws Exception {
        radio = RadioDataManager.readData();
        if (radio == null) {
            radio = new Radio();
            favoriteMode = FavoriteMode.SET;
        }

        initLayout(primaryStage);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        RadioDataManager.saveData(radio);
    }

    private void initLayout(Stage primaryStage) {
        VBox topMenu = new VBox();
        topMenu.setSpacing(25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setPadding(new Insets(15, 0, 25, 0));

        HBox frequencyTypeMenu = new HBox();
        frequencyTypeMenu.setSpacing(25);
        frequencyTypeMenu.setAlignment(Pos.CENTER);

        HBox middleMenu = new HBox();
        middleMenu.setSpacing(10);
        middleMenu.setAlignment(Pos.CENTER);

        HBox bottomMenu = new HBox();
        bottomMenu.setSpacing(20);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(25, 0, 25, 0));

        Button amFmButton = new Button("AM/FM");
        Button setToggleButton = new Button("SET");
        Button seekBackButton = new Button("<<");
        Button seekForwardButton = new Button(">>");
        Button powerButton = new Button("");

        FavoriteButton[] favoriteButtons = new FavoriteButton[6];
        for (int i = 0; i < 6; i++) {
            favoriteButtons[i] = new FavoriteButton(Integer.toString(i + 1), i);
            favoriteButtons[i].setOnMouseClicked(new SetSelectFavoriteEvent());
        }

        amFmButton.setOnAction(new FrequencyTypeEvent());

        seekBackButton.setOnAction(new SeekButtonEvent(SeekDirection.DOWN));
        seekForwardButton.setOnAction(new SeekButtonEvent(SeekDirection.UP));

        powerButton.setPrefSize(30, 30);
        //TODO powerButton.setStyle("-fx-background-image: url('resources/power.png'); -fx-background-size: 20px; -fx-background-repeat: no-repeat; -fx-background-position: center;");
        powerButton.setOnAction(new PowerButtonEvent());

        statusPane = new TextField(radio.getStatusString());
        statusPane.setAlignment(Pos.CENTER);
        statusPane.setDisable(true);
        statusPane.setStyle("-fx-background-color: darkolivegreen; -fx-opacity: 1; -fx-text-fill: white");

        frequencyTypeMenu.getChildren().addAll(amFmButton, setToggleButton);
        topMenu.getChildren().addAll(powerButton, frequencyTypeMenu);
        middleMenu.getChildren().addAll(seekBackButton, statusPane, seekForwardButton);
        bottomMenu.getChildren().addAll(favoriteButtons);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topMenu);
        mainLayout.setCenter(middleMenu);
        mainLayout.setBottom(bottomMenu);

        Scene scene = new Scene(mainLayout, 500, 220);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        mainLayout.requestFocus();

    }

}
