import java.util.*;


public class BigInt {
    public String stringVal;
    public String printVal;
    public String remainder;
    public ArrayList<Integer> number;
    public boolean negative;
    
    public BigInt(String input) {
        this.printVal = input;
        this.stringVal = input; // if need to get rid of 0s at beginning do here
        this.number = new ArrayList<Integer>();
        convert(input);
    }
    
    public String toString() {
        return stringVal;
    }
    
    public BigInt() {
        this.printVal = "";
        this.stringVal = ""; // if need to get rid of 0s at beginning do here
        this.number = new ArrayList<Integer>();
    }
    
    
    public void convert(String input){
        if(input.charAt(0) == '-'){
            this.negative = true;
            input = input.substring(1);
        } else {
            this.negative = false;
        }
        while(input.charAt(0) == '0' && input.length() > 0){
            input.substring(1);
        }
        for(int i = 0; i < input.length();i++){
            number.add(Character.getNumericValue(input.charAt(i)));
        }
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
    
    
    public BigInt add(BigInt s) {
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
                return null;
                
            }
        }
        return s;
    }
    
    
    
    
    public BigInt subtract(BigInt s){
        BigInt first = new BigInt(this.stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt result;
        
        System.out.println(first);
        System.out.println(second);
        
        
        if(first.negative && second.negative){
            second.negative = false;
            second.stringVal = second.stringVal.substring(0);
            result = first.add(second);
            return result;
        }
        
        if(first.negative && !second.negative){
            first.negative = false;
            first.stringVal = first.stringVal.substring(0);
            result = first.add(second);
            result.negative = true;
            result.stringVal = "-" + result.stringVal;
            return result;
        }
        
        if(!first.negative && second.negative){
            second.negative = false;
            second.stringVal = second.stringVal.substring(0);
            return first.add(second);
        }
        
        if(first.number.size() == second.number.size()){
        
            
            
            int fi = first.number.size()-1;
            int si = second.number.size()-1;
            String newString = "";
            
            System.out.println("here");
            
            while(si > 0){
                int newNum = 0;
                if(first.number.get(fi) < second.number.get(si)){
                    newNum = first.number.get(fi) + 10;
                    newNum -= second.number.get(si);
                    Integer val = first.number.get(fi-1);
                    val = val-1;
                    first.number.set(fi-1,val);
                    newString = String.valueOf(newNum) + newString;
                    System.out.println(newString);
                } else if (first.number.get(fi) >= second.number.get(si)){
                    newNum = first.number.get(fi) - second.number.get(si);
                    newString = String.valueOf(newNum) + newString;
                    System.out.println(newString);
                }
                si--;
                fi--;
            }
            
            System.out.println(newString);

            
            while(fi > 1){
                if(first.number.get(fi) < 0){
                    Integer val = first.number.get(fi-1);
                    val = val-1;
                    first.number.set(fi-1,val);
                    val = first.number.get(fi);
                    val = val+10;
                    first.number.set(fi,val);
                }
                fi--;
            }
            
            result = new BigInt(newString);
            return result;
        }
        return null;
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
