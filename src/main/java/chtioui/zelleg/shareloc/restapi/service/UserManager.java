package chtioui.zelleg.shareloc.restapi.service;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletConfig;

import chtioui.zelleg.shareloc.restapi.model.Collocation;
import chtioui.zelleg.shareloc.restapi.model.CollocationUser;
import chtioui.zelleg.shareloc.restapi.model.Service;
import chtioui.zelleg.shareloc.restapi.model.User;
//import io.swagger.jaxrs.config.BeanConfig;
import jakarta.ws.rs.core.Context;

public class UserManager {
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


Connection con;

	
	public UserManager(){
		
		try {
			String url =String.format("jdbc:mysql://localhost:3306/Shareloc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			String uname ="root";
			String pwd = "";
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pwd);
			
		} catch(Exception e) {
			System.out.println(e +"data insert unsuccess.");
		}
	}
	
	/*
	// swagger
	public void MyApplication(@Context ServletConfig servletConfig) {
		  
		  BeanConfig beanConfig = new BeanConfig();
		 
		  beanConfig.setVersion("1.0.0");
		  beanConfig.setTitle("ShareLoc API");
		  beanConfig.setBasePath("/shareloc/api");
		  beanConfig.setResourcePackage("com.synaptik.javaee");
		  beanConfig.setScan(true);
	} 

	*/
	
	
	public User insertUser(User user) {
		String insert = "insert into user(email,password,firstname,lastname,score) values(?,?,?,?,?) ";
		
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setLong(5, user.getScore());
			
			ps.execute();
		}catch(Exception e) {
			System.out.println(e +"data insert unsuccess.");
		}
		
		return user;
		
	}


	public String checkUser(String email,String password) throws SQLException {
		// TODO Auto-generated method stub

		
		ArrayList<User> data = new ArrayList<User>();
		
		String select = "select * from user where email='"+email+"' and password='"+password+"'";
		PreparedStatement ps = con.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		String token = null;
		
		if(rs.next()) {
			token=issueToken(email);
			long time =System.currentTimeMillis()/1000;
			String insert = "update user set token=?  where email='"+email+"' and password='"+password+"'" ;
			
			try {
				PreparedStatement ps2 = con.prepareStatement(insert);
				ps2.setString(1, token);
				
				
				
				ps2.execute();
			}catch(Exception e) {
				System.out.println(e +"data insert unsuccess.");
			}
			return token;
		}
		
		return token;
	}
private String issueToken(String email) {
		
		byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}

public Collocation createCollocation(Collocation collocation , String id) {
	String insert = "insert into collocation(name,iduser) values(?,?) ";
	
	try {
		PreparedStatement ps = con.prepareStatement(insert);
		ps.setString(1, collocation.getName());
		ps.setString(2, id);
		
		
		ps.execute();
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return collocation;
	
}

public Service createService(Service service,String id) {
	
	String insert = "insert into service(title,description,cost,idcollocation,admin) values(?,?,?,?,?) ";
	
	try {
		PreparedStatement ps = con.prepareStatement(insert);
		
		System.out.println(ps);
		ps.setString(1, service.getTitle());
		ps.setString(2, service.getDescription());
		ps.setLong(3, service.getCost());
		ps.setLong(4, service.getIdcollocation());
		ps.setString(5,id);
		
		
		
		ps.execute();
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return service;
	
}


public String deleteService(String title, String id) {
	String delete = "delete from service where title='"+title+"' and admin ='"+id+"'";
	try {
		PreparedStatement ps = con.prepareStatement(delete);
		ps.execute();
	}catch(Exception e){
		System.out.println(e +"data insert unsuccess.");
		
	}
	return delete;
}


public String deleteCollocation(String name, String id) {
	String delete = "delete from collocation where name='"+name+"' and idUser ='"+id+"'";
	try {
		PreparedStatement ps = con.prepareStatement(delete);
		ps.execute();
	}catch(Exception e){
		System.out.println(e +"data insert unsuccess.");
		
	}
	return delete;
}


public Service updateService(Service service,String id)  {
	
	String title = service.getTitle();
	String description = service.getDescription();
	int cost = service.getCost();
	
	String update = "update service set title='"+title+"', description='"+description+"' ,cost="+cost+" where admin ='"+id+"' ";
	
	try {
		PreparedStatement ps = con.prepareStatement(update);
		
		System.out.println(ps);
		//ps.setString(1, service.getTitle());
		//ps.setString(2, service.getDescription());
		//ps.setLong(3, service.getCost());
		
		
		ps.execute();
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return service;
	
}


public Collocation updateCollocation(Collocation collocation, String id) {
	String name = collocation.getName();

	String update = "update collocation set name='"+name+"' where idUser ='"+id+"' ";
	
	try {
		PreparedStatement ps = con.prepareStatement(update);
		
		System.out.println(ps);
		//ps.setString(1, service.getTitle());
		//ps.setString(2, service.getDescription());
		//ps.setLong(3, service.getCost());
		
		
		ps.execute();
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return collocation;
}


public User updateUser(User user, String id) {
	String email = user.getEmail();	
	String password = user.getPassword();
	String firstname = user.getFirstname();
	String lastname= user.getLastname();

	String update = "update user set email='"+email+"', password='"+password+"', firstname='"+firstname+"', lastname='"+lastname+"' where iduser ='"+id+"' ";
	
	try {
		PreparedStatement ps = con.prepareStatement(update);
		
		System.out.println(ps);
		//ps.setString(1, service.getTitle());
		//ps.setString(2, service.getDescription());
		//ps.setLong(3, service.getCost());
		
		
		ps.execute();
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return user;
}


public String deleteUser(String id) {
	String delete = "delete from user where iduser ='"+id+"'";
	try {
		PreparedStatement ps = con.prepareStatement(delete);
		ps.execute();
	}catch(Exception e){
		System.out.println(e +"data insert unsuccess.");
		
	}
	return delete;
}


public CollocationUser addUserintoCollocation(CollocationUser collocationuser, String id) {
	int collocationid= collocationuser.getIdcollocation();
	int userid = collocationuser.getIduser();
	
    String select = "select idUser from collocation where idUser ='"+id+"' and idCollocation ="+collocationid+"";
	
	try {
		PreparedStatement ps = con.prepareStatement(select);		
		ps.execute();
		System.out.println(ps);
		System.out.println(ps.getMaxRows());
		String insert = "insert into user_collocation(Collocationid,Userid) values(?,?)";
		try {
			
			PreparedStatement psinsert = con.prepareStatement(insert);	
			psinsert.setLong(1, collocationid);
			psinsert.setLong(2, userid);
			
			psinsert.execute();
			
			
		}catch(Exception e) {
			System.out.println(e +"data insert unsuccess.");
		}
	}catch(Exception e) {
		System.out.println(e +"data insert unsuccess.");
	}
	
	return collocationuser;
	
}

}

