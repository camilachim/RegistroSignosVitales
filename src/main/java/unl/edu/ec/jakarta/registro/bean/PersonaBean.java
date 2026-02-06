package unl.edu.ec.jakarta.registro.bean;

import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import unl.edu.ec.jakarta.registro.model.Persona;
import unl.edu.ec.jakarta.registro.services.PersonaService;
import jakarta.faces.context.FacesContext;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Named
@ViewScoped
public class PersonaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Persona persona = new Persona();
    private boolean mostrarInfo = false;
    private Persona pacienteSeleccionado;
    
    private PersonaService servicio = new PersonaService();

    public Persona getPersona() {
        return persona;
    }

    public boolean isMostrarInfo() {
        return mostrarInfo;
    }

    public List<Persona> getListaPacientes() {
        return servicio.obtenerTodas();
    }

    public String registrar() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (persona.getNombres() == null || persona.getNombres().isBlank()
                || persona.getApellidos() == null || persona.getApellidos().isBlank()
                || persona.getIdentificacion() == null || persona.getIdentificacion().isBlank()
                || persona.getSexo() == null || persona.getSexo().isBlank()
                || persona.getCiudad() == null || persona.getCiudad().isBlank()
                || persona.getDireccion() == null || persona.getDireccion().isBlank()
                || persona.getEmail() == null || persona.getEmail().isBlank()
                || persona.getTelefono() == null || persona.getTelefono().isBlank()
                || persona.getFechaNacimiento() == null) {

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Campos obligatorios",
                            "Es obligatorio llenar todos los campos"));
            return null;
        }

        Persona existe = servicio.buscarPorIdentificacion(persona.getIdentificacion());
        if (existe != null) { 
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Persona ya existente",
                            "Ya existe una persona con esa identificación en la base de datos"));
            return null;
        }

        servicio.guardarPersona(persona); 

        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro exitoso",
                        "Persona registrada correctamente en PostgreSQL"));

        mostrarInfo = false;
        persona = new Persona(); 

        return "registro.xhtml?faces-redirect=true";
    }

    public String seleccionarPaciente(Persona p) {
        this.pacienteSeleccionado = p;
        return "registro-signos.xhtml?faces-redirect=true&idPaciente="
                + p.getIdentificacion();
    }

    public void buscar() {
        Persona p = servicio.buscarPorIdentificacion(persona.getIdentificacion());
        
        if (p != null) {
            this.persona = p;
            mostrarInfo = true;
        } else {
             FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "No encontrado",
                        "No se encontró a nadie con esa cédula"));
        }
    }
}