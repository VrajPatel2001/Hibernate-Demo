package org.learn.hibernate.dao;

import org.learn.hibernate.domain.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> selectAll();
    public void insertOne(Employee employee);
    public void updateOne(int id,String name);

    public void deleteOne(int id);
}

