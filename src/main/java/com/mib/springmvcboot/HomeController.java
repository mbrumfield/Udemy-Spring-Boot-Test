package com.mib.springmvcboot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mib.springmvcboot.model.User;

@Controller
public class HomeController { 
	
	@RequestMapping("/")
	public String home() {
		return "index"; 
	}
	
	@RequestMapping("allusers")
	public String allUsers() {
		return "allusers"; 
	}

	@RequestMapping("searchbyid")
	public String searchbyid() {
		return "searchbyid"; 
	}
	
	@RequestMapping("searchbyname")
	public String searchbyname() {
		return "searchbyname"; 
	}
	
	@RequestMapping("adduser")
	public String adduser() {
		return "adduser"; 
	}
	
	@RequestMapping("deleteuser")
	public String deleteuser() {
		return "deleteuser"; 
	}
	
	@ModelAttribute
	public void modelData(Model m) {
		m.addAttribute("name", "Users");
	}
	
	@GetMapping("getUsers")
	public String getUsers(Model m) {
		
		String resultString = ""; 
				
		List<User> users = new ArrayList<User>(); 
		
		String query = "select * from Users"; 
		
		ResultSet rs = null; 
		Statement stmt = null; 
		Connection db = null; 
		try {
			db = getDBConnection(); 
			stmt = db.createStatement(); 
			rs = stmt.executeQuery(query); 
			while(rs.next()) {
				User u = new User(); 
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				users.add(u);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null; 
				}
				
				if(stmt != null) {
					stmt.close();
					stmt = null; 
				}
				
				if(db != null) {
					db.close();
					db = null;
				}
			} catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
		
		for(User u: users) {
			resultString += u.getName() + " (" + u.getId() + ")<br>"; 
		}
		 
		m.addAttribute("result", resultString);
		
		return "allusers";
	}
	
	@GetMapping("getUser")
	public String getUser(@RequestParam String id, Model m) {
		
		String resultString = ""; 
				
		String query = "select * from users where id = ?"; 
		
		ResultSet rs = null; 
		Statement stmt = null; 
		Connection db = null; 
		try {
			db = getDBConnection(); 
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, id);
			rs = statement.executeQuery(); 
			if(rs.next()) {
				User u = new User(); 
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				resultString += u.getName() + " (" + u.getId() + ")<br>"; 
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null; 
				}
				
				if(stmt != null) {
					stmt.close();
					stmt = null; 
				}
				
				if(db != null) {
					db.close();
					db = null;
				}
			} catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
		 
		m.addAttribute("result", resultString);
		
		return "searchbyid";
	}
	
	@PostMapping("addUser")
	public String addUser(@ModelAttribute User user, Model m) {
		
		String resultString = ""; 
		
		String query = "insert into users (id, name) values (?,?)"; 
		
		ResultSet rs = null; 
		Statement stmt = null; 
		Connection db = null;
		
		try {
			String id = user.getId(); 
			String name = user.getName(); 
			db = getDBConnection(); 
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.executeUpdate(); 
			resultString += "User added"; 
		} catch(Exception e) {
			e.printStackTrace();
			resultString += "Error: user not added";
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null; 
				}
				
				if(stmt != null) {
					stmt.close();
					stmt = null; 
				}
				
				if(db != null) {
					db.close();
					db = null;
				}
			} catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
		 
		m.addAttribute("result", resultString);
		
		return "adduser";
	}
	
	@GetMapping("getUserByName")
	public String getUserByName(@RequestParam String name, Model m) {
		
		String resultString = ""; 
		
		List<User> users = new ArrayList<User>();
		
		String query = "select * from users where name = ?"; 
		
		ResultSet rs = null; 
		Statement stmt = null; 
		Connection db = null; 
		try {
			db = getDBConnection(); 
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, name);
			rs = statement.executeQuery(); 
			while(rs.next()) {
				User u = new User(); 
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				users.add(u); 
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null; 
				}
				
				if(stmt != null) {
					stmt.close();
					stmt = null; 
				}
				
				if(db != null) {
					db.close();
					db = null;
				}
			} catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
		
		for(User u: users) {
			resultString += u.getName() + " (" + u.getId() + ")<br>"; 
		}
		 
		m.addAttribute("result", resultString);
		
		return "searchbyname";
	}
	
	/*
	 * TODO
	 * Add a method to removed a user by id.
	 */
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	public String deleteUser(@RequestParam String id, Model m) {
		
		String resultString = ""; 
		
		String query = "delete from users where id = ?"; 
		
		ResultSet rs = null; 
		Statement stmt = null; 
		Connection db = null;
		
		try {
			db = getDBConnection(); 
			PreparedStatement statement = db.prepareStatement(query);
			statement.setString(1, id);
			statement.executeUpdate(); 
			resultString += "User " + id + " deleted"; 
		} catch(Exception e) {
			e.printStackTrace();
			resultString += "Error: user does not exist"; 
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null; 
				}
				
				if(stmt != null) {
					stmt.close();
					stmt = null; 
				}
				
				if(db != null) {
					db.close();
					db = null;
				}
			} catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
		 
		m.addAttribute("result", resultString);
		
		return "deleteuser";
	}
	
	
	public Connection getDBConnection() throws NamingException, SQLException 
	{
		InitialContext initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL");
		
		return ds.getConnection();
	}

}
 