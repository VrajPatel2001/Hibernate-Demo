package org.learn.hibernate;


import org.learn.hibernate.dao.EmployeeDao;
import org.learn.hibernate.dao.EmployeeImplementation;
import org.learn.hibernate.dataSource.DataSource;
import org.learn.hibernate.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;


public class HibernateApplication {
    public static void main(String[] args) throws SQLException {

        Logger logger = LoggerFactory.getLogger(HibernateApplication.class);

        EmployeeDao employeeDao = new EmployeeImplementation();
        Employee e1 = new Employee("Smit Patel");

        if(DataSource.getSessionFactory() != null)
        {
            System.out.println("Connect");
        }
        //employeeDao.insertOne(e1);
       List<Employee> allEmployee = employeeDao.selectAll();
       for(Employee e:allEmployee)
       {
           System.out.println(e);
       }
       employeeDao.updateOne(1,"Vraj Nileshbhai Patel");
        List<Employee> allEmployee2 = employeeDao.selectAll();
        for(Employee e:allEmployee2)
        {
            System.out.println(e);
        }

        employeeDao.deleteOne(2);

        System.out.println("All employee after delete");
        List<Employee> allEmployee1 = employeeDao.selectAll();

        for (Employee e:allEmployee1)
        {
            System.out.println(e);
        }
        DataSource.getSessionFactory().close();



    }
}
