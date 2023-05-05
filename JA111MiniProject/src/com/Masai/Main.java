package com.Masai;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Main {
static ArrayList<Customer> customers = new ArrayList<>();

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("1 : Login as Broker/Admin");
		System.out.println("2 : Login as Customer");
		System.out.println("3 : Sign Up/Create New Account");
		int option = sc.nextInt();
		sc.nextLine();
		if(option == 1)
		{
			System.out.println("Enter User Name");
			String userName = sc.nextLine();
			System.out.println("Enter Password");
			String pass = sc.nextLine();
			
			Broker B1 = new Broker();
			boolean res = B1.checkCredential(userName, pass);
			if(res == true)
			{
				System.out.println("Broker/Admin Login Succsfully");
				
				System.out.println("1 : showStockList");
				System.out.println("2 : showCustomerList");
				System.out.println("5 : Delete Customer");
				
				int bchoice = sc.nextInt();
				//sc.nextLine();
				if(bchoice == 1)
				{
					B1.showStockList();
					
					System.out.println("1 : Add Stock");
					System.out.println("2 : Delete Stock");
					
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
					
				}
				else if(bchoice == 2)
				{
					B1.showCustomerList();
					
					System.out.println("1 : Delete Customer");
					System.out.println("0 : Exit");
					int Dcus = sc.nextInt();
					
					if(Dcus == 1)
					{
						System.out.println("Enter Customer Id for Delete Customer");
						int CusId = sc.nextInt();
						B1.deleteCustomer(CusId);
					}
					else {
						System.out.println("Exit");
					    return;
					}
					
				}
				
			}
			else {
				System.out.println("Please Enter Correct Login Credential");
			}
		}
		else if(option == 2)
		{
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
					System.out.println("Custumer Details");
					System.out.println(i.customerId+" "+i.cName);
					for(Stock j : i.cS)
					{
						System.out.println(j.stockName+" "+ j.stockPrice);
					}
					System.out.println("1 : Purchase New Stock");
					System.out.println("2 : Sell Stock");
					System.out.println("3 : Exit");
					
					int Cusin = sc.nextInt();
					
					if(Cusin == 1)
					{
						for(Stock j : Broker.s )
						{
							System.out.println(j.stockId+" "+j.stockName+" "+j.stockPrice);
						}
						System.out.println("Enter Stock id");
						int si = sc.nextInt();
						int c =0;
						for(Stock j : i.cS)
						{
							if(j.stockId == si)
							{
								c++;
							}
						}
						if(c ==0)
						{
							System.out.println("Stock Allready Purchased");
						}
						else {
							Stock newS = null;
							for(Stock j : Broker.s )
							{
								if(j.stockId == si)
								{
									newS = j;
								}
							}
							i.cS.add(newS);
						}
						
						
					}
					else if(Cusin == 2)
					{
						System.out.println("Enter Stock Id for Sell");
						int sellid = sc.nextInt();
						
						
						for(Stock j : i.cS)
						{
							if(j.stockId == sellid)
							{
								i.cS.remove(j);
								System.out.println("Stock Selled");
							}
						}
					}
				}
			}
			if(cheack == 0)
			{
				System.out.println("Please Enter Valid Username & Password");
			}
			return;
		}
		else if(option == 3)
		{
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
			
		}
	}
}