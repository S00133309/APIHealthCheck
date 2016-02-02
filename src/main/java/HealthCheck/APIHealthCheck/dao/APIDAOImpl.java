package HealthCheck.APIHealthCheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import HealthCheck.APIHealthCheck.model.API;

public class APIDAOImpl implements APIDAO {
	private JdbcTemplate jdbcTemplate;

	public APIDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(API api) {
		int rowsAffected = 0;
		if (api.getId() > 0) {
			// update
			String sql = "UPDATE api SET name=?, time=?, currentstatus=? WHERE api_id=?";
			rowsAffected = jdbcTemplate.update(sql, api.getName(), api.getTime(), api.getCurrentStatus(), api.getId());
		} else {
			// insert
			String sql = "INSERT INTO api (name, time, currentstatus)" + " VALUES (?,?,?)";
			rowsAffected = jdbcTemplate.update(sql, api.getName(), api.getTime(),api.getCurrentStatus());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int apiId) {
		String sql = "DELETE FROM api WHERE api_id=?";
		int rowsAffected = jdbcTemplate.update(sql, apiId);
		return rowsAffected;
	}

	@Override
	public List<API> list() {
		String sql = "SELECT * FROM api";
		List<API> listContact = jdbcTemplate.query(sql, new RowMapper<API>() {

			@Override
			public API mapRow(ResultSet rs, int rowNum) throws SQLException {
				API anApi = new API();

				anApi.setId(rs.getInt("api_id"));
				anApi.setName(rs.getString("name"));
				anApi.setTime(rs.getInt("time"));
				anApi.setCurrentStatus(rs.getString("currentstatus"));
				return anApi;
			}

		});

		return listContact;
	}

	@Override
	public API get(int apiId) {
		String sql = "SELECT * FROM api WHERE api_id=" + apiId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<API>() {

			@Override
			public API extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					API api = new API();
					api.setId(rs.getInt("api_id"));
					api.setName(rs.getString("name"));
					api.setTime(rs.getInt("time"));
					api.setCurrentStatus(rs.getString("currentstatus"));
					return api;
				}

				return null;
			}

		});
	}
}
