package dev.paie.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO grade (id, code, nbHeuresBase, tauxBase) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, nouveauGrade.getId(), nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase());
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE grade SET code = ? WHERE id = ?";
		BigDecimal newVal = new BigDecimal("100");
		jdbcTemplate.update(sql, newVal, grade.getId());
	}

	@Override
	public List<Grade> lister() {
		String sql = "SELECT * FROM grade";

		RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
			Grade grade = new Grade();
			grade.setId(rs.getInt("id"));
			grade.setCode(rs.getString("code"));
			grade.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
			grade.setTauxBase(rs.getBigDecimal("tauxBase"));
			return grade;
		};

		List<Grade> grades = jdbcTemplate.query(sql, mapper);
		return grades;
	}
}
