package com.yongq.s_dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SLoginDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isValidUser(String stu_id, String stu_pw) {
		boolean retVal;
		try {
			String SQL = "select count(*) from student where stu_id = ? and stu_pw = ?";
			int count = jdbcTemplate.queryForInt(SQL, new Object[] {stu_id, stu_pw});
			
			if(count == 1) {
				retVal = true;
			} else {
				retVal = false;
			}
		} catch(Exception ex) {
			retVal = false;
		}
		return retVal;
	}
}
