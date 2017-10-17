
import java.util.*;

public class BigIntApp {
  
  public static void main(String[]args) {
    ArrayList<String> output = new ArrayList<String>();
    ArrayList<String> readOutput = new ArrayList<String>();
    char[] validChar = {'+','-','/','*','<','>','='};
    String readIn;
    String temp;
    
    BigInt one;
    BigInt two;
    
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      readIn = sc.nextLine();
      if (!readIn.isEmpty() && readIn.charAt(0) != '#') {
        if (readIn.charAt(0) != ' ') {
          String[] input = readIn.split(" ");
          if (input[0].matches("-?\\d+") && input.length == 3 && input[2].matches("-?\\d+")) {
            
            one = new BigInt(input[0]);
            two = new BigInt(input[2]);
            
//            if (one.isLessThan(two)) {
//              temp = input[0];
//              input[0] = input[2];
//              input[2] = temp;
//            }
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
    char c = '+';
    int j = 0;
    for (int i = 0; i < output.size(); i+=3) {
      
      BigInt first = new BigInt(output.get(i));
      BigInt second = new BigInt(output.get(i+2));
      
      
      
      switch (output.get(i+1).charAt(0)) {
        case 'g':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.greatestCommonDivisor(second));
          j++;
          break;
        case '-':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.subtract(second));
          j++;
          break;
        case '+':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.add(second));
          j++;
          break;
        case '/':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.divideBy(second) +" "+first.divideBy(second).remainder);
          j++;
          break;
        case '*':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.multiplyBy(second));
          j++;
          break;
        case '<':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.isLessThan(second));
          j++;
          break;
        case '>':
          System.out.println("\n" + readOutput.get(j));
          System.out.println("# " + first.isGreaterThan(second));
          j++;
          break;
        case '=':
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

