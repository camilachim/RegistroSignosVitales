package unl.edu.ec.jakarta.registro.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import unl.edu.ec.jakarta.registro.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Named("inicioBean")
@ViewScoped
public class InicioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String contrasenia;

    private static final List<Usuario> usuariosRegistrados = new ArrayList<>();

    static {
        usuariosRegistrados.add(new Usuario("camila", "1105866071"));
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String iniciarSesion() {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(nombreUsuario)
                    && u.autenticar(contrasenia)) {

                return "/ver-paciente.xhtml?faces-redirect=true";
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Usuario o contraseña incorrectos", null));

        return null;
    }
}
