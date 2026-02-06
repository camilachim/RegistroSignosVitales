package unl.edu.ec.jakarta.registro.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

@Embeddable
public class DatosAntropometricos implements Serializable {

    private static final long serialVersionUID = 1L;

    private double peso; 
    private double talla; 
    private double imc;   

    public DatosAntropometricos() {}

    public DatosAntropometricos(double peso, double talla) {
        this.peso = peso;
        this.talla = talla;
        calcularIMC();
    }

    public void calcularIMC() {
        if (talla > 0) {
            this.imc = peso / (talla * talla);
        }
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) {
        this.peso = peso;
        calcularIMC();
    }

    public double getTalla() { return talla; }
    public void setTalla(double talla) {
        this.talla = talla;
        calcularIMC();
    }

    public double getImc() { return imc; }
}
