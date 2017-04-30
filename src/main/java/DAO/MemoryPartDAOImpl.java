package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import Entityes.MemoryPart;

public class MemoryPartDAOImpl implements MemoryPartDAO {

	public JdbcTemplate jdbcTemp;
	
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	public List<MemoryPart> getAll() {
		List<MemoryPart> memlist = jdbcTemp.query("select * from todo_table", new RowMapper<MemoryPart>(){
			public MemoryPart mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemoryPart mem = new MemoryPart();
				mem.setId(rs.getInt("todo_id"));
				mem.setFIO(rs.getString("todo_fio"));
				mem.setEvent(rs.getString("todo_event"));
				mem.setDate(rs.getDate("todo_date"));
				return mem;
			}
				});
		return memlist;
	}

	public MemoryPart getOne(int id) {
		MemoryPart mem = jdbcTemp.queryForObject(
				"select * from todo_table where id = ?", MemoryPart.class, id);
		return mem;
	}

	public void addOne(MemoryPart mem) {
		jdbcTemp.update(
				"insert into todo_table (todo_fio,todo_event,todo_date) values (?, ?, ?)",mem.getFIO(),mem.getEvent(),mem.getDate());
		
	}

	public void updateOne(MemoryPart mem) {
		jdbcTemp.update(
				"update todo_table set (todo_fio = ?, todo_event = ?, todo_date) where id = ?",mem.getFIO(),mem.getEvent(),mem.getDate());
		
	}

	public void deleteOne(int id) {
		jdbcTemp.update("delete from todo_table where id = ?", id);
		
	}



}
