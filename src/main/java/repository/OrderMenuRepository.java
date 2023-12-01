package repository;

import domain.OrderMenu;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import util.JpaUtil;

public class OrderMenuRepository {
    private final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public void save(OrderMenu orderMenu) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(orderMenu);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
