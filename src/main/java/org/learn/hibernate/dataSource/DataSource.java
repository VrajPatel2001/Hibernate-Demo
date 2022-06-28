package org.learn.hibernate.dataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.learn.hibernate.domain.Employee;

public class DataSource {
    static SessionFactory SESSION_FACTORY = null;

    public static SessionFactory getSessionFactory(){
        if (SESSION_FACTORY == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Employee.class);
                SESSION_FACTORY = configuration.buildSessionFactory();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return SESSION_FACTORY;
    }
}
