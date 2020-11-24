package com.example.demo;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.google.gson.Gson;

import java.sql.*;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class HttpControllerREST extends HttpServlet{

	int i=0;//counter for categorys

	@RequestMapping("/category/{categoryCode}")
	public String getCategory(@PathVariable("categoryCode") String categoryCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = DBConnecter.getDBConnection();
			Statement st = conn.createStatement();
			ArrayList<Product> productArr = new ArrayList<Product>();
			int catCode=Integer.parseInt(categoryCode);
			String SQ = "SELECT * FROM \"products\" WHERE (type="+catCode+");";
			ResultSet s = st.executeQuery(SQ);
			while(s.next()) {
				Product EX = new Product();
				EX.setCode(s.getInt(1));
				EX.setName(s.getString(2));
				EX.setPrice(s.getInt(3));
				EX.setPicture(s.getString(4));
				EX.setDescription(s.getString(5));
				EX.setType(s.getInt(6));
				productArr.add(EX);
				System.out.println(EX.getName()+" - добавлено!");
			}
			conn.commit();
			st.close();
			conn.close();
			Gson G = new Gson();
			i++;
			System.out.println(G.toJson(productArr)+"\n****************\n"+i);
			return G.toJson(productArr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return "OK";
	}
	
	
	@RequestMapping("/categorys")
	public String allCategorys(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = DBConnecter.getDBConnection();
			Statement st = conn.createStatement();
			ArrayList<ProductType> categoryArr = new ArrayList<ProductType>();
			String SQ = "SELECT * FROM \"types\" ORDER BY id;";
			ResultSet s = st.executeQuery(SQ);
			while(s.next()) {
				ProductType EX = new ProductType();
				EX.setName(s.getString(1));
				EX.setType(s.getInt(2));
				categoryArr.add(EX);
				System.out.println(EX.getName()+" - добавлено!");
			}
			conn.commit();
			st.close();
			conn.close();
			Gson G = new Gson();
			i++;
			System.out.println(G.toJson(categoryArr)+"\n****************\n"+i);
			return G.toJson(categoryArr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return "OK";
	}
	
	
	@RequestMapping("/createProduct")
	public String insert (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Required");
		if(!request.getParameter("name").equals("")&&!request.getParameter("type").equals("")&&!request.getParameter("picture").equals("")&&!request.getParameter("price").equals("")&&!request.getParameter("code").equals("")&&!request.getParameter("description").equals("")) {
			try {
				Connection conn = DBConnecter.getDBConnection();
				
				String 	name = request.getParameter("name"),
						picture = request.getParameter("picture"),
						description = request.getParameter("description");
				
				int 	price = Integer.parseInt(request.getParameter("price")),
						code = Integer.parseInt(request.getParameter("code")),
						type = Integer.parseInt(request.getParameter("type"));
				
				Product p = new Product();
				p.setCode(code);
				p.setDescription(description);
				p.setName(name);
				p.setPicture(picture);
				p.setType(type);
				
				
				Statement st = conn.createStatement();
				String SQ = "INSERT INTO \"products\" (pcode, name, price, picture, description, type) VALUES ("+code+",'"+name+"',"+price+", '"+picture+"','"+description+"',"+type+");";
				st.execute(SQ);
				System.out.println("Inserted!");
				conn.commit();
				st.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "SQL Exeption!";
			}
			
			
		} return null;
	}
	
	
	
	@RequestMapping("/createType")
	public String insertType(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("name")!=null) {
			if(!request.getParameter("name").equals("")) {
				try {
					Connection conn = DBConnecter.getDBConnection();				
					String name = request.getParameter("name");

					Statement st = conn.createStatement();
					String SQ = "INSERT INTO \"types\" (name) VALUES ('"+name+"');";
					st.execute(SQ);
					System.out.println("Inserted!");
					SQ="SELECT id FROM types";
					ResultSet s = st.executeQuery(SQ);
					String ret=null;
					while(s.next()) {
						ret=""+s.getInt(1);
					}
					ret="Category "+name+" have "+ret+" no";
					conn.commit();
					st.close();
					conn.close();
					
					return ret;
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "SQL Exeption!";
				}
			} else {return null;}
		} else {return null;}
	}
	
	
	@RequestMapping("/repair")
	public String getRepair(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("Surname")!=null&&request.getParameter("Code")!=null) {
			if(!request.getParameter("Surname").equals("")&&!request.getParameter("Code").equals("")) {
				try {
					Connection conn = DBConnecter.getDBConnection();				
					String JSON, surname = request.getParameter("Surname"), code = request.getParameter("Code");
					String Q = "SELECT status FROM \"repairs\" WHERE (surname = '"+surname+"') AND (code = "+code+");";
					int status = 0;
					PreparedStatement st = conn.prepareStatement(Q);
					ResultSet Res = st.executeQuery();
					while(Res.next()){
						status=Res.getInt(1);
					}
					RepairStatus Repair = new RepairStatus();
					Repair.setStatus(status);
					Gson G = new Gson();
					JSON = G.toJson(Repair);
									
					return JSON;
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "SQL Exeption!";
				}
			} else {return null;}
		} else {return null;}
	}
	
	

	@RequestMapping("/products")
	public String getProduct(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("code")!=null) {
			if(!request.getParameter("code").equals("")) {
				try {
					Connection conn = DBConnecter.getDBConnection();				
					String code = request.getParameter("code");
					String Q = "SELECT products.pcode, products.name, laptops.cpu, laptops.gpu, laptops.ram FROM \"products\" INNER JOIN \"laptops\" ON products.pcode = laptops.code WHERE products.pcode="+code+";";
					int t = 0;
					String res="";
					PreparedStatement st = conn.prepareStatement(Q);
					ResultSet Res = st.executeQuery();
					while(Res.next()){
						System.out.println("Reading!");
						res=res+Res.getString(2)+"</br> "+Res.getString(3)+"</br> "+Res.getString(4);
						
					}
					
					return res;
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "SQL Exeption!";
				}
			} else {return null;}
		} else {return null;}
	}
	
	
	@RequestMapping("/createRepair")
	public String testInsert(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("name")!=null) {
			if(!request.getParameter("name").equals("")) {
				try {
					Connection conn = DBConnecter.getDBConnection();
					Statement st = conn.createStatement();
					String N = request.getParameter("name");
					String SQ = "INSERT INTO \"repairs\" (code, surname, status) VALUES (114,'"+N+"', 1);";
					st.execute(SQ);
					System.out.println("Inserted!");
					conn.commit();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		return "OK";
	}
}
