package unl.edu.ec.jakarta.registro.services;

import jakarta.persistence.EntityManager;
import unl.edu.ec.jakarta.registro.model.Diagnostico;
import unl.edu.ec.jakarta.registro.model.JpaUtil;
import unl.edu.ec.jakarta.registro.model.Persona;

import java.util.List;

public class DiagnosticoService {

    public void guardar(Diagnostico diagnostico) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(diagnostico);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Diagnostico> listarPorPaciente(Persona paciente) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Diagnostico d WHERE d.paciente = :p ORDER BY d.id DESC", Diagnostico.class)
                     .setParameter("p", paciente)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public Diagnostico buscarPorId(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Diagnostico.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}