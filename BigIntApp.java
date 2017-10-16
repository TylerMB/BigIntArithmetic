
import java.util.*;

public class BigIntApp {
    
    public static void main(String[]args) {
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> readOutput = new ArrayList<String>();
        char[] validChar = {'+','-','/','*','<','>','='};
        String readIn;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            readIn = sc.nextLine();
            if (!readIn.isEmpty() && readIn.charAt(0) != '#') {
                if (readIn.charAt(0) != ' ') {
                    String[] input = readIn.split(" ");
                    if (input[0].matches("\\d+") && input.length == 3 && input[2].matches("\\d+")) {
                        readOutput.add(readIn);
                        output.add(input[0]);
                        output.add(input[1]);
                        output.add(input[2]);
                    } else {
                        readOutput.add(readIn);
                        output.add("1");
                        output.add("error");
                        output.add("1");
                    }
                }
                
            }
        }
        
        int j = 0;
        for (int i = 0; i < output.size(); i+=3) {
            
            BigInt first = new BigInt(output.get(i));
            BigInt second = new BigInt(output.get(i+2));
            
            switch (output.get(i+1)) {
                case "gcd":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.greatestCommonDivisor(second));
                    j++;
                    break;
                case "-":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.subtract(second));
                    j++;
                    break;
                case "+":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.add(second));
                    j++;
                    break;
                case "/":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.divideBy(second));
                    j++;
                    break;
                case "*":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.multiplyBy(second));
                    j++;
                    break;
                case "<":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.isLessThan(second));
                    j++;
                    break;
                case ">":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.isGreaterThan(second));
                    j++;
                    break;
                case "=":
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# " + first.isEqualTo(second));
                    j++;
                    break;
                default:
                    System.out.println("\n" + readOutput.get(j));
                    System.out.println("# Syntax error");
                    j++;
                    break;
            }
        }
    }
    
    
}

