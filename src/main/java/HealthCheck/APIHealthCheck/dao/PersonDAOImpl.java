package HealthCheck.APIHealthCheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import HealthCheck.APIHealthCheck.model.Person;

public class PersonDAOImpl implements PersonDAO {
	private JdbcTemplate jdbcTemplate;

	public PersonDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(Person person) {
		int rowsAffected = 0;
		if (person.getId() > 0) {
			// update
			String sql = "UPDATE person SET fname=?, sname=?, email=?, password=? WHERE person_id=?";
			rowsAffected = jdbcTemplate.update(sql, person.getFname(), person.getSname(), person.getEmail(), person.getPassword(), person.getId());
		} else {
			// insert
			String sql = "INSERT INTO person (fname, sname, email, password)" + " VALUES (?, ?, ?, ?)";
			rowsAffected = jdbcTemplate.update(sql, person.getFname(), person.getSname(), person.getEmail(), person.getPassword());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int personId) {
		String sql = "DELETE FROM person WHERE person_id=?";
		int rowsAffected = jdbcTemplate.update(sql, personId);
		return rowsAffected;
	}

	@Override
	public List<Person> list() {
		String sql = "SELECT * FROM person";
		List<Person> listContact = jdbcTemplate.query(sql, new RowMapper<Person>() {

			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person aPerson = new Person();

				aPerson.setId(rs.getInt("person_id"));
				aPerson.setFname(rs.getString("fname"));
				aPerson.setSname(rs.getString("sname"));
				aPerson.setEmail(rs.getString("email"));
				aPerson.setPassword(rs.getString("password"));

				return aPerson;
			}

		});

		return listContact;
	}

	@Override
	public Person get(int personId) {
		String sql = "SELECT * FROM person WHERE person_id=" + personId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Person>() {

			@Override
			public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Person person = new Person();
					person.setId(rs.getInt("person_id"));
					person.setFname(rs.getString("fname"));
					person.setSname(rs.getString("sname"));
					person.setEmail(rs.getString("email"));
					person.setPassword(rs.getString("password"));
					return person;
				}

				return null;
			}

		});
	}
}
