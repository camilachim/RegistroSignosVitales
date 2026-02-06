package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Entity
@Table(name = "registro_signos")
public class RegistroSignos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_signos")
    private Integer id;

    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "hora")
    private LocalTime hora;
    @Column(name = "observaciones")
    private String observaciones;

    public RegistroSignos() {
    }

    public RegistroSignos(String observaciones) {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.observaciones = observaciones;
    }

    public void crearRegistro() {
        System.out.println("Registro creado");
    }

    public void obtenerRegistro() {
        System.out.println("Fecha: " + fecha + " Hora: " + hora);
    }

}
