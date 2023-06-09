package com.Masai;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
public class Main {
static ArrayList<Customer> customers = new ArrayList<>();

    public static void runProgram(Scanner sc)
    {
    	System.out.println("1 : Login as Broker/Admin");
		System.out.println("2 : Login as Customer");
		System.out.println("3 : Sign Up/Create New Account");
		
		int option = sc.nextInt();
		if(option == 1)
		{
			sc.nextLine();
			LoginAsBroker(sc);
		}
		else if(option == 2) {
			sc.nextLine();
			LoginAsCustomer(sc);
		}
		else if(option == 3)
		{
			sc.nextLine();
			CreateNewAccount(sc);
		}
    }

	private static void CreateNewAccount(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter Name");
		String name = sc.nextLine();
		System.out.println("Create UserId/Enter Email");
		String UserId = sc.nextLine();
		System.out.println("Create Password");
		String Password = sc.nextLine();
		
		for(Customer i : customers)
		{
			if(i.cUserId.equals(UserId))
			{
				System.out.println("Customer Allready Exsist, Please Try with another Userid");
				return;
			}
		}
		Customer c1 = new Customer(name,UserId,Password,0);
		customers.add(c1);
		System.out.println("Successfull SignUp");
		
		runProgram(sc);
	}
	private static void LoginAsCustomer(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter User Name/UserId/Enter Email");
		String userName = sc.nextLine();
		System.out.println("Enter Password");
		String pass = sc.nextLine();
		
		int cheack =0;
		
		for(Customer i : customers)
		{
			if(i.cUserId.equals(userName) && i.cPassword.equals(pass))
			{
				cheack ++;
				System.out.println("Login Successfully");
				LoginSuccessfully(i,sc);
			}
		}
		if(cheack == 0 )
		{
			System.out.println("Enter correct Login Creadentials");
		}
		
	}

	private static void LoginSuccessfully(Customer i, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Custumer Details");
		
		System.out.println(i.customerId+" "+i.cName);
		
		if(i.cS != null)
		{
			for(Stock j : i.cS)
			{
				System.out.println(j.stockName+" "+ j.stockPrice);
			}
		}
		else {
			System.out.println("Please Purchase Stock");
		}
		System.out.println("1 : Purchase New Stock");
		System.out.println("2 : Sell Stock");
		System.out.println("3 :LogOut");
		
		int Cusin = sc.nextInt();
		
		if(Cusin == 1)
		{
			Broker b = new Broker();
			
			for(Stock j : Broker.s )
			{
				System.out.println(j.stockId+" "+j.stockName+" "+j.stockPrice);
			}
			
			System.out.println("Enter Stock id");
			System.out.println("0: for main Manu");
			
			int si = sc.nextInt();
			if(si == 0)
			{
				LoginSuccessfully(i,sc);
			}
			else {
			int c = 0;
			for(Stock j : i.cS)
			{
				if(j.stockId == si)
				{
					c++;
				}
			}
			if(c == 0)
			{
				System.out.println("Stock Allready Purchased");
			}
			else 
			{
//				Stock newS = null;
				for(Stock j : Broker.s )
				{
					
					if(j.stockId == si)
					{
						
						i.cS.add(j);
						System.out.println("Stock Purchased");
					}
				}
			}
			LoginSuccessfully(i,sc);
			}
		}
		else if(Cusin == 2)
		{
//			for(Stock s : i.cS)
//			{
//				System.out.println(st.stockId+" "+st.stockName+" "+st.stockPrice);
//			}
			for(Stock st : i.cS)
			{
				System.out.println(st.stockId+" "+st.stockName+" "+st.stockPrice);
			}
			System.out.println("Enter Stock Id for Sell");
			System.out.println("0: for main Manu");
			
			int sellid = sc.nextInt();
			
			if(sellid == 0 )
			{
				LoginSuccessfully(i,sc);
			}
			else {
				System.out.println("Stock Selled");
				
				for(Stock j : i.cS)
				{
					if(j.stockId == sellid)
					{
						i.cS.remove(j);
						System.out.println("Stock Selled");
					}
				}
			}
			LoginSuccessfully(i,sc);
		}
		else if(Cusin == 3)
		{
			runProgram(sc);
		}
	}

	private static void LoginAsBroker(Scanner sc) {
		// TODO Auto-generated method stub
	    
		System.out.println("Enter User Name");
		String userName = sc.nextLine();
		System.out.println("Enter Password");
		String pass = sc.nextLine();
		
		Broker B1 = new Broker();
		boolean res = B1.checkCredential(userName, pass);
		if(res == true)
		{
			System.out.println("Broker/Admin Login Succsfully");
			BrokerLoginSucces(B1,sc);
		}
		else {
			System.out.println("Please Enter Correct Login Credential");
	    }
	}
	private static void BrokerLoginSucces(Broker B1,Scanner sc) 
	{
		// TODO Auto-generated method stub
		System.out.println("1 : showStockList");
		System.out.println("2 : showCustomerList");
		System.out.println("3 : Log Out");
		int bchoice = sc.nextInt();
		if(bchoice == 1)
		{
			showStockList(B1,sc);
		}
		else if(bchoice == 2)
		{
			ShowBrokerCusList(B1,sc);
		}
		else if(bchoice == 3)
		{
			runProgram(sc);
		}
		
	}

	private static void ShowBrokerCusList(Broker B1, Scanner sc) {
		// TODO Auto-generated method stub
		
		B1.showCustomerList();
		System.out.println("1 : Delete Customer");
		System.out.println("0 : Enter 0 for All Options");
		int Dcus = sc.nextInt();
		if(Dcus == 1)
		{
			System.out.println("Enter Customer Id for Delete Customer");
			int CusId = sc.nextInt();
			B1.deleteCustomer(CusId);
		}
		else {
			BrokerLoginSucces(B1,sc);
		}
		
		
	}

	private static void showStockList(Broker B1, Scanner sc) {
		// TODO Auto-generated method stub
		B1.showStockList();
		
		System.out.println("1 : Add Stock");
		System.out.println("2 : Delete Stock");
		System.out.println("3 :  All Options");
		
		int A_D_stock = sc.nextInt();
		
		if(A_D_stock == 1)
		{
			System.out.println("Enter StockId");
			int stockId = sc.nextInt();
			sc.nextLine();
			System.out.println("Please enter Stock_Name for Adding into Stock List");
			String stockName = sc.nextLine();
			System.out.println("Please enter Market Stock_Price");
			double StockPrice = sc.nextDouble();
			B1.addStock(stockId,stockName, StockPrice);
		}
		else if(A_D_stock == 2)
		{
			System.out.println("Enter StockId for delete Stock");
			int stockId = sc.nextInt();
			B1.deleteStock(stockId);
		}
		else if(A_D_stock == 3)
		{
			BrokerLoginSucces(B1,sc);
		}
		
		System.out.println("Enter 0 for All Options");
		int op = sc.nextInt();
		if(op == 0)
		{
			BrokerLoginSucces(B1,sc);
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		customers.add(new Customer("Aviraj","sawantaviraj2@gmail.com","Avi@1",0));
		customers.add(new Customer("Rahul","Rahul72@gmail.com","Rahul#1",0));
		runProgram(sc);
	}

}
