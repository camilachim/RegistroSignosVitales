package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Entity
@Table(name = "persona")
public class Persona implements Serializable {


    private static final long serialVersionUID = 1L;

    private static List<Persona> personas = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;
    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;
    @Column(name = "identificacion", length = 20, nullable = false, unique = true)
    private String identificacion;
    @Column(name = "sexo", length = 10)
    private String sexo;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "pais", length = 50)
    private String pais;
    @Column(name = "ciudad", length = 50)
    private String ciudad;
    @Column(name = "direccion", length = 255)
    private String direccion;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "telefono", length = 20)
    private String telefono;


    public Persona() {
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        LocalDate nacimiento = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(nacimiento, LocalDate.now()).getYears();
    }

    public void actualizarDatos(String nombres, String apellidos,
                                String ciudad, String direccion,
                                String email, String telefono) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public void registrarPaciente() {
        personas.add(this);
    }

    public static Persona buscarPaciente(String identificacion) {
        for (Persona p : personas) {
            if (p.identificacion != null &&
                    p.identificacion.equals(identificacion)) {
                return p;
            }
        }
        return null;
    }

    public static boolean existePersona(String nombres, String apellidos) {
        for (Persona p : personas) {
            if (p.getNombres().equalsIgnoreCase(nombres)
                    && p.getApellidos().equalsIgnoreCase(apellidos)) {
                return true;
            }
        }
        return false;
    }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public static List<Persona> getPersonas() {
        return personas;
    }



    @Override
    public String toString() {
        return nombres + " " + apellidos +
                " - Identificación: " + identificacion +
                " - Edad: " + getEdad() +
                " - Ciudad: " + ciudad;
    }
}
