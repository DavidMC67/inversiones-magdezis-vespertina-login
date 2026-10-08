package com.inversiones.magdezis.service;

import com.inversiones.magdezis.dto.LoginDto;
import com.inversiones.magdezis.dto.RegistroDto;
import com.inversiones.magdezis.model.Rol;
import com.inversiones.magdezis.model.Usuario;
import com.inversiones.magdezis.repository.RolRepository;
import com.inversiones.magdezis.repository.UsuarioRepository;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final RolRepository rolRepository = new RolRepository();

    public List<Rol> listarRoles() {
        try {
            return rolRepository.listar();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudieron cargar los roles", e);
        }
    }

    public void registrar(RegistroDto dto) {
        if (vacio(dto.getNombre()) || vacio(dto.getApellido()) || vacio(dto.getCorreo()) || vacio(dto.getContrasena())) {
            throw new IllegalArgumentException("Complete todos los campos");
        }
        if (dto.getIdRol() <= 0) {
            throw new IllegalArgumentException("Seleccione un rol");
        }
        if (!dto.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo no es válido");
        }
        if (dto.getContrasena().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
        try {
            String correo = dto.getCorreo().trim().toLowerCase();
            if (usuarioRepository.existeCorreo(correo)) {
                throw new IllegalArgumentException("El correo ya está registrado");
            }
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(dto.getNombre().trim());
            usuario.setApellidoUsuario(dto.getApellido().trim());
            usuario.setCorreoUsuario(correo);
            usuario.setContrasenaUsuario(BCrypt.hashpw(dto.getContrasena(), BCrypt.gensalt()));
            usuario.setRol(new Rol(dto.getIdRol(), null));
            usuarioRepository.guardar(usuario);
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo registrar el usuario", e);
        }
    }

    public Usuario iniciarSesion(LoginDto dto) {
        if (vacio(dto.getCorreo()) || vacio(dto.getContrasena())) {
            throw new IllegalArgumentException("Complete todos los campos");
        }
        try {
            Usuario usuario = usuarioRepository.buscarPorCorreo(dto.getCorreo().trim().toLowerCase());
            if (usuario == null || !BCrypt.checkpw(dto.getContrasena(), usuario.getContrasenaUsuario())) {
                throw new IllegalArgumentException("Correo o contraseña incorrectos");
            }
            return usuario;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo iniciar sesión", e);
        }
    }

    private boolean vacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
