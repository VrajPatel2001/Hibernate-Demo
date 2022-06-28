package org.learn.hibernate.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.learn.hibernate.dataSource.DataSource;
import org.learn.hibernate.domain.Employee;



import java.util.List;
import java.util.Queue;

public class EmployeeImplementation implements EmployeeDao{

    @Override
    public List<Employee> selectAll() {
        Session session = DataSource.getSessionFactory().openSession();
        Transaction tx = null;

        List<Employee> allEmployee = null;

        try {
            tx = session.beginTransaction();
            allEmployee = session.createQuery("FROM Employee", Employee.class).getResultList();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return allEmployee;

    }

    @Override
    public void insertOne(Employee employee) {
        Transaction tx = null;

        try (Session session = DataSource.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateOne(int employeeId, String name) {
        Session session = DataSource.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee e = session.createQuery("FROM Employee where id = :employeeId",Employee.class).setParameter("employeeId",employeeId).getSingleResult();
            e.setName(name);
            session.update(e);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteOne(int id) {
        Session session = DataSource.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Employee where id=:id");
            query.setParameter("id",id);
            int result = query.executeUpdate();
            System.out.println(result);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
