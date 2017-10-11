
public class BigInt {
    public String stringVal;
    public String printVal;
    public String remainder;
    
    
    public BigInt(String input) {
        this.printVal = input;
        this.stringVal = input; // if need to get rid of 0s at beginning do here

    }

    public String toString() {
        return printVal;
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


    public void add(BigInt s) {
        String result = "";
        boolean over = false;
        int indexOne = stringValue.length()-1;
        
        if (stringVal.length() < s.stringVal.length()) {
            int dif = s.stringValue.length() - stringVal.length();
            for (int i = indexOne; i >= 0; i--) {
                int x = Character.getNumericValue(s.stringValue.charAt(i+dif));
                int y = Character.getNumericValue(stringValue.charAt(i));
                int z = x+y;
                if (over) z++;
                over = false;
                if (z > 9) over = true;

                String number =  Integer.toString(z);
                result = number.substring(number.length()-1) + result;
            }
            int i = (s.stringVal.length() - stringVal.length());
            if (i == 0 && over) {
                result = "1" + result;
                return;
            
        }
    
        
    }
                


}
