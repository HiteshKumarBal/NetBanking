package com.codevibes.onlinebankingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codevibes.onlinebankingsystem.databaseconnection.databaseConnection;
import com.codevibes.onlinebankingsystem.entity.Accountant;
import com.codevibes.onlinebankingsystem.entity.Customer;
import com.codevibes.onlinebankingsystem.exception.AccountantException;
import com.codevibes.onlinebankingsystem.exception.CustomerException;

public class AccountantDaoImplementation implements AccountantDao {

	@Override
	public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException {
		
		Accountant acc=null;
		try(Connection conn= databaseConnection.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from accountant where accountantUserName= ? and accountantPassword = ? ");
			
			ps.setString(1, accountantUsername);
			ps.setString(2, accountantPassword);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				String n=rs.getString("accountantUsername");
				String e=rs.getString("accountantEmail");
				String p=rs.getString("accountantPassword");
				
				acc=new Accountant(n, e, p);
				
			}
		}
		catch (SQLException e) {
			throw new AccountantException("Invalid Username and Password");
		}
		return acc;
	}

	@Override
	public int addCustomer(String customerName, String customerMail, String customerPassword, String customerMobile,
			String customerAddress) throws CustomerException {
		int cid= 1;
		try(Connection conn=databaseConnection.provideConnection()){
			PreparedStatement ps =conn.prepareStatement("insert into customerinformation (customerName, customerMail, customerPassword, customerMobile, customerAddress) values(?,?,?,?,?)");
		
			ps.setString(1, customerName);
			ps.setString(2, customerMail);
			ps.setString(3, customerPassword);
			ps.setString(4, customerMobile);
			ps.setString(5, customerAddress);
			
			int x = ps.executeUpdate();
			if(x>0) {
				PreparedStatement ps1 = conn.prepareStatement("select cid from customerinformation where customerMail = ? and customerMobile = ? ");
				ps1.setString(1, customerMail);
				ps1.setString(2, customerMobile);
				ResultSet rs = ps1.executeQuery();
				
				if(rs.next()) {
					cid =rs.getInt(cid);
				}
				else {
					System.out.println("Inserted data is incorrect Please try again.....");
				}
				
			}
			
		}
		catch(SQLException e){
			System.out.println("SQL Querry Related error......");
		}
		return cid;
	}
	

	@Override
	public String addAccount(int customerBalance, int cid) throws CustomerException {


		String message = null;
		try(Connection conn= databaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into account(customerBalance, cid) values(?,?) ");
		
			ps.setInt(1, customerBalance);
			ps.setInt(2, cid);
		
			int x = ps.executeUpdate();
			if(x>0) {
				System.out.println("Account Balance Added Successfully....");
			}
			else {
				System.out.println("inserted data not added successfully...");
			}
		}
		catch (SQLException e) {
			
			System.out.println("SQL related exception.....");
		}
		return message;
	}

	@Override
	public String updateCustomer(int customerAccountNumber, String customerAddress) throws CustomerException {
		
		String message= null;
		
		try(Connection conn=databaseConnection.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("update customerinformation i inner join account a on i.cid =a.cid and a.customerAccountNumber = ? set i.customerAddress = ?");
			
			ps.setInt(1, customerAccountNumber);
			ps.setString(2, customerAddress);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Address updated successfully.....");
			}
			else {
				System.out.println("customer updation is not successful...");
				System.out.println("--------------------------------");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public String deleteAccount(int customerAccountNumber) throws CustomerException {
		
		
		String message = null;
		
		try(Connection conn = databaseConnection.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("delete i from customerinformation i inner join account a on i.cid =a.cid where a.customerAccountNumber = ? ");
			ps.setInt(1, customerAccountNumber);
			
			int x= ps.executeUpdate();
			
			if(x>0) {
				System.err.println("Account Deleted Successfully....");
			}
			else {
				System.out.println("Deletation failed......Account not found");
				System.out.println("----------------------------------------");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	@Override
	public Customer viewCustomer(String customerAccountNumber) throws CustomerException {
		
		Customer cu = null;
		
		try (Connection conn=databaseConnection.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerAccountNumber = ?");
			
			ps.setString(1, customerAccountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int a = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int b = rs.getInt("customerBalance");
				String e = rs.getString("customerPassword");
				String p = rs.getString("customerMail");
				String m = rs.getString("customerMobile");
				String ad = rs.getString("customerAddress");
				
				cu = new Customer(a,n,b,e,p,m,ad);
				
			}
			
			else {
				
				throw new CustomerException("Invalid Account Number.....");
			}
		}
		catch(SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		return cu;
	}

	@Override
	public Customer viewAllCustomer() throws CustomerException {
		
		Customer cu= null;
		
		try(Connection conn = databaseConnection.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int a = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int b = rs.getInt("customerBalance");
				String e = rs.getString("customerPassword");
				String p = rs.getString("customerMail");
				String m = rs.getString("customerMobile");
				String ad = rs.getString("customerAddress");
				
				System.out.println("***********************************");
				System.out.println("Account Number : "+ a);
				System.out.println("Customer Name : "+n);
				System.out.println("Customer Balance : "+b);
				System.out.println("Customer Password : "+e);
				System.out.println("Customer Mail : "+p);
				System.out.println("Customer Mobile : "+m);	
				System.out.println("Customer Address : "+ad);
				System.out.println("***********************************");
				
				cu = new Customer(a,n,b,e,p,m,ad);
				
				}
		}
		catch(SQLException e) {
			throw new CustomerException("Invalid Account Number !!!");
		}
		return cu;
	}

	
}
