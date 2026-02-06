package unl.edu.ec.jakarta.registro.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * @author chimbocamila29 (Camila Chimbo;
 *                         Ethan Soto;
 *                         Victor Macas)
 */

public class SignosVitalesService {

    public void guardarSignos(SignosVitales signos) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(signos); 
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<SignosVitales> listarPorPaciente(Persona paciente) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT s FROM SignosVitales s WHERE s.paciente = :paciente ORDER BY s.id DESC";
            TypedQuery<SignosVitales> query = em.createQuery(jpql, SignosVitales.class);
            
            query.setParameter("paciente", paciente);
            
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}