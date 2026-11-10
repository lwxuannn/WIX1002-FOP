import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Keep track of the total charges for all calls.
        double totalCharges = 0.0;

        // Step 2: Continue processing calls until the user enters EXIT.
        while (true) {
            System.out.print("Enter phone number (or EXIT to stop): ");
            String phone = sc.nextLine().trim();

            // Stop the program if the user enters EXIT.
            // equalsIgnoreCase() accepts EXIT, exit, Exit, etc.
            if (phone.equalsIgnoreCase("EXIT")) {
                break;
            }

            // Step 3: Remove spaces and hyphens from the phone number.
            phone = phone.replace(" ", "");
            phone = phone.replace("-", "");

            // Step 4: Check whether the number is an emergency number.
            // Emergency numbers are free of charge.
            boolean isEmergency =
                phone.equals("999") ||
                phone.equals("991") ||
                phone.equals("112") ||
                phone.equals("994");

            // Step 5: Determine whether the number is international.
            // A number starting with + but not +60 is international.
            boolean isInternational =
                phone.startsWith("+") && !phone.startsWith("+60");

            // Step 6: Normalize Malaysian phone numbers.
            // Convert +60 to 0, or add a missing leading zero.
            if (phone.startsWith("+60")) {
                phone = "0" + phone.substring(3);
            } else if (!phone.startsWith("+") && !phone.startsWith("0") && !isEmergency) {
                phone = "0" + phone;
            }

            // Step 7: Get a valid call duration.
            int duration;
            while (true) {
                System.out.print("Enter call duration in minutes: ");

                // Check that the input is an integer.
                if (!sc.hasNextInt()) {
                    System.out.println(
                        "Error: Duration must be an integer."
                    );
                    sc.nextLine(); // Discard invalid input.
                    continue;
                }

                duration = sc.nextInt();
                sc.nextLine(); // Consume the remaining newline.

                // Duration must be greater than zero.
                if (duration > 0) {
                    break;
                }

                System.out.println(
                    "Error: Call duration must be greater than 0."
                );
            }

            // Step 8: Calculate the base rate.
            double rate = 0.0;
            String network = "Unknown";

            if (isEmergency) {
                // Emergency calls are free.
                rate = 0.0;
                network = "Emergency";
            } else if (isInternational) {
                // International calls have a flat rate.
                rate = 1.50;
                network = "International";
            } else {
                // Identify the Malaysian network using its 3-digit prefix.
                String prefix = "";

                if (phone.length() >= 3) {
                    prefix = phone.substring(0, 3);
                }

                // CelNom prefixes: 012, 014, 016
                if (prefix.equals("012") ||
                    prefix.equals("014") ||
                    prefix.equals("016")) {
                    rate = 0.15;
                    network = "CelNom";
                }
                // DigiLink prefixes: 011, 013, 015
                else if (prefix.equals("011") ||
                         prefix.equals("013") ||
                         prefix.equals("015")) {
                    rate = 0.12;
                    network = "DigiLink";
                }
                // MaxiPulse prefixes: 017, 018, 019
                else if (prefix.equals("017") ||
                         prefix.equals("018") ||
                         prefix.equals("019")) {
                    rate = 0.18;
                    network = "MaxiPulse";
                }
            }

            // Step 9: Calculate the original call cost.
            double cost = rate * duration;

            // Step 10: Apply the duration discount or surcharge.
            if (!isEmergency) {
                if (duration >= 6 && duration <= 20) {
                    // A 10% discount means paying 90% of the original cost.
                    cost = cost * 0.90;
                } else if (duration > 20) {
                    // A 15% surcharge means paying 115% of the original cost.
                    cost = cost * 1.15;
                }
            }

            // Step 11: Add this call's cost to the accumulated total.
            totalCharges += cost;

            // Step 12: Display the result for this call.
            System.out.println("\nCall Summary");
            System.out.println("Phone Number: " + phone);
            System.out.println("Network: " + network);
            System.out.println("Duration: " + duration + " minute(s)");
            System.out.printf("Rate: RM %.2f/min%n", rate);
            System.out.printf("Call Charge: RM %.2f%n", cost);
        }

        // Step 13: Display the total cost after the user exits.
        System.out.printf("Total Charges: RM %.2f%n", totalCharges);
        sc.close();
    }
}
