package repository;

import domain.Order;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrderRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("skhu-burger-persistence");
    EntityManager em = emf.createEntityManager();

    public void save(Order order) {
        em.persist(order);
    }

    public void findById(Long id) {
        em.find(Order.class, id);
    }

    public void delete(Order order) {
        em.remove(order);
    }
}
