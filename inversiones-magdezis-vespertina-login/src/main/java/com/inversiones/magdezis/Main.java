package com.inversiones.magdezis;

import com.inversiones.magdezis.view.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Vista.iniciar(stage);
        Vista.mostrar("login.fxml", "Inversiones Magdezis");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
