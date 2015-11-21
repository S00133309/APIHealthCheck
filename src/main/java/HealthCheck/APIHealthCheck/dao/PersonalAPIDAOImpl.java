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
import HealthCheck.APIHealthCheck.model.PersonalAPI;

public class PersonalAPIDAOImpl implements PersonalAPIDAO {
	private JdbcTemplate jdbcTemplate;

	public PersonalAPIDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(PersonalAPI personalApi) {
		int rowsAffected = 0;
		if (personalApi.getId() > 0) {
			// update
			String sql = "UPDATE personal_api SET person_id=?, api_id=? WHERE pa_id=?";
			rowsAffected = jdbcTemplate.update(sql, personalApi.getPersonId(), personalApi.getApiId(),personalApi.getId());
		} else {
			// insert
			String sql = "INSERT INTO personal_api (person_id, sapi_idname)" + " VALUES (?, ?)";
			rowsAffected = jdbcTemplate.update(sql, personalApi.getPersonId(), personalApi.getApiId());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int personalApiId) {
		String sql = "DELETE FROM personal_api WHERE pa_id=?";
		int rowsAffected = jdbcTemplate.update(sql, personalApiId);
		return rowsAffected;
	}

	@Override
	public List<PersonalAPI> list() {
		String sql = "SELECT * FROM personal_api";
		List<PersonalAPI> listContact = jdbcTemplate.query(sql, new RowMapper<PersonalAPI>() {

			@Override
			public PersonalAPI mapRow(ResultSet rs, int rowNum) throws SQLException {
				PersonalAPI aPersonalAPI = new PersonalAPI();

				aPersonalAPI.setId(rs.getInt("pa_id"));
				aPersonalAPI.setPersonId(rs.getInt("person_id"));
				aPersonalAPI.setApiId(rs.getInt("api_id"));

				return aPersonalAPI;
			}

		});

		return listContact;
	}

	@Override
	public PersonalAPI get(int personalApiId) {
		String sql = "SELECT * FROM personal_api WHERE pa_id=" + personalApiId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<PersonalAPI>() {

			@Override
			public PersonalAPI extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PersonalAPI personalApi = new PersonalAPI();
					personalApi.setId(rs.getInt("pa_id"));
					personalApi.setPersonId(rs.getInt("person_id"));
					personalApi.setApiId(rs.getInt("api_id"));
					return personalApi;
				}

				return null;
			}

		});
	}
}
