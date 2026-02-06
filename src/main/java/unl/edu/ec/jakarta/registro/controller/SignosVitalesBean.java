package unl.edu.ec.jakarta.registro.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import unl.edu.ec.jakarta.registro.model.Persona;
import unl.edu.ec.jakarta.registro.model.SignosVitales;
import unl.edu.ec.jakarta.registro.services.SignosVitalesService;
import unl.edu.ec.jakarta.registro.model.DatosAntropometricos;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Named("controladorSignosVitales")
@SessionScoped
public class SignosVitalesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Persona paciente;
    private SignosVitales nuevoSigno;
    private DatosAntropometricos datosAntropometricos;
    private List<SignosVitales> listaSignos;
    private SignosVitalesService servicio = new SignosVitalesService(); 

    @PostConstruct
    public void init() {
        nuevoSigno = new SignosVitales();
        datosAntropometricos = new DatosAntropometricos();
        listaSignos = new ArrayList<>();
    }

    public Persona getPaciente() {
        return paciente;
    }

    public void setPaciente(Persona paciente) {
        this.paciente = paciente;
    }

    public SignosVitales getNuevoSigno() {
        return nuevoSigno;
    }

    public void setNuevoSigno(SignosVitales nuevoSigno) {
        this.nuevoSigno = nuevoSigno;
    }

    public DatosAntropometricos getDatosAntropometricos() {
        return datosAntropometricos;
    }

    public void setDatosAntropometricos(DatosAntropometricos datosAntropometricos) {
        this.datosAntropometricos = datosAntropometricos;
    }

    public List<SignosVitales> getListaSignos() {
        return listaSignos;
    }

    public String cargarPaciente(Persona p) {
        this.paciente = p;
        this.nuevoSigno = new SignosVitales();
        this.datosAntropometricos = new DatosAntropometricos();
        
        this.listaSignos = servicio.listarPorPaciente(p);

        return "registro-signos.xhtml?faces-redirect=true";
    }


    public void guardarSigno() {
        
        if (this.paciente == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay paciente seleccionado"));
            return;
        }

        SignosVitales signo = new SignosVitales(
                nuevoSigno.getTemperaturaC(),
                nuevoSigno.getFrecuenciaCardiacaLpm(),
                nuevoSigno.getFrecuenciaRespiratoriaRpm(),
                nuevoSigno.getPresionDiastolicaMmHg(),
                nuevoSigno.getPresionSistolicaMmHg(),
                nuevoSigno.getSaturacionOxigeno()
        );

        signo.setDatosAntropometricos(new DatosAntropometricos(
                datosAntropometricos.getPeso(),
                datosAntropometricos.getTalla()
        ));
        
        signo.setPaciente(this.paciente); 

        servicio.guardarSignos(signo); 

        this.listaSignos = servicio.listarPorPaciente(this.paciente);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Signos vitales guardados correctamente en BD", null));

        nuevoSigno = new SignosVitales();
        datosAntropometricos = new DatosAntropometricos();
    }
}