package com.inversiones.magdezis.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Vista {

    private static Stage escenario;

    private Vista() {
    }

    public static void iniciar(Stage stage) {
        escenario = stage;
    }

    public static void mostrar(String archivo, String titulo) {
        try {
            Parent raiz = FXMLLoader.load(Vista.class.getResource("/view/" + archivo));
            escenario.setScene(new Scene(raiz));
            escenario.setTitle(titulo);
            escenario.sizeToScene();
            escenario.centerOnScreen();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la vista " + archivo, e);
        }
    }

    public static void alerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
