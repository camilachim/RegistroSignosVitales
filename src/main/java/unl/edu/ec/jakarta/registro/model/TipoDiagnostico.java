package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.*;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

public enum TipoDiagnostico {

    @Enumerated(EnumType.STRING)
    URGENTE("Urgente"),
    MEDIO("Medio"),
    NO_URGENTE("No urgente");

    private final String label;

    private TipoDiagnostico(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
