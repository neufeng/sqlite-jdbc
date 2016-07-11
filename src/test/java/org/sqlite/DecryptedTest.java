package org.sqlite;

import static org.junit.Assert.assertEquals;
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
			String filePath = "/Users/zhangjun/Downloads/xypx/ershouchesanji.db";
			String password = "123456";
			
			Class.forName("org.sqlite.JDBC");
			
			String outputPath = "/Users/zhangjun/Downloads/xypx/test.db";
			File file = File.createTempFile("test", ".db");
			
			String url = "jdbc:sqlite:" + file.getAbsolutePath();
			Connection conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String sql = String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';", filePath, password);
			st.execute(sql);
			st.execute("SELECT sqlcipher_export('encrypted');");
			st.execute("DETACH DATABASE encrypted;");
			
			file.renameTo(new File(outputPath));
			
//			Properties props = new Properties();
//	        props.put( "key", password );
//	        Connection conn = DriverManager.getConnection( url, props );
//	        Statement st = conn.createStatement();
//	        ResultSet rs = st.executeQuery( "select * from t_question where type = 1 limit 1" );
//	        while (rs.next()) {
//	        	System.out.println(rs.getString("title"));
//	        	System.out.println(rs.getString("answer_array"));
//	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
