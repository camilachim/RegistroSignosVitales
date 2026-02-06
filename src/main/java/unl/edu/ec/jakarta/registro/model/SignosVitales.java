package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Entity
@Table(name = "signos_vitales")
public class SignosVitales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_signos_vitales")
    private Integer id;
    
    @Column(name = "temperatura_c")
    private double temperaturaC;
    @Column(name = "frecuencia_cardiaca_lpm")
    private int frecuenciaCardiacaLpm;
    @Column(name = "frecuencia_respiratoria_rpm")
    private int frecuenciaRespiratoriaRpm;
    @Column(name = "presion_diastolica_mmhg")
    private int presionDiastolicaMmHg;
    @Column(name = "presion_sistolica_mmhg")
    private int presionSistolicaMmHg;
    @Column(name = "saturacion_oxigeno")
    private int saturacionOxigeno;


    @ManyToOne
    @JoinColumn(name = "id_persona_fk")
    private Persona paciente;

    public Persona getPaciente() {
        return paciente;
    }

    public void setPaciente(Persona paciente) {
        this.paciente = paciente;
    }

    
    public SignosVitales() {}

    public SignosVitales(double temperaturaC, int fc, int fr, int pd, int ps, int so) {
        this.temperaturaC = temperaturaC;
        this.frecuenciaCardiacaLpm = fc;
        this.frecuenciaRespiratoriaRpm = fr;
        this.presionDiastolicaMmHg = pd;
        this.presionSistolicaMmHg = ps;
        this.saturacionOxigeno = so;
    }

    @Embedded
    private DatosAntropometricos datosAntropometricos = new DatosAntropometricos();

    public DatosAntropometricos getDatosAntropometricos() { return datosAntropometricos; }
    public void setDatosAntropometricos(DatosAntropometricos datosAntropometricos) {
        this.datosAntropometricos = datosAntropometricos;
    }

    public double getTemperaturaC() { return temperaturaC; }
    public void setTemperaturaC(double temperaturaC) { this.temperaturaC = temperaturaC; }

    public int getFrecuenciaCardiacaLpm() { return frecuenciaCardiacaLpm; }
    public void setFrecuenciaCardiacaLpm(int frecuenciaCardiacaLpm) { this.frecuenciaCardiacaLpm = frecuenciaCardiacaLpm; }

    public int getFrecuenciaRespiratoriaRpm() { return frecuenciaRespiratoriaRpm; }
    public void setFrecuenciaRespiratoriaRpm(int frecuenciaRespiratoriaRpm) { this.frecuenciaRespiratoriaRpm = frecuenciaRespiratoriaRpm; }

    public int getPresionDiastolicaMmHg() { return presionDiastolicaMmHg; }
    public void setPresionDiastolicaMmHg(int presionDiastolicaMmHg) { this.presionDiastolicaMmHg = presionDiastolicaMmHg; }

    public int getPresionSistolicaMmHg() { return presionSistolicaMmHg; }
    public void setPresionSistolicaMmHg(int presionSistolicaMmHg) { this.presionSistolicaMmHg = presionSistolicaMmHg; }

    public int getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(int saturacionOxigeno) { this.saturacionOxigeno = saturacionOxigeno; }

    public boolean temperaturaNormal() { return temperaturaC >= 36 && temperaturaC <= 37.5; }
    public boolean frecuenciaCardiacaNormal() { return frecuenciaCardiacaLpm >= 60 && frecuenciaCardiacaLpm <= 100; }
    public boolean frecuenciaRespiratoriaNormal() { return frecuenciaRespiratoriaRpm >= 12 && frecuenciaRespiratoriaRpm <= 20; }
    public boolean presionDiastolicaNormal() { return presionDiastolicaMmHg >= 60 && presionDiastolicaMmHg <= 80; }
    public boolean presionSistolicaNormal() { return presionSistolicaMmHg >= 90 && presionSistolicaMmHg <= 120; }
    public boolean saturacionOxigenoNormal() { return saturacionOxigeno >= 95; }
    public boolean signosNormales() {
        return temperaturaNormal() && frecuenciaCardiacaNormal() && frecuenciaRespiratoriaNormal() &&
                presionDiastolicaNormal() && presionSistolicaNormal() && saturacionOxigenoNormal();
    }


}

