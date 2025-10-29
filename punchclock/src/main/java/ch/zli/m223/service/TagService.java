package ch.zli.m223.service;

import ch.zli.m223.model.Tag;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Tag createTag(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteTag(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.remove(tag);
    }

    @Transactional
    public Tag updateTag(Tag tag) {
        return entityManager.merge(tag);
    }
}
