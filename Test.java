

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
        BigInt gThan5 = new BigInt("213456");
        BigInt ten = new BigInt("100");
        BigInt two = new BigInt("60");
        BigInt one = new BigInt("1");
        BigInt zero = new BigInt("0");
        BigInt neg1 = new BigInt("-1");

        
        System.out.println("Should be true: " + gThan1.isGreaterThan(gThan2));
        System.out.println("Should be false: " + gThan2.isGreaterThan(gThan3));
        System.out.println("Should be true: " + gThan4.isGreaterThan(gThan2));
        System.out.println("Should be false: " + gThan1.isGreaterThan(gThan4));
        System.out.println("Should be true: " + one.isGreaterThan(zero));
        System.out.println("Should be false: " + zero.isGreaterThan(zero));
        System.out.println("Should be false: " + zero.isGreaterThan(one));

        
        
        
        //isLessThan testing
        System.out.println("\nIsLessThan Testing... \n");

        System.out.println("Should be false: " + gThan1.isLessThan(gThan2));
        System.out.println("Should be false: " + gThan2.isLessThan(gThan3));
        System.out.println("Should be false: " + gThan4.isLessThan(gThan2));
        System.out.println("Should be true: " + gThan1.isLessThan(gThan4));
        System.out.println("Should be true: " + zero.isLessThan(one));
        System.out.println("Should be false: " + zero.isLessThan(zero));
        System.out.println("Should be false: " + zero.isLessThan(neg1));
        System.out.println("Should be false: " + one.isLessThan(neg1));
        System.out.println("Should be true: " + neg1.isLessThan(one));

        BigInt huge = new BigInt("123456789987654321123456789");
        BigInt huger = new BigInt("987654321123456789987654321");


        //Subtraction testing
        System.out.println("\nSubtraction Testing... \n");

        System.out.println("Should be 9000: " + gThan4.subtract(gThan1));
        System.out.println("Should be equal 0: " + gThan1.subtract(gThan1));
        System.out.println("Should be revers -9000: " + gThan1.subtract(gThan4));
        System.out.println("Should be 201111 : " + gThan5.subtract(gThan1));
        System.out.println("Should be 0 : " + one.subtract(one));
        System.out.println("Should be -1 : " + zero.subtract(one));
        System.out.println("Should be 1 : " + zero.subtract(neg1));
        System.out.println("Should be -2 : " + neg1.subtract(one));
        System.out.println("Should be neg : " + huge.subtract(huger));
        System.out.println("Should be pos : " + huger.subtract(huge));
        System.out.println("Should be 0 : " + zero.subtract(zero));




        //Addition testing
        
        System.out.println("\nAddition Testing... \n");
        
        BigInt test = gThan4.add(gThan1);
        System.out.println("Should be 33690: " + test.stringVal);
        System.out.println("Should be 24690: " + gThan1.add(gThan1));
        System.out.println("Should be 0: " + gThan1.add(gThan2));
        System.out.println("Should be -24690: " + gThan3.add(gThan2));
        System.out.println("Should be 160: " + ten.add(two));
        System.out.println("Should be 0 : " + neg1.add(one));
        System.out.println("Should be -2 : " + neg1.add(neg1));
        System.out.println("Should be 1 : " + one.add(zero));
        System.out.println("Should be -1 : " + neg1.add(zero));
        System.out.println("Should be 0 : " + zero.add(zero));
        System.out.println("Should be big : " + huge.add(huger));


        
        
        // Division Testing
        System.out.println("\nDivision Testing...\n");
        
        System.out.println("Should be 1: " + gThan1.divideBy(gThan1));
        System.out.println("Should be 0 r huge: " + huge.divideBy(huger) +" r " + huge.divideBy(huger).remainder);
        System.out.println("Should be error: " + zero.divideBy(one));
        System.out.println("Should be -1: " + neg1.divideBy(one));
        System.out.println("should be 17 r 3951:" + gThan5.divideBy(gThan1) +" r " + gThan5.divideBy(gThan1).remainder);
        
        //Multiplication testing
        
        System.out.println("\nMultiplication Testing...\n");
        System.out.println("Should be 152399025: "  + gThan1.multiplyBy(gThan1));
        System.out.println("Should be 152399025: "  + gThan1.multiplyBy(gThan2));
        System.out.println("Should be 152399025: "  + huger.multiplyBy(huge));
        System.out.println("Should be 152399025: "  + neg1.multiplyBy(huge));
        System.out.println("Should be 152399025: "  + gThan2.multiplyBy(gThan2));
        System.out.println("Should be 152399025: "  + huge.multiplyBy(gThan2));
        
        //Greatest Common Diviser
        
        System.out.println("\nGreatest Common Divisor Testing...\n");
        System.out.println("Should be 20: " + ten.greatestCommonDivisor(two));
        System.out.println("Should be 3: " + gThan5.greatestCommonDivisor(gThan1));
        
    }
}
