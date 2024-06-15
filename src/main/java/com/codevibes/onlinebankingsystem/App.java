package com.codevibes.onlinebankingsystem;

import java.util.*;

import com.codevibes.onlinebankingsystem.dao.AccountantDao;
import com.codevibes.onlinebankingsystem.dao.AccountantDaoImplementation;
import com.codevibes.onlinebankingsystem.dao.CustomerDao;
import com.codevibes.onlinebankingsystem.dao.CustomerDaoImplements;
import com.codevibes.onlinebankingsystem.entity.Accountant;
import com.codevibes.onlinebankingsystem.entity.Customer;
import com.codevibes.onlinebankingsystem.exception.AccountantException;
import com.codevibes.onlinebankingsystem.exception.CustomerException;

public class App 
{
    public static void main( String[] args )
    {
        
    	Scanner sc=new Scanner(System.in);
    	boolean f=true;
    	while(f) {
    		System.out.println("-------- WELCOME TO ONLINE BANKING SYSTEM --------");
    		System.out.println("--------------------------------------------------");
    		System.out.println("1. ADMIN LOGIN PORTAL \r\n"
    				+ "2. CUSTOMER");
    		System.out.println("choose your option");
    		int choice=sc.nextInt();
    		switch(choice) {
    		case 1:
    			System.out.println("Admin Login Credentials----------------------- Accountant");
    			System.out.println("Enter Username : ");
    			String username =sc.next();
    			System.out.println("Enter password : ");
    			String pass =sc.next();
    			
    			AccountantDao ad= new AccountantDaoImplementation();
    			
    			try {
    				Accountant a= ad.LoginAccountant(username, pass);
    				if(a==null) {
    					System.out.println("Wrong cridential");
    					break;
    				}
    				
    					System.out.println("LogIn Successfully....");
    					System.out.println("Welcome to : "+a.getAccountantUsername()+ " as Admin of online Banking System");
    				
    			boolean y = true;
    			while(y) {
    			 System.out.println("-------------------------------\r\n" + 
    			"1. Add new Customer Account \r\n" + "2. Add update customer address \r\n" 
    					 + "3. Remove The Account By Account Number\r\n"
    					 + "4. View Particular Account Details By Given Account Number \r\n"
    					 + "5. View All Account/Customer List\r\n"
    					 + "6. Account LogOut \r\n"
    					 );
    					 
    			 System.out.println("Enter The Conditional Number......");
    			 int x = sc.nextInt();
    			 
    			 if(x==1) {
    				 System.out.println("-------New Account---------");
    				 System.out.println("Enter CustomerName : ");
    				 String a1 = sc.next();
    				 
    				 System.out.println("Enter Account Opening Balance");
    				 int a2 = sc.nextInt();
    				 
    				 System.out.println("Enter CustomerMail : ");
    				 String a3 = sc.next();
    				 System.out.println("Enter CustomerPassword : ");
    				 String a4 = sc.next();
    				 System.out.println("Enter CustomerMobile : ");
    				 String a5 = sc.next();
    				 System.out.println("Enter CustomerAddress : ");
    				 String a6 = sc.next();
    				 
    				 int s1 = -1;
    				 try {
    					 s1=ad.addCustomer(a1, a3, a4, a5, a6);
    					 
    					 try {
    						 ad.addAccount(a2, s1);
    					 }
    					 catch(CustomerException e){
    						 e.printStackTrace();
    					 }
    				 }
    				 catch(CustomerException e) {
    					 System.out.println(e.getMessage());
    				 }
    				 System.out.println("--------------------------");
    			 }
    			 
    			 if(x ==2) {
    				 System.out.println("Update Customer Address.......");
    				 System.out.println("Enter Customer Account Number.....");
    				 int u =sc.nextInt();
    				 System.out.println("Enter New Address....");
    				 String u2 = sc.next();
    				 try {
    					 String mes =ad.updateCustomer(u,u2);
    				 }
    				 
    				 catch (CustomerException e){
    					 e.printStackTrace();
    				 }
    			 }
    			 
    			 if(x==3) {
    				 System.out.println("Remove Account....");
    				 System.out.println("Enter Account Number......");
    				 int ac = sc.nextInt();
    				 String s= null;
    				 try {
    					 
    					 s=ad.deleteAccount(ac);
    					 
    					 
    				 }
    				 catch(CustomerException e){
    					 e.printStackTrace();
    				 }
    				 
    				 if(s!=null) {
    					 System.out.println(s);
    				 }
    			 }
    			 
    			 if(x == 4) {
    				 
    				 System.out.println("-----------Customer Details-----------");
    				 System.out.println("Enter Customer Account Number : ");
    				 String ac = sc.next();
    				 
    				 try {
    					 
    					 Customer cus = ad.viewCustomer(ac);
    					 if(cus != null) {
    						 System.out.println("*****************************");
    						 System.out.println("Account Number :"+ cus.getCustomerAccountNumber());
    						 System.out.println("Name :"+ cus.getCustomerName());
    						 System.out.println("Balance :"+ cus.getCustomerBalance());
    						 System.out.println("Email :"+ cus.getCustomerMail());
    						 System.out.println("Password :"+ cus.getCustomerPassword());
    						 System.out.println("Mobile :"+ cus.getCustomerMobile());
    						 System.out.println("Address :"+ cus.getCustomerAddress());
    					 }
    					 
    					 else {
    						 System.out.println("Account Doesnot Exist.....");
    					 }
    					 
    						 
    				 }
    				 catch(CustomerException e){
    					 
    					 e.printStackTrace();
    				 }
    			 }
    			 
    			 if(x == 5) {
    				 try {
    					 System.out.println("------------------------- All Customer List --------------------------");
    					 Customer cus = ad.viewAllCustomer();
    				 }
    				 catch(CustomerException e) {
    					 e.printStackTrace();
    				 }
    			 }
    			 
    			 if(x == 6) {
    				 System.out.println("--------Account LogOut Successfully--------");
    				 y = false;
    			 }
    			 
    			}
    			break;
    			
    			}
    			catch (AccountantException e) {
    				System.out.println(e.getMessage());
    			}
    			break;
    			
//    			Case 2 start Customer Part
    			
    		case 2:
    			System.out.println("LogIn <<---------------------------->> Customer");
    			System.out.println("-----------------------------------------------");
    			System.out.println("Enter Username : ");
    			String customerUsername = sc.next();
    			System.out.println("Enter Password : ");
    			String customerPassword = sc.next();
    			System.out.println("Enter Account Number : ");
    			int accountNumber = sc.nextInt();
    			
    			CustomerDao cd = new CustomerDaoImplements();
    			
    			try {
    				Customer cus= cd.LoginCustomer(customerUsername, customerPassword, accountNumber);
    				System.out.println("Welcome : "+cus.getCustomerName());
    				
    				boolean m =true;
    				
    				while(m) {
    					System.out.println("------------------------------------------\r\n"
    					+"1. View Balance\r\n"
    					+ "2.Deposite Money\r\n"
    					+ "3. Withdraw Money\r\n"
    					+"4. Transfer Money\r\n"
    					+"5. LOGOUT\r\n"
    							);
    					
    					int x =sc.nextInt();
    					if(x==1) {
    						System.out.println("-------------- Balance ---------------");
    						System.out.println("Current Account Balance -----");
    						System.out.println(cd.viewBalance(accountNumber));
    						System.out.println("--------------------------------------");
    					}
    					
    					if(x == 2) {
    						System.out.println("----------Deposite----------");
    						System.out.println("Enter Amount to Deposite.............");
    						int am = sc.nextInt();
    						cd.Deposite(accountNumber, am);
    						System.out.println("Your Balance After Deposite");
    						System.out.println(cd.viewBalance(accountNumber));
    						System.out.println("----------------------------");
    					}
    					
    					if(x == 3) {
    						System.out.println("---------WITHDRAW MONEY---------");
    						System.out.println("Enter Withdraw Amount :  ");
    						int am = sc.nextInt();
    						
    						try {
    							
    							cd.withdraw(accountNumber, am);
    							System.out.println("Your Balance After Withdraw");
    							System.out.println(cd.viewBalance(accountNumber));
    							System.out.println("-----------------------------");
    						}
    						catch(CustomerException e) {
    							System.out.println(e.getMessage());
    							
    						}
    					}
    					
    					if(x == 4) {
    						System.out.println("-----------------Amount  Transfer----------------");
    						System.out.println("Enter Amount To Transfer");
    						int t = sc.nextInt();
    						System.out.println("Enter Account Number To Tranfer Amount");
    						int ac = sc.nextInt();
    						
    						try {
    							cd.Transfer(accountNumber, t, ac);
    							System.out.println("Amount Transfered Successfully..........!!!!!");
    							System.out.println("------------------------------");
    						}
    						catch(Exception e) {
    							System.out.println(e.getMessage());
    						}
    					}
    					
    					// LogOut Action
    					
    					if(x==5) {
    						System.out.println("Customer LogOut Successfully");
    						System.out.println("Thank You For Using Our Banking Servise......!!!!!");
    						m = false;
    					}
    					
    				}
    				break;
    			}
    			catch(CustomerException e) {
    				System.out.println(e.getMessage());
    			}

    		}
    		
    	}
    }
}
