import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.NullPointerException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;





public class Main{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, SQLException,NullPointerException{
		Connection con=null;
		Statement st=null;
		ResultSet rs = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		int option;
		String name;
		String surname;
		int id;
		String gender;
		String dob;
		String status;
		Scanner sc=new Scanner(System.in);
		
		String connectionUrl = "jdbc:postgresql://localhost:5432/students";
	    Class.forName("org.postgresql.Driver");
	    System.out.println("Choose one option");
	    System.out.println("1. Show students list");
	    System.out.println("2. Show courses");
	    System.out.println("3. Add student");
	    System.out.println("4. Show student's grade");
	    try {
	  	   con = DriverManager.getConnection(connectionUrl,"postgres","123");
	  	 st=con.createStatement();
	  	 option =sc.nextInt();
	  	 if(option==1) {
	  	 rs=st.executeQuery("select * from students_list");
		
	  	System.err.println("Students list:");
        while(rs.next()) 
     	   System.out.println("Student's ID-"+rs.getInt("id")+". Name-"+rs.getString("firstname")+". Surname-"+rs.getString("surname")+". Gender-"+rs.getString("gender")+". Date of birth-"+rs.getDate("dob")+". Status-"+rs.getString("status"));
	  	 }
	  	 else if(option==2) {
	  		
	    rs1=st.executeQuery("select * from courses");
	    System.err.println("Info about student's courses");
	    while(rs1.next())
	    	System.out.println("Student name-"+rs1.getString("student_name")+". Course-"+rs1.getString("course")+". Teacher-"+rs1.getString("teacher"));
	  	 }
	  	 else if(option==3) {
	  		 System.out.println("Type name");
	  		name=sc.next();
	  		 System.out.println("Type surname");
	  		 surname=sc.next();
	  		System.out.println("Type gender");
	  		gender=sc.next();
	  		System.out.println("Type date of birth yyyy-MM-dd");
	  		dob=sc.next();
	  		System.out.println("Type status");
	  		status=sc.next();
	  		rs2=st.executeQuery("insert into students_list(firstname,surname,gender,dob,status) values ('"+name+"','"+surname+"','"+gender+"','"+dob+"','"+status+"')");
	  		}
	  	 else if(option==4) {
	  		 System.out.println("Type student's id");
	  		 id=sc.nextInt();
	  		 rs3=st.executeQuery("select * from grades where (id="+"'"+id+"'"+")");
	  		 while(rs3.next()) {
	  			 System.out.println("ID-"+rs3.getInt("id")+". Student name-"+rs3.getString("name")+". Java's grade="+rs3.getDouble("java")+". Calc grade="+rs3.getDouble("calculus_2")+". History grade="+rs3.getDouble("history")+". WEB grade="+rs3.getDouble("web_development")+". English grade="+rs3.getDouble("english"));
	  		 }
	  	 }

	    }catch(Exception e) {
	  	   System.out.println(e);
	    }finally {
	 	   try {
	 		  st.close();
	 		 con.close();
	 		   rs.close();
	 		 
	 		   rs1.close();
	 		   rs2.close();
	 		   rs3.close();
	 		   
	 	   }catch(SQLException throwables) {
	 		   throwables.printStackTrace();
	 	   }
	    }
	}
}
	
	
	
