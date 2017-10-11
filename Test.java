

public class Test {

    public static void main(String[] args) {
        BigInt first = new BigInt("123456789");
        BigInt second = new BigInt("123456789");
        BigInt third = new BigInt("-123456789");
        BigInt fourth = new BigInt("-12345678");
        BigInt fifth = new BigInt("123456780");
        BigInt sixth = new BigInt("-123456789");

        System.out.println("Should be true: " + first.isEqualTo(second));
        System.out.println("Should be false: " + first.isEqualTo(third));
        System.out.println("Should be false: " + first.isEqualTo(fourth));
        System.out.println("Should be true: " + third.isEqualTo(sixth));
        System.out.println("Should be false: " + third.isEqualTo(fourth));

}
