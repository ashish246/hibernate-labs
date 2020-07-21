package com.hibernate.main;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class HibernateNativeSQL {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

		// Get All Employees
		SQLQuery query = session
				.createSQLQuery("select emp_id, emp_name, emp_salary from Employee");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			Employee emp = new Employee();
			emp.setId(Long.parseLong(row[0].toString()));
			emp.setName(row[1].toString());
			emp.setSalary(Double.parseDouble(row[2].toString()));
			System.out.println(emp);
		}

		// Get All Employees - addScalar example
		query = session
				.createSQLQuery(
						"select emp_id, emp_name, emp_salary from Employee")
				.addScalar("emp_id", new LongType())
				.addScalar("emp_name", new StringType())
				.addScalar("emp_salary", new DoubleType());
		rows = query.list();
		for (Object[] row : rows) {
			Employee emp5 = new Employee();
			emp5.setId(Long.parseLong(row[0].toString()));
			emp5.setName(row[1].toString());
			emp5.setSalary(Double.parseDouble(row[2].toString()));
			System.out.println(emp5);
		}

		// Native SQL with multiple tables. If we would like to get data from
		// both Employee and Address tables, we can simply write the SQL query
		// for that and parse the result set.
		query = session
				.createSQLQuery("select e.emp_id, emp_name, emp_salary,address_line1, city, zipcode from Employee e, Address a where a.emp_id=e.emp_id");
		rows = query.list();
		for (Object[] row : rows) {
			Employee emp6 = new Employee();
			emp6.setId(Long.parseLong(row[0].toString()));
			emp6.setName(row[1].toString());
			emp6.setSalary(Double.parseDouble(row[2].toString()));
			Address address = new Address();
			address.setAddressLine1(row[3].toString());
			address.setCity(row[4].toString());
			address.setZipcode(row[5].toString());
			emp6.setAddress(address);
			System.out.println(emp6);
		}

		// Join example with addEntity and addJoin. We can also use addEntity()
		// and addJoin() methods to fetch the data from associated table using
		// tables join.
		query = session
				.createSQLQuery(
						"select {e.*}, {a.*} from Employee e join Address a ON e.emp_id=a.emp_id")
				.addEntity("e", Employee.class).addJoin("a", "e.address");
		rows = query.list();
		for (Object[] row : rows) {
			for (Object obj : row) {
				System.out.print(obj + "::");
			}
			System.out.println("\n");
		}
		// Above join returns both Employee and Address Objects in the array
		for (Object[] row : rows) {
			Employee e = (Employee) row[0];
			System.out.println("Employee Info::" + e);
			Address a = (Address) row[1];
			System.out.println("Address Info::" + a);
		}

		// Native SQL with parameters using index (?)
		query = session
				.createSQLQuery("select emp_id, emp_name, emp_salary from Employee where emp_id = ?");
		List<Object[]> empData = query.setLong(0, 1L).list();
		for (Object[] row : empData) {
			Employee emp6 = new Employee();
			emp6.setId(Long.parseLong(row[0].toString()));
			emp6.setName(row[1].toString());
			emp6.setSalary(Double.parseDouble(row[2].toString()));
			System.out.println(emp6);
		}

		// Native SQL with parameters using name | Embedding oracle hints using
		String hql = "/*+ INDEX(employee id) */ select emp_id, emp_name, emp_salary from Employee where emp_id = :id";

		query = session.createSQLQuery(hql);
		empData = query.setLong("id", 2L).list();
		for (Object[] row : empData) {
			Employee emp7 = new Employee();
			emp7.setId(Long.parseLong(row[0].toString()));
			emp7.setName(row[1].toString());
			emp7.setSalary(Double.parseDouble(row[2].toString()));
			System.out.println(emp7);
		}

		// rolling back to save the test data
		tx.commit();

		// closing hibernate resources
		sessionFactory.close();
	}

}