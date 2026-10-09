package com.inversiones.magdezis.controller;

import com.inversiones.magdezis.dto.LoginDto;
import com.inversiones.magdezis.model.Usuario;
import com.inversiones.magdezis.service.AuthService;
import com.inversiones.magdezis.view.Vista;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private final AuthService authService = new AuthService();

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void iniciarSesion() {
        try {
            Usuario usuario = authService.iniciarSesion(new LoginDto(txtCorreo.getText(), txtContrasena.getText()));
            Vista.alerta(Alert.AlertType.INFORMATION, "Inicio de sesión",
                    "Bienvenido " + usuario.getNombreUsuario() + ", inició sesión con el rol " + usuario.getRol().getNombreRol());
            txtContrasena.clear();
        } catch (IllegalArgumentException e) {
            Vista.alerta(Alert.AlertType.WARNING, "Aviso", e.getMessage());
        } catch (IllegalStateException e) {
            Vista.alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void irRegistro() {
        Vista.mostrar("registro.fxml", "Registro");
    }
}
