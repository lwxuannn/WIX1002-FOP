//VIVA WIX1002 Q2 Tiered Savings Plan with Goal Tracker 

import java.util.Scanner;

public class Q2
{
    public static void main(String[] args) 
    {
        try(Scanner sc=new Scanner(System.in))
        {
            //D = monthly deposit
           double D;

           
           //1. User Inputs:
           do
            {
                System.out.print("Enter monthly deposit (RM): ");
                D = sc.nextDouble();
                if (D < 100 || D > 5000)
                {
                    System.out.println("Error: Deposit must be between RM 100 and RM 5000.");
                }
            } while (D < 100 || D > 5000); 
           
           System.out.print("Enter starting annual interest rate (%): ");
           double R=sc.nextDouble();  // R =annual rate
           System.out.print("Enter savings goal (RM): ");
           double G=sc.nextDouble(); //G = savings goal
            System.out.print("Is there a withdrawal? (Y/N): ");
            char option=sc.next().charAt(0); 
            
            
            int M=0;  // withdrawal month
           double W=0; // withdrawal amount
           
            if (option=='Y' || option=='y' )
           {
                      System.out.print("Enter withdrawal month (1-36): ");
                      M=sc.nextInt();
                      System.out.print("Enter withdrawal amount (RM): ");
                      W=sc.nextDouble(); 
           }
            
            //Variables for  3. Simulation Logic (36 months)
           double r;  //r = monthly interest rate
           double interest=0; //interest earned in the current month
           double balance=0; // current savings balance 
           double penalty=0;  // early withdrawal penalty (2% of W)
           double total_interest=0;
           double total_deposits=0;
           boolean goalReached = false; 
           double annualRate=0;
           int year;// current year (1, 2 or 3)
           
 
           // 3. Simulation Logic (36 months)
           for(int i=1;i<=36;i++)
           {
               //(Start of the month) update monthly balance + deposit
               balance=balance+D; 
               total_deposits=total_deposits+D;
               
               
               //Withdrawal month
                if ((option=='Y' || option=='y' )&& (i==M))
           {
   
                if(M>=1 && M<=12) //Penalty applies only in Year 1
                      {
                                 penalty=0.02*W; 
                       }
               
               else
                      {
                                 penalty=0; //money withdrew during Year2,3 without penalty
                      }
                
               
               if((W+penalty)>balance) //Check if withdrawal amount+penalty is more than balance
                      {
                                  System.out.println("Warning: Withdrawal exceeds balance! Balance reset to RM 0.00.");
                                  balance=0.00;
                       }
               else
               {
                   if(penalty>0) //2% of withdrawal amount charged
                   {
                       balance=balance-(penalty+W);
                       System.out.printf("Early withdrawal penalty charged: RM %.2f%n",penalty); //penalty amount
                   }
                   else
                   {
                       balance=balance-W; //no penalty,so just deduct with withdrawal amount
                   }
               }
           }
               
               //End of month
               //2. Rate for each year
               year=(i-1)/12+1; //check for year
               if(year==1)
               {
                   r=R/(100.0*12); //check for the monthly interest rate, r
                   annualRate=R;
               }
               else if(year==2)
               { 
                 r=(R+0.5)/(100.0*12);  //check for the monthly interest rate, r
                 annualRate=R+0.5;
               }
               else
               {
                   r=(R+1.0)/(100.0*12); //check for the monthly interest rate, r  
                   annualRate=R+1.0;
               }
               
               interest=balance*r;          // this month's interest
               balance=balance+interest;        // add interest into the balance
               total_interest=total_interest+interest;        // keep the running total
               
               
               //Goal check
               if(!goalReached &&balance>=G)
               {
                   System.out.println("Savings goal reached in Month "+i+"!");
                   goalReached = true;  //so it will not print again
               }
            
            //4. Output
               if(i==12 || i==24 || i==36)
               {
                   System.out.printf("End of Year "+year+" Balance: RM %.2f (rate %.1f%%)%n",balance,annualRate);
               }
               
               
           }
           
           
           System.out.printf("Total Deposits: RM %.2f%n",total_deposits);
           System.out.printf("Total Interest Earned: RM %.2f%n",total_interest);
           
           if (!goalReached)
           {
                    System.out.println("Savings goal not reached within 36 months.");
           }
           
   
           }  
        }
        
    }
    

