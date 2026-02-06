package unl.edu.ec.jakarta.registro.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import unl.edu.ec.jakarta.registro.model.Diagnostico;
import unl.edu.ec.jakarta.registro.model.Persona;
import unl.edu.ec.jakarta.registro.model.TipoDiagnostico;
import unl.edu.ec.jakarta.registro.services.DiagnosticoService;
import unl.edu.ec.jakarta.registro.services.PersonaService;

import java.io.Serializable;
import java.util.List;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Named("observacionBean")
@ViewScoped
public class ObservacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Diagnostico diagnostico = new Diagnostico();
    private List<Diagnostico> listaDiagnosticos;
    private String idPaciente;
    private Persona paciente;
    private Integer idDiagnosticoSeleccionado; 
    private Diagnostico diagnosticoDetalle;    
    private DiagnosticoService servicio = new DiagnosticoService();
    private PersonaService personaService = new PersonaService();

    public void cargarDatos() {
        if (idPaciente != null) {
            this.paciente = personaService.buscarPorIdentificacion(idPaciente);
            if (this.paciente != null) {
                this.listaDiagnosticos = servicio.listarPorPaciente(this.paciente);
            }
        }
    }

    public void cargarDetalleDiagnostico() {
        if (idDiagnosticoSeleccionado != null) {
            this.diagnosticoDetalle = servicio.buscarPorId(idDiagnosticoSeleccionado);
            
            if (this.diagnosticoDetalle != null) {
                this.paciente = this.diagnosticoDetalle.getPaciente();
            }
        }
    }

    public void guardar() {
        if (paciente == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay paciente seleccionado"));
            return;
        }

        try {
            diagnostico.setPaciente(this.paciente);
            servicio.guardar(diagnostico);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Observación guardada"));

            diagnostico = new Diagnostico();
            this.listaDiagnosticos = servicio.listarPorPaciente(this.paciente);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TipoDiagnostico[] getTiposDiagnostico() {
        return TipoDiagnostico.values();
    }

    public Diagnostico getDiagnostico() { return diagnostico; }
    public void setDiagnostico(Diagnostico diagnostico) { this.diagnostico = diagnostico; }
    public List<Diagnostico> getListaDiagnosticos() { return listaDiagnosticos; }
    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }
    public Persona getPaciente() { return paciente; }

    public Integer getIdDiagnosticoSeleccionado() { return idDiagnosticoSeleccionado; }
    public void setIdDiagnosticoSeleccionado(Integer idDiagnosticoSeleccionado) { this.idDiagnosticoSeleccionado = idDiagnosticoSeleccionado; }
    public Diagnostico getDiagnosticoDetalle() { return diagnosticoDetalle; }
    public void setDiagnosticoDetalle(Diagnostico diagnosticoDetalle) { this.diagnosticoDetalle = diagnosticoDetalle; }
}