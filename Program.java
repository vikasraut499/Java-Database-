package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) throws Exception {
		
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://remotemysql.com:3306/1lAIHXc8Va";
			String username = "1lAIHXc8Va";
			String password = "TEMA3CTgGO";
			Class.forName(driver);
			
			Connection connect = DriverManager.getConnection(url,username,password);
			
			
			// creating table
			
			PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS STUDENT_TABLE(STUDENT_NO int(3) NOT NULL, STUDENT_NAME varchar(30),STUDENT_DOB DATE, STUDENT_DOJ DATE, PRIMARY KEY(STUDENT_NO));");
			create.executeUpdate();
			System.out.println("Table Created.");
			
			// 1. inserting records in the table
			Statement smt = connect.createStatement();
			
			String query = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (101,'Vikas Raut', '2000-01-18', '2000-01-18');";
			smt.executeUpdate(query);
			
			
			
			// inserting queries in batch
			String query5 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (102,'Raj Kumar', '2000-01-22', '2000-01-18');";
			String query6 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (103,'Sid Khurana', '2000-01-23', '2000-01-18');";
			String query1 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (104,'Ritesh Khanna', '2000-01-18', '2000-01-18');";
			String query2 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (105,'John Cape', '2000-01-19', '2000-01-18');";
			String query3 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (106,'Akash Bhatiya', '2000-01-20', '2000-01-18');";
			String query4 = "INSERT INTO STUDENT_TABLE(STUDENT_NO, STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) values (107,'Anil Sharma', '2000-01-21', '2000-01-18');";
			
			smt.addBatch(query1);
			smt.addBatch(query2);
			smt.addBatch(query3);
			smt.addBatch(query4);
			smt.addBatch(query5);
			smt.addBatch(query6);
			
			smt.executeBatch();
			
			
			// showing inserted data in table
			
			query = "SELECT * FROM STUDENT_TABLE";
			ResultSet rs = smt.executeQuery(query);
			System.out.println("Data in Table:");
			
			while(rs.next()) {
				System.out.print(rs.getInt("STUDENT_NO") + " ");
				System.out.print(rs.getString("STUDENT_NAME") + " ");
				System.out.print(rs.getString("STUDENT_DOB") + " ");
				System.out.println(rs.getString("STUDENT_DOJ") + " ");
			}
			
			// 2. UPDATING DATA IN TABLE
			
			query = "UPDATE STUDENT_TABLE SET STUDENT_DOJ = '2000-01-21' WHERE STUDENT_NO in (101, 103);";
		    smt.executeUpdate(query);
		    
		    // SHOWING DATA IN TABLE AFTER UPDATE
		    query = "SELECT * FROM STUDENT_TABLE";
			rs = smt.executeQuery(query);
		    
		    System.out.println("\nUpdated Data in Table:");
			
			while(rs.next()) {
				System.out.print(rs.getInt("STUDENT_NO") + " ");
				System.out.print(rs.getString("STUDENT_NAME") + " ");
				System.out.print(rs.getString("STUDENT_DOB") + " ");
				System.out.println(rs.getString("STUDENT_DOJ") + " ");
			}
			
			
			// 3. DELETING ROW FROM A TABLE WHERE STUDENT_NO IS 101
			query = "DELETE FROM STUDENT_TABLE  WHERE STUDENT_NO = 101";
			smt.executeUpdate(query);
			
			
			// SHOWING DATA IN TABLE AFTER UPDATE
			query = "SELECT * FROM STUDENT_TABLE;";
			rs = smt.executeQuery(query);
		    System.out.println("\nAfter deleting first row in Table:");
			
			while(rs.next()) {
				System.out.print(rs.getInt("STUDENT_NO") + " ");
				System.out.print(rs.getString("STUDENT_NAME") + " ");
				System.out.print(rs.getString("STUDENT_DOB") + " ");
				System.out.println(rs.getString("STUDENT_DOJ") + " ");
			}
			
			
			// 4. GETTING LIST OF NAMES IN TABLE
			query = "SELECT STUDENT_NAME FROM STUDENT_TABLE;";
			rs = smt.executeQuery(query);
		    System.out.println("\nList of student in Table:");
			
			while(rs.next()) {
				System.out.print(rs.getString("STUDENT_NAME") + "\n");
				
			}
			
			//5. GETTING ONE STUDENT INFORMATION OF STUDENT ID = 101
			query = "SELECT * FROM STUDENT_TABLE WHERE STUDENT_NO=104";
			rs = smt.executeQuery(query);
			// PRINTING ROW FROM TABLE
			
		    System.out.println("\nOne Row student in Table:");
			rs.next();
			System.out.print(rs.getInt("STUDENT_NO") + " ");
			System.out.print(rs.getString("STUDENT_NAME") + " ");
			System.out.print(rs.getString("STUDENT_DOB") + " ");
			System.out.println(rs.getString("STUDENT_DOJ") + " ");
		

			}catch (Exception e){
				System.out.println(e);
			}
		
		
	}
}
	
	