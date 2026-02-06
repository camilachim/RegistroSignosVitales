package unl.edu.ec.jakarta.registro.model; 

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbrewPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}