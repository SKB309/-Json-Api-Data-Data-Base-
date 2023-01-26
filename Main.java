package com.afrah;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {
	SubMain subMain;
	Articles articles;
	Results results;
	Book books;
	static Connection connection;;
	
	String userName;

	static {
		
		String serverName = "localhost";

		String url = "jdbc:sqlserver://localhost:1433;databaseName=JsonApiData;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void mainMenue() {

		System.out.println("***********************************************************");
		System.out.println(" 1- connect To( Json Api Data )Data Base ");
		System.out.println(" 2- print json Api ");
		System.out.println(" 3- create Big Data Table ");
		System.out.println(" 4- insert Into Table Json Api Table");
		System.out.println(" 5- Select Top 5 ");
		System.out.println(" 6- delet by id");
		System.out.println(" 0- Exit ");
		System.out.println("***********************************************************");
	}

	public static void jsonApi() throws Throwable, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		System.out.println(" printing (API) information  ");

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=li9s7KAAbYij55AM3jqE8wM8AkLFVbEf")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.printf(response.body());

	}

	public static void connectToDataBase() throws Throwable, IllegalAccessException, ClassNotFoundException {

		Connection connection;

		try {

			// Create a connection to the database

			String serverName = "localhost";

			String url = "jdbc:sqlserver://localhost:1433;databaseName=JsonApiData;encrypt=true;trustServerCertificate=true";
			String user = "sa";
			String pass = "root";
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			connection = DriverManager.getConnection(url, user, pass);

			System.out.println("Successfully Connected to the database!" + " JsonApiData ");

		} catch (SQLException e) {

			System.out.println("Could not connect to the database " + e.getMessage());
		}

	}

	public static void createJsonDataTable() throws Throwable, IllegalAccessException, ClassNotFoundException {
		Connection conn;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JsonApiData;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		try (Connection conn1 = DriverManager.getConnection(url, user, pass);
				Statement stmt = conn1.createStatement();) {
			String sql = "CREATE TABLE JsonApiTable " + "( Id int PRIMARY KEY IDENTITY(1,1),"
					+ " author VARCHAR(1000),"
					+ "description VARCHAR(1000),"
					+ "price VARCHAR(1000),"
					+ "title VARCHAR(1000),"
					+ "rank INTEGER ,"
					+ "contributor VARCHAR(1000),"
					+ "amazon_product_url VARCHAR(1000),"
					+ "copyright VARCHAR(1000),"
					+ "published_date VARCHAR(1000) ,"
					+ "published_date_description VARCHAR(1000),"
					+ "previous_published_date VARCHAR(1000) ,"
					+ "publisher VARCHAR(1000),)";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTableJsonData() throws Throwable, InterruptedException {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=JsonApiData;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);

		HttpRequest request2 = HttpRequest.newBuilder()

				.uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=li9s7KAAbYij55AM3jqE8wM8AkLFVbEf")).build();

//		HttpResponse<String> response2;

		HttpClient client = HttpClient.newHttpClient();

//		
//
		HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());

		Gson gson1 = new Gson();
		Authors res = new Gson().fromJson(response.body(), Authors.class);
		System.out.println(res);

		for (int r =0; r< res.getResults().getBooks().length;r++) {
			
			

			String sql = "Insert into JsonApiTable values( '" 
			        + res.getResults().getBooks()[r].getAuthor() +"','" 
			        
					+ res.getResults().getBooks()[r].getDescription() + "','" 
					+ res.getResults().getBooks()[r].getPrice()+"','"
					+ res.getResults().getBooks()[r].getTitle()+"',"
					+ res.getResults().getBooks()[r].getRank()+",'"
					+ res.getResults().getBooks()[r].getContributor()+"','"
					+ res.getResults().getBooks()[r].getAmazon_product_url()+"','"
					+ res.getResults().getCopyright()+"','"
					+ res.getResults().getBooks()[r].getPublished_date()+"','"
					+ res.getResults().getBooks()[r].getPublished_date_description()+"','"
					+ res.getResults().getBooks()[r].getPrevious_published_date()+"','"
					+ res.getResults().getBooks()[r].getPublisher()+ "')";

//			        + book1
//					+ "')";

			System.out.println(sql);

			Connection con;

			try {

				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				// Registering drivers
				DriverManager.registerDriver(driver);

				// Reference to connection interface
				con = DriverManager.getConnection(url, user, pass);

				// Creating a statement
				Statement st = con.createStatement();

				// Executing query
				int m = st.executeUpdate(sql);
				if (m >= 1)
					System.out.println("inserted successfully : " + sql);
				else
					System.out.println("insertion failed");

				// Closing the connections
				con.close();
			}

//		 Catch block to handle exceptions
			catch (Exception ex) {
				// Display message when exceptions occurs
				System.err.println(ex);
			}
		}
	}

	public static void selectTop() throws Throwable {
		 Connection connection;
		 
//		Select top 1 * from table

		Connection con;
		Scanner sc = new Scanner(System.in);

		String serverName = "localhost";

		String url = "jdbc:sqlserver://localhost:1433;databaseName=JsonApiData;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);

		connection = DriverManager.getConnection(url, user, pass);

//		System.out.println(" Please Enter The ID Of The Row To Print");
//		Scanner inputScanner = new Scanner(System.in);
//		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP 5 * FROM  JsonApiTable ";
//+ userInput;

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);

