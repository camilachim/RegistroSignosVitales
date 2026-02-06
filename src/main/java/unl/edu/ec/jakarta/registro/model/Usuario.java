package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;  
import java.io.Serializable;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "usuario", length = 50, nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasenia", length = 255, nullable = false)
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        setContrasenia(contrasenia);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        try {
            this.contrasenia = EncryptorManager.encrypt(contrasenia);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    public boolean autenticar(String contraseniaIngresada) {
        try {
            return this.contrasenia.equals(
                    EncryptorManager.encrypt(contraseniaIngresada)
            );
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
