package ch.zli.m223.service;

import java.util.List;

import ch.zli.m223.model.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteCategory(long id) {
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }

    @Transactional
    public Category updateCategory(Category entry) {
        return entityManager.merge(entry);
    }
}
