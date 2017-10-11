
public class BigInt {
    public String stringVal;
    public String remainder;
    
    
    public BigInt(String input) {
        this.stringValue = input;

    }

    public String toString() {
        return stringValue;
    }



    public boolean isEqualTo(String s) {
        if !(stringValue.charAt(0).isEqual(s.charAt(0))) {
                return false;
                else {
                    // either both +ve or -ve
                    if (stringValue.length() != s.length()) {
                        return false;
                    }
                    for (int i = 0; i < d.length(); i++) {
                        if (stringValue.charAt(i) != s.charAt(i)) {
                            return false;
                     
                        }
                    }
                    return true;
                }
    
    



}
