
import java.util.*;

public class BigIntApp {

    public static void main(String[]args) {
        char[] validChar = {'+','-','/','*','<','>','='};
        String readIn;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            readIn = sc.nextLine();
            if (readIn != "" && readIn != "#") {
                String[] input = readIn.split(" ");
                if (input[0].matches("\\d+") && input.length == 3 && input[2].matches("\\d+")) {
                    BigInt first = new BigInt(input[0]);
                    BigInt second = new BigInt(input[2]);
                    if (input[1].equals("gcd")) {
                        System.out.println(readIn);
                        System.out.println("# " + first.greatestCommonDivisor(second));
                    } else {
                        switch (input[1].charAt(0)) {
                            case '-':
                                System.out.println(readIn);
                                System.out.println("# " + first.subtract(second));
                            case '+':
                                System.out.println(readIn);
                                System.out.println("# " + first.add(second));
                            case '/':
                                System.out.println(readIn);
                                System.out.println("# " + first.divideBy(second));
                            case '*':
                                System.out.println(readIn);
                                System.out.println("# " + first.multiplyBy(second));
                            case '<':
                                System.out.println(readIn);
                                System.out.println("# " + first.isLessThan(second));
                            case '>':
                                System.out.println(readIn);
                                System.out.println("# " + first.isGreaterThan(second));
                            case '=':
                                System.out.println(readIn);
                                System.out.println("# " + first.isEqualTo(second));
                        }
                    }
                    
                    
                    
                }
            }
        }
    }
    
    

}
