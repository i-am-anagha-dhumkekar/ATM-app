package com.nissan.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppATM {
	static Scanner input=new Scanner(System.in);
	//saved information of customer
	public static int[] panNumbers={111222333,444555666,777888999};
	public static int[] availableBalance={100,6000,20000};
	public static int[] accountNumber={100,102,104};
	public static int minAmt=100;
	//public static int amount;
	//inputs from user
	public static int amount;
	//public static int accno;
	public static void main(String[] args)
	{
		//menu driven
		char choose='n';
		do
		{
			System.out.println("What you want to do?"+"\n Please select number from below menu:");
			System.out.println("1.Deposit money in bank account"+"\n 2.Withdraw money from account"+"\n3.Show balance"+"\n4.Transfer amount to another account");
			int choice=input.nextInt();
			System.out.println("Enter your account number:");
			int accno=input.nextInt();
			boolean found=false;
			int index=0;
			for(int i=0;i<accountNumber.length;i++)
			{
				if(accountNumber[i]==accno)
				{
					index=i;
					found=true;
				}
			}
			if(found==false)
			{
				System.out.println("Sorry!!! Account number not found");
			}
			else
			{
				switch (choice)
				{
					case 1:
						depositAmount(index);
						break;
					case 2:
						withdrawMoney(index);
						break;
					case 3:
						System.out.println("Your Account Balance is: "+showBalance(index));
						break;
					case 4:
						transerToAnotherAcc(index,accno);
						break;
					default:
						System.out.println("Selected wrong option, Please select appropriate option");
						break;
				}
			}
			System.out.println("Do you want to enter more(y/n)");
			choose=input.next().charAt(0);
		}
		while(choose=='y' || choose=='Y');
			
	
	}
	private static void transerToAnotherAcc(int index,int _accno) {
		//System.out.println("Enter account number to transfer money: ");
		//int accNo=input.nextInt();
		withdrawMoney(index);
		int position=0;
		boolean found=false;
		for(int i=0;i<accountNumber.length;i++)
		{
			if(accountNumber[index]==_accno)
			{
				position=i;
				found=true;
			}
		}
		if(found==false)
		{
			System.out.println("Account not found!!!");
		}
		else
		{
			availableBalance[position]+=amount;
		}
		
	}
	private static int showBalance(int index) {
		
		return availableBalance[index];
		
	}
	private static void withdrawMoney(int index) {
		try{
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			int avaiableBalanc=availableBalance[index]-minAmt;
			System.out.println("Enter amount to be withdrawn: ");
			amount=input.nextInt();
			if(avaiableBalanc>=amount)
			{
				availableBalance[index]-=amount;
				avaiableBalanc=availableBalance[index]-amount;
				System.out.println("Successful Withdraw, Remaining balance is "+avaiableBalanc);
			}
			else //if(avaiableBalanc<amount)
			{
				System.out.println("Insufficient balance...Please try again");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid input");
		}
		
		
	}
	public static void depositAmount(int index)
	{
		BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
		try{
				
				System.out.println("Enter the amount:");
				String amt=input.next();
				String valid=setValidNumbers(amt);
				amount=Integer.parseInt(valid);
				//availableBalance[index]+=amount;
					if(amount>=50000)
					{
						System.out.println("Please provid us with your PAN Number");
						String panNum=input.next();
						String validnum=setValidNumbers(panNum);
						int panNumber=Integer.parseInt(validnum);
						if(panNumber==panNumbers[index])
						{
							availableBalance[index]+=amount;
							System.out.println("Sucessfully deposited!!!");
						}
						else
						{
							System.out.println("Wrong Pan number...Please Try again");
							panNum=brRead.readLine();
						}
						
					}
					else
					{
						availableBalance[index]+=amount;
						System.out.println("Sucessfully deposited!!!");
					}	
				
		}
		catch(Exception e)
		{
			System.out.println("Invalid");
		}
	}
	private static String setValidNumbers(String amt) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^0-9 ]");
			
			do{
				//Matches
				Matcher matcher=objPat.matcher(amt);
				boolean finder=matcher.find();
				if(finder)
				{
					System.out.println("Must contain only numbers. Please Enter again: ");
					amt=brRead.readLine();
				}
				else
				{
					return amt;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in amount typed");		
		}
		return amt;
	}
	
}
