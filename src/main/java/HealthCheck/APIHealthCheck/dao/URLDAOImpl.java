package HealthCheck.APIHealthCheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import HealthCheck.APIHealthCheck.model.URL;

public class URLDAOImpl implements URLDAO {
	private JdbcTemplate jdbcTemplate;

	public URLDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int saveOrUpdate(URL url) {
		int rowsAffected = 0;
		if (url.getId() > 0) {
			// update
			String sql = "UPDATE url SET api_id=?, url=? WHERE url_id=?";
			rowsAffected = jdbcTemplate.update(sql, url.getApiId(), url.getUrl(), url.getId());
		} else {
			// insert
			String sql = "INSERT INTO url (api_id,url)" + " VALUES (?,?)";
			rowsAffected = jdbcTemplate.update(sql, url.getApiId(), url.getUrl());
		}
		return rowsAffected;
	}

	@Override
	public int delete(int urlId) {
		String sql = "DELETE FROM url WHERE url_id=?";
		int rowsAffected = jdbcTemplate.update(sql, urlId);
		return rowsAffected;
	}

	@Override
	public List<URL> list() {
		String sql = "SELECT * FROM url";
		List<URL> listContact = jdbcTemplate.query(sql, new RowMapper<URL>() {

			@Override
			public URL mapRow(ResultSet rs, int rowNum) throws SQLException {
				URL aUrl = new URL();

				aUrl.setId(rs.getInt("url_id"));
				aUrl.setApiId(rs.getInt("api_id"));
				aUrl.setUrl(rs.getString("url"));

				return aUrl;
			}

		});

		return listContact;
	}

	@Override
	public URL get(int urlId) {
		String sql = "SELECT * FROM url WHERE url_id= " + urlId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<URL>() {

			@Override
			public URL extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					URL url = new URL();
					url.setId(rs.getInt("url_id"));
					url.setApiId(rs.getInt("api_id"));
					url.setUrl(rs.getString("url"));

					return url;
				}

				return null;
			}

		});
	}

	@Override
	public List<URL> listByApi(int apiID) {
		String sql = "SELECT * FROM url WHERE api_id= " + apiID;
		List<URL> listContact = jdbcTemplate.query(sql, new RowMapper<URL>() {

			@Override
			public URL mapRow(ResultSet rs, int rowNum) throws SQLException {
				URL aUrl = new URL();

				aUrl.setId(rs.getInt("url_id"));
				aUrl.setApiId(rs.getInt("api_id"));
				aUrl.setUrl(rs.getString("url"));

				return aUrl;
			}

		});

		return listContact;
	}

}
