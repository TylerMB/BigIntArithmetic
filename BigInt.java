
public class BigInt {
    public String stringVal;
    public String remainder;
    
    
    public BigInt(String input) {
        this.stringVal = input;

    }

    public String toString() {
        return stringVal;
    }



    public boolean isEqualTo(BigInt s) {
        if (!(stringVal.charAt(0) == (s.stringVal.charAt(0)))) {
            return false;
        } else {
            // either both +ve or -ve
            if (stringVal.length() != s.stringVal.length()) {
                return false;
            }
            for (int i = 0; i < stringVal.length(); i++) {
                if (stringVal.charAt(i) != s.stringVal.charAt(i)) {
                    return false;
                     
                }
            }
            return true;
        }

    }

    public boolean isGreaterThan(BigInt s) {        
        if (stringVal.charAt(0) == '-' && s.stringVal.charAt(0) != '-') {
            return false;
        } else if (stringVal.charAt(0) != '-' && s.stringVal.charAt(0) == '-') {
            return true;
        } else {
            // Both are either +ve or -ve
            if (stringVal.length() > s.stringVal.length()) {
                return true;
            } else if (stringVal.length() < s.stringVal.length()) {
                return false;
            } else {
                for (int i = 0; i < stringVal.length();i++) {
                    if (stringVal.charAt(i) < s.stringVal.charAt(i)) {
                        return false;
                    }
                    if (stringVal.charAt(i) != s.stringVal.charAt(i)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public boolean isLessThan(BigInt s) {        
        if (stringVal.charAt(0) == '-' && s.stringVal.charAt(0) != '-') {
            return true;
        } else if (stringVal.charAt(0) != '-' && s.stringVal.charAt(0) == '-') {
            return false;
        } else {
            // Both are either +ve or -ve
            if (stringVal.length() < s.stringVal.length()) {
                return true;
            } else if (stringVal.length() > s.stringVal.length()) {
                return false;
            } else {
                for (int i = 0; i < stringVal.length();i++) {
                    if (stringVal.charAt(i) > s.stringVal.charAt(i)) {
                        return false;
                    }
                    if (stringVal.charAt(i) != s.stringVal.charAt(i)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
                


}
