package repository;

import domain.Category;
import domain.Menu;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import util.JpaUtil;

public class MenuRepository {
    private final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public void save(Menu menu) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(menu);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public Menu findById(Long id) {
        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
        Menu menu = em.find(Menu.class, id);
//        tx.commit();
        em.close();
        return menu;
    }

    public List<Menu> findAll() {
        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
        try {
            String query = "select m from Menu m";
            return em.createQuery(query, Menu.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Menu findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "select m from Menu m where m.name = :name";
            return em.createQuery(query, Menu.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Menu> findByCategory(String categoryText) {
        Category category = Category.getCategory(categoryText);
        EntityManager em = emf.createEntityManager();
        try {
            String query = "select m from Menu m where m.category = :category";
            return em.createQuery(query, Menu.class)
                    .setParameter("category", category)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
