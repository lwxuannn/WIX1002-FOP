import java.util.Scanner;
public class Q1 
{
    public static void main(String[] args) 
    {
        //Create Scanner to read user input
        try(Scanner sc = new Scanner (System.in)) 
        {
            //Ask user to enter original message
            System.out.print("Enter Original Message: ");
            String ori = sc.nextLine();
            
            String encoded = "";
            
            //Loop through each character in the original message
            for (int i = 0; i < ori.length(); i ++)
            {
                //Get the character at the current position
                char character = ori.charAt(i);
                
                // 1. Lowercase letters
                if (character >= 'a' && character <= 'z')
                {
                    //Calculate shift based on original position
                    int shift = (i % 5) + 1;
                    
                    //Shift the letter forward using circular alphabet
                    char newChar = (char)('a' + (character - 'a' + shift) % 26);
                    
                    //Add the shifted letter to encoded message
                    encoded += newChar;
                }
                
                // 2. Uppercase letters
                else if (character >= 'A' && character <= 'Z')
                {
                    //Find the mirror letter in lowercase
                    char mirror = (char)('z' - (character - 'A'));
                    
                    //Add the mirror letter to encoded message and enclose with angle brackets
                    encoded += "<" + mirror + ">";
                }
                
                // 3. Digit runs
                else if (character >= '0' && character <= '9')
                {
                    //Initialise counter to count repeated digits
                    int count = 1;
                    
                    //Count consecutive occurrences of the same digit
                    while (i + 1 < ori.length() && ori.charAt(i + 1) == character)
                    {
                        count++;
                        i++; //Skip the digit that has already been counted
                    }
                    
                    //Find the new digit by replace the digit with (9 - digit)
                    char digit = (char)(('9' - character) + '0');
                    
                    //If the digit is repeated, print x followed by its count
                    if (count > 1)
                    {
                        encoded += digit + "x" + count;
                    }
                    
                    //If the digit appears only once, print the digit only
                    else
                    {
                        encoded += digit;
                    }
                    
                }
                
                // 4. Space runs
                else if (character == ' ')
                {
                    //Replace one or more consecutive spaces with underscore
                    encoded += "_";
                    
                    //Skip all remaining consecutive spaces
                    while (i + 1 < ori.length() && ori.charAt(i + 1) == character)
                        i++;
                    
                }
                
                // 5. Symbols
                else if (character == '+' || character == '-' || character == '*' || character == '/' || character == '=')
                {
                    //Replace each symbol with the previous symbol in circular order
                    switch(character)
                    {
                        case '+':
                            encoded += "=";
                            break;
                        
                        case '-':
                            encoded += "+";
                            break;
                        
                        case '*':
                            encoded += "-";
                            break;
                        
                        case '/':
                            encoded += "*";
                            break;
                        
                        case '=':
                            encoded += "/";
                            break;
                    }
                }
                
                //Other characters remain unchanged
                else
                    encoded += character;
            }
            
            //Display the encoded message
            System.out.println("Encoded Message: " + encoded);
            
            //Display the length of the original and encoded messages
            System.out.print("Original Length: " + ori.length() + " | Encoded Length: " + encoded.length());
        }
    }
    
}
