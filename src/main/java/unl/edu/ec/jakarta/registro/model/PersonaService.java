package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

public class PersonaService {

    public void guardarPersona(Persona persona) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Persona> obtenerTodas() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Persona buscarPorIdentificacion(String identificacion) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Persona> query = em.createQuery(
                "SELECT p FROM Persona p WHERE p.identificacion = :id", Persona.class);
            
            query.setParameter("id", identificacion);
            
            List<Persona> resultados = query.getResultList();
            
            if (resultados.isEmpty()) {
                return null; 
            } else {
                return resultados.get(0); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}