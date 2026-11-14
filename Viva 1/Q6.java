import java.util.Scanner;

public class Q6 {
    public static void main (String []args) {

        // try-with-resources: the Scanner is closed automatically when the block ends
        try (Scanner cs = new Scanner (System.in)) {

            // ===================== READ THE INPUT =====================

            System.out.print("Enter flight commands: ");
            String command = cs.nextLine();   // read the whole line, e.g. R3d2U

            // ===================== 1. VALIDATION (check the whole string first) =====================

            boolean valid = true;   // assume the string is valid until a rule is broken

            // Rule: the string must not be empty and the first character must not be a digit
            if (command.length() == 0 || Character.isDigit(command.charAt(0))) {
                valid = false;
            }

            // Check every character (stop early as soon as the string is invalid)
            for (int i = 0; i < command.length() && valid; i++) {
                char c = command.charAt(i);

                if (Character.isDigit(c)) {
                    // Rule: a digit must not come right after another digit
                    if (i + 1 < command.length() && Character.isDigit(command.charAt(i+1))) valid = false;

                    // Rule: the digit must be from 2 to 9 (so 0 and 1 are invalid)
                    if (c < '2' || c > '9') valid = false;
                }
                // Rule: any other character must be one of U D L R u d l r
                else if ("UDLRudlr".indexOf(c) == -1) valid = false;   // -1 means "not found"
            }

            // If invalid, print the message and stop the program before any drone moves
            if (!valid) {
                System.out.println("Invalid Command String");
                return;
            }

            // ===================== VARIABLES =====================

            // Lowercase x/y = trial position (where the drone TRIES to go)
            // Uppercase X/Y = confirmed position (where the drone REALLY is)
            // Both drones start at (0,0)
            int xA = 0, yA = 0, xB = 0, yB = 0, XA = 0, YA = 0, XB = 0, YB = 0;

            int collide = 0;    // number of collisions
            int miss = 0;       // number of near misses
            int block = 0;      // number of blocked moves
            int separate = 0;   // maximum separation seen so far
            int total = 0;      // total number of steps

            // ===================== 2. SIMULATION =====================

            int i = 0;   // position in the command string

            while (i < command.length()) {
                char c = command.charAt(i);   // the command letter
                int skip = 1;   // how many characters this command uses (letter only = 1)
                int step = 1;   // how many times to repeat the command (default = 1)

                // If a digit follows the letter, it is the repeat count
                if (i + 1 < command.length() && Character.isDigit(command.charAt(i+1))) {
                    step = command.charAt(i+1)-'0';   // convert the digit character to a number
                    skip = 2;                          // the letter and the digit were both used
                }

                // Each repetition is a separate step
                for (int s = 0; s < step; s++) {

                    // ---------- Try the move (uppercase = Drone A, lowercase = Drone B) ----------
                    if (c == 'R') xA++;
                    if (c == 'L') xA--;
                    if (c == 'U') yA++;
                    if (c == 'D') yA--;
                    if (c == 'r') xB++;
                    if (c == 'l') xB--;
                    if (c == 'u') yB++;
                    if (c == 'd') yB--;

                    // ---------- Check the airspace boundary (-3 to 3 on both axes) ----------
                    if (xA < -3 || xA > 3 || xB < -3 || xB > 3 || yA < -3 || yA > 3 || yB < -3 || yB > 3) {
                        // Outside the airspace: blocked move
                        block++;
                        // Undo the move: go back to the last confirmed positions
                        xA = XA; yA = YA; xB = XB; yB = YB;
                    }
                    else {
                        // Inside the airspace: confirm the new positions
                        XA = xA; YA = yA; XB = xB; YB = yB;
                    }

                    total++;   // count this step (blocked steps are counted too)

                    // ---------- Compare the drones after EVERY step ----------
                    if (XA == XB && YA == YB) collide++;   // same cell = collision
                    else if (Math.abs(XA-XB) <= 1 && Math.abs(YA-YB) <= 1) miss++;   // touching cells = near miss

                    // Separation = x gap + y gap; keep the maximum
                    int sprt = Math.abs(XA-XB) + Math.abs(YA-YB);
                    separate = Math.max(separate, sprt);
                }

                i += skip;   // move to the next command
            }

            // ===================== 3. SHORTEST ROUTES =====================

            // Drone A: all X moves first (R or L), then all Y moves (U or D), in uppercase
            String A = "";
            for (int k = 0; k < Math.abs(XA); k++) {
                A += (XA > 0) ? "R" : "L";
            }
            for (int k = 0; k < Math.abs(YA); k++) {
                A += (YA > 0) ? "U" : "D";
            }
            if (XA == 0 && YA == 0) A = "HOME";   // Drone A ended at the start

            // Drone B: same idea, but in lowercase
            String B = "";
            for (int k = 0; k < Math.abs(XB); k++) {
                B += (XB > 0) ? "r" : "l";
            }
            for (int k = 0; k < Math.abs(YB); k++) {
                B += (YB > 0) ? "u" : "d";
            }
            if (XB == 0 && YB == 0) B = "HOME";   // Drone B ended at the start

            // ===================== 4. OUTPUT =====================

            System.out.println("Total Steps: " + total);
            System.out.println("Blocked Moves: " + block);
            System.out.println("Collisions: " + collide);
            System.out.println("Near Misses: " + miss);
            System.out.println("Maximum Separation: " + separate);
            System.out.println("Drone A Final: (" + XA + "," + YA + ") Route: " + A);
            System.out.println("Drone B Final: (" + XB + "," + YB + ") Route: " + B);
        }
    }

}
