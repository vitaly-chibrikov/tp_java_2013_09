package main;

import dao.UserDataSetDAO;
import dataSets.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@CreatedBy(author = "tully", date = "11.12.13")
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        SessionFactory sessionFactory = createSessionFactory(configuration);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.append(transaction.getLocalStatus().toString()).append('\n');
        session.close();

        UserDataSetDAO dao = new UserDataSetDAO(sessionFactory);
        dao.save(new UserDataSet("tully"));
        dao.save(new UserDataSet("sully"));

        UserDataSet dataSet = dao.read(1);
        System.out.append("User id: " + dataSet.getId() + " User name: " + dataSet.getName() + '\n');

        dataSet = dao.readByName("sully");
        System.out.append("User id: " + dataSet.getId() + " User name: " + dataSet.getName() + '\n');
    }

    public static SessionFactory createSessionFactory(Configuration configuration) {
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Lecture8");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
