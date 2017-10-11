import java.util.*;


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
        int indexOne = stringVal.length()-1;
        
        if (stringVal.length() < s.stringVal.length()) {
            int dif = s.stringVal.length() - stringVal.length();
            for (int i = indexOne; i >= 0; i--) {
                int x = Character.getNumericValue(s.stringVal.charAt(i+dif));
                int y = Character.getNumericValue(stringVal.charAt(i));
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
    
    
    public void multiply(BigInt s){
        String result = "";
        List<Integer> carryOverArray = new ArrayList<Integer>();
        int large;
        boolean neg = false;
        
        /*Checks for negatives*/
        if(stringVal.charAt(0) == '-' && s.stringVal.charAt(0) == '-') {
            neg = false;
            stringVal = stringVal.substring(1);
            s.stringVal = s.stringVal.substring(1);
        }else if(stringVal.charAt(0) == '-'){
            neg = true;
            stringVal = stringVal.substring(1);
        }else if(s.stringVal.charAt(0) == '-'){
            neg = true;
            s.stringVal = s.stringVal.substring(1);
        }
        
        if (stringVal.length() > s.stringVal.length( )) {
            large = stringVal.length();
        }else{
            large = s.stringVal.length();
        }
        /*Increase for larger numbers*/
        large+=4;
        for(int i =0; i < large ; i++){
            int carryOver = 0;
            for(int j = 0; j < large; j++){
                int x, y;
                if(j >= s.stringVal.length() || i>= stringVal.length()){
                    x = 0;
                    y = 0;
                }else{
                    x = Character.getNumericValue(stringVal.charAt(stringVal.length()-1-i));
                    y = Character.getNumericValue(s.stringVal.charAt(stringVal.length()-1-j));
                }
                if(carryOverArray.isEmpty()||carryOverArray.size() <= (i+j)){
                    carryOverArray.add(i+j,0);
                }
                int z = (x*y) + carryOverArray.get(i+j) + carryOver;
                carryOver = z/10;
                carryOverArray.set(i+j,z%10);
            }
            
        }
        
        for(int i = 0; i < carryOverArray.size();i++){
            result = result + carryOverArray.get(carryOverArray.size()-1-i);
        }
        for(int i = 0; i < carryOverArray.size()-1;i++){
            if(result.charAt(0) != '0'){
                break;
            }
            result = result.substring(1);
        }
        if(neg == true){
            result = "-" + result;
        }
        this.stringVal = result;
        
    }
}
