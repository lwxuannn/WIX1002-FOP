import java.util.Scanner;
public class Q6TwoDronesOneSky {
    public static void main (String []args) {
        Scanner cs = new Scanner (System.in);
        
        System.out.print("Enter flight commands: ");
        String command = cs.nextLine();
        
        boolean valid = true;
        if (command.length() == 0 || Character.isDigit(command.charAt(0))) {
            valid = false;
        }
        
        for (int i = 0; i < command.length() && valid; i++) {
            char c = command.charAt(i);
            if(Character.isDigit(c)) {
                if (i + 1 < command.length() && Character.isDigit(command.charAt(i+1))) valid = false;
                if (c < '2' || c > '9') valid = false;
            }
            else if ("UDLRudlr".indexOf(c) == -1) valid = false;
        }
        
        if (!valid) {
            System.out.println("Invalid Command String");
            return;
        }
        
        int xA = 0, yA = 0, xB = 0, yB = 0, XA = 0, YA = 0, XB = 0, YB = 0;
        int collide = 0, miss = 0, block = 0, separate = 0, total = 0;
        
        int i = 0;
        while (i < command.length()) {
            char c = command.charAt(i);
            int skip = 1;
            int step = 1;
            if (i + 1 < command.length() && Character.isDigit(command.charAt(i+1))) {
                step = command.charAt(i+1)-'0';
                skip = 2;
            }

            
            for (int s = 0; s < step; s++) {
             if (c == 'R') xA++;
             if (c == 'L') xA--;
             if (c == 'U') yA++;
             if (c == 'D') yA--;
             if (c == 'r') xB++;
             if (c == 'l') xB--;
             if (c == 'u') yB++;
             if (c == 'd') yB--;
            
            if (xA < -3 || xA > 3 || xB < -3 || xB > 3 || yA < -3 || yA > 3 || yB < -3 || yB > 3) {
                 block++;
                 xA = XA; yA = YA; xB = XB; yB = YB;
            } 
            else {
                XA = xA; YA = yA; XB = xB; YB = yB;
            }
            total++;
            
            if (XA == XB && YA == YB) collide++;
            else if (Math.abs(XA-XB) <= 1 && Math.abs(YA-YB) <= 1) miss++;
            
            int sprt = Math.abs(XA-XB) + Math.abs(YA-YB);
            separate = Math.max(separate, sprt);
            }
            i += skip;
        }
        
        //Calculate route
        String A = "";
        for (int k = 0; k < Math.abs(XA); k++) {
            A += (XA > 0) ? "R" : "L";
        }
        for (int k = 0; k < Math.abs(YA); k++) {
            A += (YA > 0) ? "U" : "D";
        }
        if (XA == 0 && YA == 0) A = "HOME";
        
        String B = "";
        for (int k = 0; k < Math.abs(XB); k++) {
            B += (XB > 0) ? "r" : "l";
        }
        for (int k = 0; k < Math.abs(YB); k++) {
            B += (YB > 0) ? "u" : "d";
        }
        if (XB == 0 && YB == 0) B = "HOME";
        
        System.out.println("Total Steps: " + total);
        System.out.println("Blocked Moves: " + block);
        System.out.println("Collisions: " + collide);
        System.out.println("Near Misses: " + miss);
        System.out.println("Maximum Separation: " + separate);
        System.out.println("Drone A Final: (" + XA + "," + YA + ") Route: " + A);
        System.out.println("Drone B Final: (" + XB + "," + YB + ") Route: " + B);
    }
    
}
