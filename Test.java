

public class Test {

    public static void main(String[] args) {

        // isEqualTo testing
        System.out.println("\nIsEqualTo Testing... \n");
            
        BigInt first = new BigInt("123456789");
        BigInt second = new BigInt("-123456789");
        BigInt test1 = new BigInt("123456789");
        BigInt test2 = new BigInt("-123456789");
        BigInt test3 = new BigInt("-12345678");
 
        System.out.println("Should be true: " + first.isEqualTo(test1));
        System.out.println("Should be false: " + first.isEqualTo(test2));
        System.out.println("Should be false: " + first.isEqualTo(test3));
        System.out.println("Should be true: " + second.isEqualTo(test2));
        System.out.println("Should be false: " + second.isEqualTo(test3));

        //isGreaterThan testing
        System.out.println("\nIsGreaterThan Testing... \n");
        
        BigInt gThan1 = new BigInt("12345");
        BigInt gThan2 = new BigInt("-12345");
        BigInt gThan3 = new BigInt("-12345");
        BigInt gThan4 = new BigInt("21345");

        System.out.println("Should be true: " + gThan1.isGreaterThan(gThan2));
        System.out.println("Should be false: " + gThan2.isGreaterThan(gThan3));
        System.out.println("Should be true: " + gThan4.isGreaterThan(gThan2));
        System.out.println("Should be false: " + gThan1.isGreaterThan(gThan4));

        //isLessThan testing
        System.out.println("\nIsGreaterThan Testing... \n");

        System.out.println("Should be false: " + gThan1.isLessThan(gThan2));
        System.out.println("Should be false: " + gThan2.isLessThan(gThan3));
        System.out.println("Should be false: " + gThan4.isLessThan(gThan2));
        System.out.println("Should be true: " + gThan1.isLessThan(gThan4));
        
        //Subtraction testing
        System.out.println("\nSubtraction Testing... \n");

        System.out.println("Should be 9000: " + gThan4.subtract(gThan1));
        System.out.println("Should be equal 0: " + gThan1.subtract(gThan1));
        System.out.println("Should be revers -9000: " + gThan1.subtract(gThan4));
        
        
        //Addition testing
        
        System.out.println("\nAddition Testing... \n");
        
        BigInt test = gThan4.add(gThan1);
        System.out.println("Should be 33690: " + test.stringVal);
        System.out.println("Should be 24690: " + gThan1.add(gThan1));
        System.out.println("Should be 0: " + gThan1.add(gThan2));
        System.out.println("Should be -24690: " + gThan3.add(gThan2));
        
        
        // Division Testing
        System.out.println("\nDivision Testing...\n");
        
        System.out.println("Should be 1: " + gThan1.divideBy(gThan1));
        
                           
        
    }
}
