package com.inversiones.magdezis.controller;

import com.inversiones.magdezis.dto.RegistroDto;
import com.inversiones.magdezis.model.Rol;
import com.inversiones.magdezis.service.AuthService;
import com.inversiones.magdezis.view.Vista;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController implements Initializable {

    private final AuthService authService = new AuthService();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private ComboBox<Rol> cmbRol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cmbRol.setItems(FXCollections.observableArrayList(authService.listarRoles()));
            cmbRol.getSelectionModel().selectFirst();
        } catch (IllegalStateException e) {
            Vista.alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void registrar() {
        Rol rol = cmbRol.getValue();
        int idRol = rol == null ? 0 : rol.getIdRol();
        try {
            authService.registrar(new RegistroDto(txtNombre.getText(), txtApellido.getText(),
                    txtCorreo.getText(), txtContrasena.getText(), idRol));
            Vista.alerta(Alert.AlertType.INFORMATION, "Registro", "Usuario registrado correctamente");
            Vista.mostrar("login.fxml", "Inversiones Magdezis");
        } catch (IllegalArgumentException e) {
            Vista.alerta(Alert.AlertType.WARNING, "Aviso", e.getMessage());
        } catch (IllegalStateException e) {
            Vista.alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void volver() {
        Vista.mostrar("login.fxml", "Inversiones Magdezis");
    }
}
