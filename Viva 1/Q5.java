//VIVA WIX1002 Q5 The Songket Diamond Weaver

import java.util.Scanner;

public class Q5
{
    public static void main(String[] args)
    {
        try(Scanner sc=new Scanner(System.in))
        {
            int K,N;  // N = grid size, K = band thickness
            
            //1. Input Validation
           do
           {
                System.out.print("Enter size N: ");
                N=sc.nextInt();
                System.out.print("Enter band thickness K: ");
                K=sc.nextInt();
                
                if(N<5 || N>25 || (N%2==0))  // Check N: must be odd and between 5 and 25
                {
                    System.out.println("Error: N must be an odd number between 5 and 25.");
                }
                
                if(K<1 || K>3 || K>N/2 )  // Check K: between 1 and 3, and not more than N/2
                {
                    System.out.println("Error: K must be between 1 and 3 and not more than N / 2.");
                }
            }
           //Repeat while any condition is invalid, so both N and K are asked again   
           while( N<5 || N>25 || N%2==0||K<1  || K>3 ||K>N/2); 
           
           
           //2. Pattern Construction
           
           int mid=N/2;  //find centre
           
           for(int r=0;r<N;r++) // counting rows from 0
           {
               for(int c=0;c<N;c++) // counting columns from 0
               {
                   int d = Math.abs(r - mid) + Math.abs(c - mid); //Manhattan distance
                   
                   if(d==0) // Centre of the diamond
                   {
                       System.out.print("@");
                   }
                   
                   // Inside the diamond (d<=mid)
                   else if(d<=mid && ((d/K)%3==0))
                   {
                       System.out.print("#");
                   }
                   
                   else if(d<=mid && ((d/K)%3==1))
                    {
                       System.out.print("+");
                    }
                   
                    else if(d<=mid && ((d/K)%3==2))
                    {
                       System.out.print("o");
                     }
                    
                   // Outside the diamond (d>mid)
                   // Top-left corner
                   else if(d>mid  && r<mid && c<mid)
                    {
                       System.out.print("/");
                     }
                   
                    // Top-right corner
                   else if(d>mid  && r<mid && c>mid)
                    {
                       System.out.print("\\");
                     }
                   
                   // Bottom-left corner
                   else if(d>mid  && r>mid && c<mid)
                    {
                       System.out.print("\\");
                     }
                   
                    // Bottom-right corner
                   else if(d>mid  && r>mid && c>mid)
                    {
                       System.out.print("/");
                     }
                   
               }
               System.out.println( );
           }
     
         }
    
       }
 } 

    
    


