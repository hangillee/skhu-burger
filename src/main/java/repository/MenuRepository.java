package repository;

import domain.Menu;
import domain.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MenuRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("skhu-burger-persistence");
    EntityManager em = emf.createEntityManager();

    public void save(Menu menu) {
        em.persist(menu);
    }

    public Menu findById(Long id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> findByOrder(Order order) {
        String query = "select m from Menu m where m.order = :order";
        return em.createQuery(query, Menu.class)
                .setParameter("order", order)
                .getResultList();
    }
}
