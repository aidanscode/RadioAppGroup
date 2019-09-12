package com.cheekcollectors.radioapp;

import com.cheekcollectors.radioapp.radio.Radio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioAppGUI extends Application {
    private static final String APP_TITLE = "Radio Application";
    private static Radio radio;
    private static TextField statusPane;

    public static void main(String[] args) {
        launch(args);
    }

    public static Radio getRadio(){
        return radio;
    }

    public static void updateStatus(String status){
        statusPane.setText(status);
    }

    public void start(Stage primaryStage) throws Exception {
    //radio = DataStorageManager.readData();
    if (radio == null)
        radio = new Radio();
    initLayout(primaryStage);
    primaryStage.show();
            }
            public void stop() throws Exception {
        //super.stop;

       // DataStorageManager.saveData(radio);
            }

            private void initLayout(Stage primaryStage){
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
        Button seekBackButton = new Button("<<"); Button seekForwardButton = new Button(">>");
        Button powerButton = new Button("");
    }
        }