//                int count = 0; we can not use count here because it will limit my results

			while (resultSet.next()) {

				Integer id = resultSet.getInt("Id");
				String author = resultSet.getString("author");
				String description = resultSet.getString("description");
				String price = resultSet.getString("price");
				String title = resultSet.getString("title");
				Integer rank = resultSet.getInt("rank");
				String contributor = resultSet.getString("contributor");
				String amazon_product_url = resultSet.getString("amazon_product_url");
				String copyright = resultSet.getString("copyright");
				String published_date = resultSet.getString("published_date");
				String published_date_description = resultSet.getString("published_date_description");
				String previous_published_date = resultSet.getString("previous_published_date");
				String publisher = resultSet.getString("publisher");

//				String description = resultSet.getString("description");

//				Date createdDate = resultSet.getDate("created_date");
//				Date updatedDate = resultSet.getDate("updated_date");
//				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + author + " " + description + " " + price + " " + title + " " + rank + " "
						+ contributor + " " + amazon_product_url + " " + copyright + " " + published_date + " "
						+ published_date_description + " " + previous_published_date + " " + publisher);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void deleteById() throws Throwable {
		
		 String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		 String user = "sa";
		 String pass = "root";
		 String sqlQueryToUpdate = "";
		
//		 ResultSet rs = st.executeQuery();
//		 String SQL="delete  from INMS_3 where Agent_Id=? and First_Name=? and Last_Name=?  and User_Name=? and Phone_no=?";

		
		 Connection connection = DriverManager.getConnection(url, user, pass);

	        
		 
		 
		 
		 
		 
		 
		 
		 System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		PreparedStatement st = connection.prepareStatement("DELETE FROM JsonApiTable WHERE Id = " + userInput + ";");
		
		st.setInt(userInput, userInput);
		st.executeUpdate(); 
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			
			while (resultSet.next()) {

				Integer id = resultSet.getInt("Id");
				String author = resultSet.getString("author");
				String description = resultSet.getString("description");
				String price = resultSet.getString("price");
				String title = resultSet.getString("title");
				Integer rank = resultSet.getInt("rank");
				String contributor = resultSet.getString("contributor");
				String amazon_product_url = resultSet.getString("amazon_product_url");
				String copyright = resultSet.getString("copyright");
				String published_date = resultSet.getString("published_date");
				String published_date_description = resultSet.getString("published_date_description");
				String previous_published_date = resultSet.getString("previous_published_date");
				String publisher = resultSet.getString("publisher");
//				String description = resultSet.getString("description");
//				Date createdDate = resultSet.getDate("created_date");
//				Date updatedDate = resultSet.getDate("updated_date");
//				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(
						id + " " + author + " " + description + " " + price + " " + title + " " + rank + " "
						+ contributor + " " + amazon_product_url + " " + copyright + " " + published_date + " "
						+ published_date_description + " " + previous_published_date + " " + publisher);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}}
	
	
	public static void toExit() {

		System.out.println("***********");
		System.out.println("Good Bay");
		System.out.println("***********");

	}

	public static void byDefault() {

		System.out.println("plase Enter correct choise");

	}

	public static void main(String[] args) throws Throwable {

		Scanner sc = new Scanner(System.in);

		boolean exit = true;

		do {

			mainMenue();
			int option = sc.nextInt();

			switch (option) {

			case 1:
				connectToDataBase();
				break;

			case 2:
				jsonApi();
				break;

			case 3:
				createJsonDataTable();

				break;

			case 4:
				insertIntoTableJsonData();

				break;
				
			case 5 :
                    
				selectTop();
				
				break;
			
			case 6: 
				
				 deleteById();
				
				break;
				
			case 0:
				toExit();
				exit = false;

				break;

			default:

				byDefault();
			}

		} while (exit);
	}

}

//574c22e0-b5c7-4810-8a34-c6ae15242400
//tmrH4dez+Ri5kxDc