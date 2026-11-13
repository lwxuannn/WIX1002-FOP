import java.util.Scanner;
public class Q3 
{
    public static void main(String[] args) 
    {
        //Create Scanner to read user input
        try(Scanner sc = new Scanner (System.in))
        {
            //Initialise variables for bill statistics
            int processed = 0;
            double totalAmount = 0;
            double highestBill = 0;
            String highestAcc = "";
            
            //Repeat the billing process until user enters EXIT
            do
            {
                //Ask user to enter account number
                System.out.print("Enter account number (or EXIT to stop): ");
                String acc = sc.nextLine();

                //Check whether user wants to stop the program
                if (acc.equalsIgnoreCase("EXIT"))
                {
                    //Display final bill statistics
                    System.out.println("Bills Processed: " + processed);
                    System.out.printf("Total Amount Billed: RM %.2f%n", totalAmount);
                    System.out.printf("Highest Bill: RM %.2f (%s)%n", highestBill, highestAcc); 
                    break;
                }
                
                //Remove spaces and hyphens, then convert to uppercase
                acc = acc.replaceAll("[- ]", "").toUpperCase();
                
                //Assume account number is valid initially
                boolean valid = true;
                
                //Check account number length and first character
                if (acc.length() != 9 || (acc.charAt(0) != 'R' && acc.charAt(0) != 'C' && acc.charAt(0) != 'I'))
                {
                    valid = false;
                }
                
                //Check whether the remaining 8 characters are digits
                else
                {
                    for (int i = 1; i < acc.length(); i++)
                    {
                        if (acc.charAt(i) < '0' || acc.charAt(i) > '9')
                        {
                            valid = false;
                            break;
                        }
                    }
                }
                
                //Skip billing if account number is invalid
                if(valid == false)
                {
                    System.out.println("Error: Invalid account number. Bill skipped.\n");
                }
                else
                {
                    int usage;
                    
                    //Keep asking user until valid electricity usage is entered
                    while (true)
                    {
                        System.out.print("Enter electricity usage (kWh): ");
                    
                        //Check whether input is an integer
                        if(!sc.hasNextInt())
                        {
                            System.out.println("Error: Usage must be an integer.");
                            sc.nextLine();
                            continue;
                        }
                    
                        //Read electricity usage
                        usage = sc.nextInt();
                        sc.nextLine();
                    
                        //Check whether usage is greater than zero
                        if (usage <= 0)
                        {
                            System.out.println("Error: Usage must be greater than 0.");
                            continue;
                        }
                        
                        //Exit loop when usage is valid
                        break;
                    }

                    //Determine customer category based on account number
                    String category;
                    
                    switch (acc.charAt(0)) 
                    {
                        case 'R':
                            category = "Residential";
                            break;
                            
                        case 'C':
                            category = "Commercial";
                            break;
                            
                        default:
                            category = "Industrial";
                            break;
                    }
                        
                    //Initialise energy charge and service tax
                    double charge = 0;
                    double tax = 0;
                    
                    //Calculate electricity charge based on customer category
                    switch (acc.charAt(0))
                    {
                        case 'R':
                        {
                            //Calculate Residential charge using block tariff
                            if (usage < 201)
                            {
                                charge = usage * 0.218;
                            }
                            
                            else if (usage < 301)
                            {
                                charge = (200 * 0.218)+(usage - 200)*0.334;
                            }
                            
                            else if (usage < 601)
                            {
                                charge = (200 * 0.218) + (100 * 0.334) + (usage - 300)*0.516;
                            }
                            
                            else
                            {
                                charge = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + (usage-600) * 0.546;
                            }
                                
                            //Apply minimum energy charge of RM 3.00
                            if (charge < 3.00)
                            {
                                charge = 3.00;
                            }
                                
                            //Apply 8% service tax only when usage exceeds 600 kWh
                            if (usage > 600)
                            {
                                tax = charge * 0.08;
                            }
                                
                            break;
                        }
                            
                        case 'C':
                        {
                            //Calculate Commercial charge at RM 0.435 per kWh
                            charge = usage * 0.435;
                                
                            //Apply 8% service tax
                            tax = charge * 0.08;
                                
                            break;
                        }
                            
                        case 'I':
                        {
                            //Calculate Industrial charge at RM 0.380 per kWh
                            charge = usage * 0.380;
                                
                            //Apply minimum energy charge of RM 600.00
                            if (charge < 600.00)
                            {
                                charge = 600.00;
                            }
                                
                            //Apply 8% service tax
                            tax = charge * 0.08;
                               
                            break;
                        }
                                
                    }
                                
                    //Calculate total bill
                    double total = charge + tax;
                    
                    //Display bill summary
                    System.out.println("\nBill Summary");
                    System.out.println("Account Number: " + acc);
                    System.out.println("Category: " + category);
                    System.out.println("Usage: " + usage + " kWh");    
                    System.out.printf("Energy Charge: RM %.2f%n", charge);
                    System.out.printf("Service Tax (8%%): RM %.2f%n", tax);
                    System.out.printf("Total Bill: RM %.2f%n%n", total);
                        
                    //Update number of processed bills
                    processed ++;
                    //Add current bill to total amount billed
                    totalAmount += total;
                        
                    //Check and update highest bill
                    if (total > highestBill)
                    {
                        highestBill = total;
                        highestAcc = acc;
                    }       
                }
            }
            while (true);
        }
    } 
}
