package HealthCheck.APIHealthCheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import HealthCheck.APIHealthCheck.model.APIResult;

public class APIResultDAOImpl implements APIResultDAO {
	private JdbcTemplate jdbcTemplate;

	public APIResultDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(APIResult apiResult) {
		int rowsAffected = 0;
		if (apiResult.getId() > 0) {
			// update
			String sql = "UPDATE api_result SET api_id=?, date=?, status=? WHERE apiresult_id=?";
			rowsAffected = jdbcTemplate.update(sql, apiResult.getApiId(), apiResult.getDate(), apiResult.getStatus(), apiResult.getId());
		} else {
			// insert
			String sql = "INSERT INTO api_result (api_id, date, status) VALUES (?,?,?)";
			rowsAffected = jdbcTemplate.update(sql, apiResult.getApiId(), apiResult.getDate(), apiResult.getStatus());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int apiResultId) {
		String sql = "DELETE FROM api_result WHERE apiresult_id=?";
		int rowsAffected = jdbcTemplate.update(sql, apiResultId);
		return rowsAffected;
	}

	@Override
	public List<APIResult> list() {
		String sql = "SELECT * FROM api_result";
		List<APIResult> listContact = jdbcTemplate.query(sql, new RowMapper<APIResult>() {

			@Override
			public APIResult mapRow(ResultSet rs, int rowNum) throws SQLException {
				APIResult anApi = new APIResult();

				anApi.setId(rs.getInt("apiresult_id"));
				anApi.setApiId(rs.getInt("api_id"));
				anApi.setDate(rs.getDate("date"));
				anApi.setStatus(rs.getString("status"));
				return anApi;
			}

		});

		return listContact;
	}

	@Override
	public APIResult get(int apiResultId) {
		String sql = "SELECT * FROM api_result WHERE apiresult_id=" + apiResultId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<APIResult>() {

			@Override
			public APIResult extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					APIResult api = new APIResult();
					api.setId(rs.getInt("apiresult_id"));
					api.setApiId(rs.getInt("api_id"));
					api.setDate(rs.getDate("date"));
					api.setStatus(rs.getString("status"));
					return api;
				}

				return null;
			}

		});
	}
}
