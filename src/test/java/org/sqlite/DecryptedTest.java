package org.sqlite;

import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.matchers.JUnitMatchers;

public class DecryptedTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String filePath = "test.db";
			String password = "123456";
			
			Class.forName("org.sqlite.JDBC");
			
			String url = "jdbc:sqlite:" + filePath;
			Properties props = new Properties();
	        props.put( "key", password );
	        Connection conn = DriverManager.getConnection( url, props );
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery( "select * from t_question where type = 1 limit 1" );
	        while (rs.next()) {
	        	System.out.println(rs.getString("title"));
	        	System.out.println(rs.getString("answer_array"));
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
