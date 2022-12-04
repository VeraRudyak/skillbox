import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new  StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From " + ShoppingList.class.getSimpleName() ;
        List<ShoppingList> courseList = session.createQuery(hql).getResultList();


        for (ShoppingList p : courseList) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

            linkedPurchaseList.setId(new LinkedPurchaseListKey(p.getStudent().getId(), p.getCourse().getId()));
            linkedPurchaseList.setStudentName(p.getStudent().getName());
            linkedPurchaseList.setCourseName(p.getCourse().getName());
            linkedPurchaseList.setPrice(p.getPrice());
            linkedPurchaseList.setSubscriptionDate(p.getSubscriptionDate());
            session.saveOrUpdate(linkedPurchaseList);
        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}

