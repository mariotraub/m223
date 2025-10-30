package ch.zli.m223;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@IfBuildProfile("dev")
public class TestData {
    @Inject
    EntityManager em;

    @Startup
    public void initTestData() {
        System.err.println("Creating Testdata");
        List<Tag> tags = createTags();
        List<Category> categories = createCategories();
        createEntries(tags, categories);
    }

    @Transactional
    void createEntries(List<Tag> tags, List<Category> categories) {
        Entry e1 = new Entry();
        e1.setTags(tags);
        e1.setCategory(categories.getFirst());
        e1.setCheckIn(LocalDateTime.of(2025, 10, 29, 8, 0));
        e1.setCheckOut(LocalDateTime.of(2025, 10, 29, 17, 0));
        em.persist(e1);

        Entry e2 = new Entry();
        e2.setTags(tags.subList(1, tags.size()));
        e2.setCategory(categories.get(1));
        e2.setCheckIn(LocalDateTime.of(2025, 10, 30, 8, 0));
        e2.setCheckOut(LocalDateTime.of(2025, 10, 30, 17, 0));
        em.persist(e2);
    }

    @Transactional
    List<Tag> createTags() {
        Tag t1 = new Tag();
        t1.setTitle("Tag 1");
        em.persist(t1);

        Tag t2 = new Tag();
        t2.setTitle("Tag 2");
        em.persist(t2);

        return List.of(t1, t2);
    }

    @Transactional
    List<Category> createCategories() {
        Category c1 = new Category();
        c1.setTitle("Category 1");
        em.persist(c1);

        Category c2 = new Category();
        c2.setTitle("Category 2");
        em.persist(c2);

        return List.of(c1, c2);
    }

}
