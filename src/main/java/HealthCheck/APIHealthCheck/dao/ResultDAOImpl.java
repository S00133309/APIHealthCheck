package HealthCheck.APIHealthCheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import HealthCheck.APIHealthCheck.model.Result;

public class ResultDAOImpl implements ResultDAO {
	private JdbcTemplate jdbcTemplate;

	public ResultDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(Result result) {
		int rowsAffected = 0;
		if (result.getId() > 0) {
			// update
			String sql = "UPDATE result SET url_id=?, response_code=?, time_pinged=?, date_pinged=?, note=?, pa_id=? WHERE result_id=?";
			rowsAffected = jdbcTemplate.update(sql, result.getUrlId(), result.getResponseCode(), result.getTimePinged(),
					result.getDatePinged(), result.getNote(), result.getPaId(), result.getId());
		} else {
			// insert
			String sql = "INSERT INTO result (url_id, response_code, time_pinged, date_pinged, note, pa_id)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			rowsAffected = jdbcTemplate.update(sql, result.getUrlId(), result.getResponseCode(), result.getTimePinged(),
					result.getDatePinged(),  result.getNote(), result.getPaId());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int resultId) {
		String sql = "DELETE FROM result WHERE result_id=?";
		int rowsAffected = jdbcTemplate.update(sql, resultId);
		return rowsAffected;
	}

	@Override
	public List<Result> list() {
		String sql = "SELECT * FROM result";
		List<Result> listContact = jdbcTemplate.query(sql, new RowMapper<Result>() {

			@Override
			public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
				Result aResult = new Result();

				aResult.setId(rs.getInt("result_id"));
				aResult.setUrlId(rs.getInt("url_id"));
				aResult.setResponseCode(rs.getInt("response_code"));
				aResult.setTimePinged(rs.getTime("time_pinged"));
				aResult.setDatePinged(rs.getDate("date_pinged"));
				aResult.setNote(rs.getString("note"));
				aResult.setPaId(rs.getInt("pa_id"));
				return aResult;
			}

		});

		return listContact;
	}

	@Override
	public Result get(int resultId) {
		String sql = "SELECT * FROM result WHERE result_id=" + resultId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Result>() {

			@Override
			public Result extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Result aResult = new Result();
					aResult.setId(rs.getInt("result_id"));
					aResult.setResponseCode(rs.getInt("response_code"));
					aResult.setUrlId(rs.getInt("url_id"));
					aResult.setTimePinged(rs.getTime("time_pinged"));
					aResult.setDatePinged(rs.getDate("date_pinged"));
					aResult.setNote(rs.getString("note"));
					aResult.setPaId(rs.getInt("pa_id"));
					return aResult;
				}

				return null;
			}

		});
	}
}
