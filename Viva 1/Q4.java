// Import the Scanner class so we can read input from the keyboard
import java.util.Scanner;
 
public class Q4 {
 
    public static void main(String[] args) {
 
        // Create a Scanner object to read what the user types
        Scanner sc = new Scanner(System.in);
 
        // ===================== DATA OF THE 4 RELIEF CENTRES =====================
 
        // ----- Centre 1 -----
        String name1 = "Kampung Baru Hall";  // name of centre 1
        int max1 = 60;                       // maximum families at centre 1
        double foodPer1 = 3.0;               // food (kg) per family at centre 1
        int dist1 = 12;                      // one-way distance (km) to centre 1
        boolean served1 = false;             // false = not served yet
 
        // ----- Centre 2 -----
        String name2 = "Sri Muda School";
        int max2 = 80;
        double foodPer2 = 2.5;
        int dist2 = 25;
        boolean served2 = false;
 
        // ----- Centre 3 -----
        String name3 = "Masjid Al-Hidayah";
        int max3 = 40;
        double foodPer3 = 4.0;
        int dist3 = 8;
        boolean served3 = false;
 
        // ----- Centre 4 -----
        String name4 = "Taman Melati Hall";
        int max4 = 50;
        double foodPer4 = 3.5;
        int dist4 = 30;
        boolean served4 = false;
 
        // ===================== RESOURCES OF THE VAN =====================
 
        double foodLeft = 400.0;   // food left in the van (kg)
        double fuelLeft = 30.0;    // fuel left in the van (L)
 
        // ===================== TOTALS FOR THE FINAL REPORT =====================
 
        int totalFamilies = 0;     // total families served so far
        double totalFood = 0;      // total food delivered so far (kg)
        double totalFuel = 0;      // total fuel used so far (L)
 
        // ===================== MAIN LOOP =====================
 
        // Repeat while the van still has food
        while (foodLeft > 0) {
 
            // ---------- STEP 1: Check if any centre can still be reached ----------
 
            boolean canReach = false;   // assume nobody can be reached first
 
            // Fuel needed for a round trip = distance x 2 x 0.25
            // Centre 1: must be not served AND have enough fuel
            if (!served1 && dist1 * 2 * 0.25 <= fuelLeft) {
                canReach = true;
            }
            // Centre 2
            if (!served2 && dist2 * 2 * 0.25 <= fuelLeft) {
                canReach = true;
            }
            // Centre 3
            if (!served3 && dist3 * 2 * 0.25 <= fuelLeft) {
                canReach = true;
            }
            // Centre 4
            if (!served4 && dist4 * 2 * 0.25 <= fuelLeft) {
                canReach = true;
            }
 
            // If no centre can be reached, show message and stop the loop
            if (!canReach) {
                System.out.println("No remaining centre can be reached with the fuel left.");
                break;
            }
 
            // ---------- STEP 2: Show the menu ----------
 
            System.out.println("\n===== Flood Relief Van Dispatcher =====");
            System.out.printf("Food Left: %.2f kg | Fuel Left: %.2f L%n", foodLeft, fuelLeft);
 
            // Print centre 1 (no new line yet, because we may add [SERVED])
            System.out.printf("1. %s (%d families, %.1f kg/family, %d km)", name1, max1, foodPer1, dist1);
            if (served1) {
                System.out.print(" [SERVED]");   // add the tag if already served
            }
            System.out.println();               // go to the next line
 
            // Print centre 2
            System.out.printf("2. %s (%d families, %.1f kg/family, %d km)", name2, max2, foodPer2, dist2);
            if (served2) {
                System.out.print(" [SERVED]");
            }
            System.out.println();
 
            // Print centre 3
            System.out.printf("3. %s (%d families, %.1f kg/family, %d km)", name3, max3, foodPer3, dist3);
            if (served3) {
                System.out.print(" [SERVED]");
            }
            System.out.println();
 
            // Print centre 4
            System.out.printf("4. %s (%d families, %.1f kg/family, %d km)", name4, max4, foodPer4, dist4);
            if (served4) {
                System.out.print(" [SERVED]");
            }
            System.out.println();
 
            // Print the exit option
            System.out.println("0. End dispatch");
 
            // ---------- STEP 3: Ask for the centre and validate it ----------
 
            System.out.print("Select a centre (0-4): ");
 
            // hasNextInt() is false if the user did not type an integer
            if (!sc.hasNextInt()) {
                sc.next();   // throw away the wrong input so it does not repeat forever
                System.out.println("Error: Please enter an integer from 0 to 4.");
                continue;    // go back to the top of the loop (show the menu again)
            }
 
            int choice = sc.nextInt();   // read the integer
 
            // Choice 0 means the user wants to stop
            if (choice == 0) {
                System.out.println("Dispatch ended by user.");
                break;   // leave the loop
            }
 
            // Any number outside 0 - 4 is invalid
            if (choice < 0 || choice > 4) {
                System.out.println("Error: Invalid centre selection.");
                continue;   // back to the top of the loop
            }
 
            // ---------- Copy the chosen centre's data into working variables ----------
            // This lets the rest of the code work for any centre without repeating it
 
            String name = "";        // name of the chosen centre
            int max = 0;             // max families of the chosen centre
            double foodPer = 0;      // food per family of the chosen centre
            int dist = 0;            // distance of the chosen centre
            boolean served = false;  // is the chosen centre already served?
 
            if (choice == 1) {
                name = name1;
                max = max1;
                foodPer = foodPer1;
                dist = dist1;
                served = served1;
            } else if (choice == 2) {
                name = name2;
                max = max2;
                foodPer = foodPer2;
                dist = dist2;
                served = served2;
            } else if (choice == 3) {
                name = name3;
                max = max3;
                foodPer = foodPer3;
                dist = dist3;
                served = served3;
            } else {   // choice must be 4 here
                name = name4;
                max = max4;
                foodPer = foodPer4;
                dist = dist4;
                served = served4;
            }
 
            // Check if this centre was already served
            if (served) {
                System.out.println("Error: This centre has already been served.");
                continue;
            }
 
            // Fuel needed for the round trip to this centre
            double fuelNeeded = dist * 2 * 0.25;
 
            // Check if the van has enough fuel
            if (fuelNeeded > fuelLeft) {
                System.out.printf("Error: Not enough fuel to reach %s (needs %.2f L).%n", name, fuelNeeded);
                continue;
            }
 
            // ---------- STEP 4: Ask for number of families and validate ----------
 
            int families = 0;         // number of families to serve
            boolean valid = false;    // becomes true when the input is correct
 
            // Keep asking until the input is valid
            while (!valid) {
                System.out.print("Enter number of families to serve (1-" + max + "): ");
 
                if (!sc.hasNextInt()) {
                    // The user did not type an integer
                    sc.next();   // throw away the wrong input
                    System.out.println("Error: Enter a whole number.");
                } else {
                    families = sc.nextInt();   // read the integer
                    if (families < 1 || families > max) {
                        // The number is outside 1 to max
                        System.out.println("Error: Number of families is out of range.");
                    } else {
                        valid = true;   // correct input, so the while loop will stop
                    }
                }
            }
 
            // ---------- STEP 5: Calculate the food ----------
 
            // Food needed for all the families requested
            double foodRequired = families * foodPer;
 
            double foodDelivered;      // food really delivered
            int familiesServed;        // families really served
            boolean foodRanOut;        // true if the van runs out of food
 
            if (foodRequired <= foodLeft) {
                // There is enough food, so deliver everything
                foodDelivered = foodRequired;
                familiesServed = families;
                foodRanOut = false;
            } else {
                // Not enough food: only whole family packs can be given
                familiesServed = (int) (foodLeft / foodPer);   // (int) removes the decimal part
                foodDelivered = familiesServed * foodPer;      // food for those whole packs
                foodRanOut = true;
            }
 
            // ---------- STEP 6: Update the records ----------
 
            // Mark the chosen centre as served
            if (choice == 1) {
                served1 = true;
            } else if (choice == 2) {
                served2 = true;
            } else if (choice == 3) {
                served3 = true;
            } else {
                served4 = true;
            }
 
            foodLeft = foodLeft - foodDelivered;           // food left in the van
            fuelLeft = fuelLeft - fuelNeeded;              // fuel left in the van
            totalFamilies = totalFamilies + familiesServed; // add to total families
            totalFood = totalFood + foodDelivered;         // add to total food delivered
            totalFuel = totalFuel + fuelNeeded;            // add to total fuel used
 
            // ---------- STEP 7: Print the delivery summary ----------
 
            System.out.println("\n--- Delivery Summary ---");
            System.out.println("Centre: " + name);
            System.out.printf("Food Required: %.2f kg%n", foodRequired);
            System.out.printf("Food Delivered: %.2f kg%n", foodDelivered);
            System.out.printf("Fuel Used: %.2f L (round trip %d km)%n", fuelNeeded, dist * 2);
 
            if (foodRanOut) {
                // Food ran out: show the special message and end the session
                System.out.println("Food Ran Out! Only " + familiesServed + " of " + families + " families served.");
                System.out.println("------------------------");
                break;   // leave the main loop
            } else {
                // Normal delivery
                System.out.println("Result: " + familiesServed + " families served.");
                System.out.println("------------------------");
            }
        }
 
        // ===================== FINAL REPORT =====================
 
        System.out.println("\n===== Dispatch Report =====");
        System.out.println("Families Served: " + totalFamilies);
        System.out.printf("Food Delivered: %.2f kg%n", totalFood);
        System.out.printf("Fuel Used: %.2f L%n", totalFuel);
        System.out.printf("Food Left: %.2f kg | Fuel Left: %.2f L%n", foodLeft, fuelLeft);
    }
}
