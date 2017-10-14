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
        this.remainder = "0";
        convert(input);
    }
    
    public String toString() {
        return stringVal;
        
    }
    
    public String getRemainder() {
        return remainder;
    }
    
    public BigInt() {
        this.printVal = "";
        this.stringVal = ""; // if need to get rid of 0s at beginning do here
        this.number = new ArrayList<Integer>();
        this.remainder = "0";
    }
    
    
    public void convert(String input){
        if( input.length() == 0){
            this.printVal = "";
            this.stringVal = ""; // if need to get rid of 0s at beginning do here
            this.number = new ArrayList<Integer>();
            this.remainder = "0";
        } else {
            if(input.charAt(0) == '-'){
                this.negative = true;
                input = input.substring(1);
            } else {
                this.negative = false;
            }
            if(input.charAt(0) == '0' && input.length() == 1){
                this.printVal = "0";
                
            }else while(input.charAt(0) == '0' && input.length() > 0){
                input.substring(1);
            }
            for(int i = 0; i < input.length();i++){
                number.add(Character.getNumericValue(input.charAt(i)));
            }
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
    
    
    public BigInt add(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        ArrayList<String> res = new ArrayList<String>();
        Integer carry = 0;
        int indexOne = first.stringVal.length() - 1;
        int indexTwo = second.stringVal.length() - 1;
        
        //System.out.println(stringVal.charAt(0));
        //System.out.println(s.stringVal.charAt(0));
        
        // Deals with negative values
        if (first.negative && !second.negative) {
            //System.out.println("h1\n");
            first.stringVal = first.stringVal.substring(1);
            first.negative = false;
            //System.out.println(first.stringVal);
            BigInt r1 = second.subtract(first);
            return r1;
        }
        if (!first.negative && second.negative) {
            //System.out.println("h2\n");
            second.stringVal = second.stringVal.substring(1);
            second.negative = false;
            //System.out.println(second.stringVal);
            BigInt r2 = first.subtract(second);
            return r2;
        }
        if (first.negative && second.negative) {
            //System.out.println("h3\n");
            first.stringVal = first.stringVal.substring(1);
            second.stringVal = second.stringVal.substring(1);
            first.negative = false;
            second.negative = false;
            //System.out.println(first.stringVal);
            //second.stringVal = s.stringVal.substring(1);
            //System.out.println(second.stringVal);
            BigInt r3 = first.add(second);
            r3.negative = true;
            r3.stringVal = "-" + r3.stringVal;
            return r3;
        }
        
        if(first.isLessThan(second)){
            return second.add(first);
        }
        
        int fi = first.number.size()-1;
        int si = second.number.size()-1;
        String newString = "";

       // System.out.println("first: "+first);
       // System.out.println("second: "+second);
        while(si >= 0){
            int newNum = first.number.get(fi) + second.number.get(si);
         //   System.out.println(newNum);
            if(newNum > 9){
                newNum -= 10;
               // System.out.println(newNum);
                if(fi > 0){
                    Integer val = first.number.get(fi-1);
                    val = val +1;
                    first.number.set(fi-1,val);
                } else {
                    first.number.add(0,1);
                    fi++;
                }
            }
           // System.out.println(first.number);
            newString = String.valueOf(newNum) + newString;
           // System.out.println(newString);

            si--;
            fi--;
        }
        while(fi > si){
            newString = String.valueOf(first.number.get(fi)) + newString;
            fi--;
        }

        while(newString.length() > 0 && newString.charAt(0) == '0'){
            newString = newString.substring(1);
        }
        BigInt result = new BigInt(newString);
        //System.out.println(result);
        return result;
    }
    
    
    
    
    public BigInt subtract(BigInt s){
        BigInt first = new BigInt(this.stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt result;
        
        //System.out.println(first);
        //System.out.println(second);
        
        if(first.stringVal.equals("0")){
            return second;
        }
        if(second.stringVal.equals("0")){
            return first;
        }
        
        
        
        if(first.negative && second.negative){
            second.negative = false;
            second.stringVal = second.stringVal.substring(1);
            result = first.add(second);
            return result;
        }
        
        if(first.negative && !second.negative){
            first.negative = false;
            first.stringVal = first.stringVal.substring(1);
            result = first.add(second);
            result.negative = true;
            result.stringVal = "-" + result.stringVal;
            return result;
        }
        
        if(!first.negative && second.negative){
            second.negative = false;
            second.stringVal = second.stringVal.substring(1);
            return first.add(second);
        }
        
        if(first.isGreaterThan(second)){
            // System.out.println(first.number);
            //System.out.println(second.number);
            int fi = first.number.size()-1;
            int si = second.number.size()-1;
            String newString = "";
            
            // System.out.println(second.number.size());
            // System.out.println(si);
            while(si >= 0){
                // System.out.println("inf" + " " + si);
                int newNum = 0;
                if(first.number.get(fi) < second.number.get(si)){
                    //System.out.println(first.number);
                    //System.out.println(second.number);
                    newNum = first.number.get(fi) + 10;
                    newNum -= second.number.get(si);
                    Integer val = first.number.get(fi-1);
                    val = val-1;
                    first.number.set(fi-1,val);
                    // System.out.println(first.number);
                    newString = String.valueOf(newNum) + newString;
                    //System.out.println(newString);
                } else if (first.number.get(fi) >= second.number.get(si)){
                    // System.out.println(first.number);
                    // System.out.println(second.number);
                    newNum = first.number.get(fi) - second.number.get(si);
                    newString = String.valueOf(newNum) + newString;
                }
                si--;
                fi--;
            }
            //System.out.println("inf here");
            while(fi > si){
                //System.out.println("inf");
                
                //System.out.println(fi);
                newString = String.valueOf(first.number.get(fi)) + newString;
                fi--;
            }
            //System.out.println(newString);
            
            while(newString.charAt(0) == '0'){
                newString = newString.substring(1);
            }
            result = new BigInt(newString);
            //System.out.println(result);
            
            return result;
        }
        
        if(first.isLessThan(second)){
            //System.out.println("Should be revers -9000");
            result = second.subtract(first);
            result.negative = true;
            result.stringVal = "-" + result.stringVal;
            return result;
            
        }
        
        if(first.isEqualTo(second)){
            //System.out.println("Should be equal 0");
            result = new BigInt("0");
            return result;
        }
        return null;
    }

    
    public BigInt multiplyBy(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt res = new BigInt("0");
        String result = "";
        List<Integer> carryOverArray = new ArrayList<Integer>();
        int large;
        negative = false;
        
        /*Checks for negatives*/
        if(stringVal.charAt(0) == '-' && s.stringVal.charAt(0) == '-') {
            negative = false;
            first.stringVal = stringVal.substring(1);
            second.stringVal = s.stringVal.substring(1);
        }else if(stringVal.charAt(0) == '-'){
            negative = true;
            first.stringVal = stringVal.substring(1);
        }else if(s.stringVal.charAt(0) == '-'){
            negative = true;
            second.stringVal = s.stringVal.substring(1);
        }
        
        if (stringVal.length() > s.stringVal.length( )) {
            large = first.stringVal.length();
        }else{
            large = second.stringVal.length();
        }
        /*Increase for larger numbers*/
        large+=4;
        for(int i =0; i < large ; i++){
            int carryOver = 0;
            for(int j = 0; j < large; j++){
                int x, y;
                if(j >= second.stringVal.length() || i>= first.stringVal.length()){
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
        if(negative == true){
            result = "-" + result;
        }
        res.stringVal = result;
        return res;
    }
    
    public BigInt divideBy(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt res = new BigInt("0");
        String ans = "";
        String remainder = "";
        negative = false;

        if(first.negative && second.negative){
            res.negative = false;
            first.stringVal = first.stringVal.substring(1);
            second.stringVal = second.stringVal.substring(1);
            //System.out.println("Here1");
        }else if(first.negative){
            res.negative = true;
            first.stringVal = first.stringVal.substring(1);
            //System.out.println("Here2");
        }else if(second.negative){
            res.negative = true;
            second.stringVal = second.stringVal.substring(1);
           // System.out.println("Here3");
        }
        BigInt temp = new BigInt(first.stringVal);
        //System.out.println("Divisor: " + temp.stringVal);
        while(temp.isGreaterThan(second) || temp.isEqualTo(second)){
            BigInt one = new BigInt("1");
           // System.out.println(temp);
            temp = temp.subtract(second);
            //System.out.println(res.stringVal);
          //  System.out.println("res: "+ res + " " + one);
            res = res.add(one);
            //System.out.println(res);
            //System.out.println("Temp: " + temp.stringVal);
        }
        remainder = temp.toString();
        first.stringVal = res.toString();
        if(negative == true){
            res.stringVal = "-" + res.stringVal;
            //System.out.println("Here5");
        }
      //  System.out.println(remainder);
      //  System.out.println(res);
        res.remainder = remainder;
        return res;
    }
    
    
    public BigInt greatestCommonDivisor(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt temp;
        BigInt quotient;
        BigInt zero = new BigInt("0");
        BigInt remainder = new BigInt("-1");
        
        if(first.isGreaterThan(second)){
            first = new BigInt(stringVal);
            second = new BigInt(s.stringVal);
        } else {
            first = new BigInt(s.stringVal);
            second = new BigInt(stringVal);
        }
        //System.out.println("hello1" + remainder.stringVal);
        while(!remainder.isEqualTo(zero)){
            quotient = first.divideBy(second);
            //System.out.println("hello1" + remainder.stringVal);
            remainder = new BigInt(quotient.getRemainder());
           // System.out.println(quotient);
           // System.out.println(quotient.getRemainder());

            if(remainder.isEqualTo(zero)){
                return second;
            }
            //System.out.println("hello" + remainder.stringVal);
            first = second;
            second = remainder;
        }
        return second;
    }
}
