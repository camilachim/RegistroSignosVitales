package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Entity
@Table(name = "diagnostico")
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoDiagnostico tipo;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_persona_fk")
    private Persona paciente;

    public Diagnostico() {
    }
    
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public TipoDiagnostico getTipo() { return tipo; }
    public void setTipo(TipoDiagnostico tipo) { this.tipo = tipo; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Persona getPaciente() { return paciente; }
    public void setPaciente(Persona paciente) { this.paciente = paciente; }
}