package Service;


import com.bogdan.model.Author;
import com.bogdan.model.Quotes;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityService {
    public static List<Author> getForAuthor(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Author a");
        List<Author> authors = Arrays.stream(query.getResultList().toArray()).map(obj -> (Author) obj)
                .collect(Collectors.toList());
        entityManager.close();
        entityManagerFactory.close();
        return authors;
    }

    public static List<Quotes> getForQuotes(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select q from Quotes q");
        List<Quotes> quotes = Arrays.stream(query.getResultList().toArray()).map(obj -> (Quotes) obj)
                .collect(Collectors.toList());
        entityManager.close();
        entityManagerFactory.close();
        return quotes;
    }

    public static Quotes getQuoteById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Quotes quote = entityManager.find(Quotes.class, id);

        entityManager.close();
        entityManagerFactory.close();

        return quote;
    }

    public static Author getAuthorById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Author author = entityManager.find(Author.class, id);

        entityManager.close();
        entityManagerFactory.close();

        return author;
    }

    public static void persistMergeQuote(Quotes quote) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Quotes quoteEntity = getQuoteById(quote.getId());

        persistMergeEntity(entityManager, transaction, quote, quoteEntity);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void persistMergeAuthor(Author author) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Author authorEntity = getAuthorById(author.getId());

        persistMergeEntity(entityManager, transaction, author, authorEntity);

        entityManager.close();
        entityManagerFactory.close();
    }

    private static <T> void persistMergeEntity(EntityManager entityManager, EntityTransaction transaction,
                                               T entity, T tableEntity) {
        transaction.begin();

        if (tableEntity == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        entityManager.flush();

        transaction.commit();
    }

    public static void deleteQuoteById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Quotes quote = entityManager.find(Quotes.class, id);
        entityManager.remove(quote);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void deleteAuthorById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void deleteQuoteByAuthorId(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AppPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Query query = entityManager.createQuery("select q from Quotes q where q.author = :author");
        query.setParameter("author", getAuthorById(id));
        List<Quotes> quotes = Arrays.stream(query.getResultList().toArray()).map(obj -> (Quotes) obj)
                .collect(Collectors.toList());

        for (Quotes quote : quotes) {
            transaction.begin();

            entityManager.remove(quote);

            transaction.commit();
        }

        entityManager.close();
        entityManagerFactory.close();
    }

}
