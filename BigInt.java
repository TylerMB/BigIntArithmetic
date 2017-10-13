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
        if(input.charAt(0) == '0' && input.length() == 1){
            this.printVal = "0";

        }else while(input.charAt(0) == '0' && input.length() > 0){
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
    
    
    public BigInt add(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        ArrayList<String> res = new ArrayList<String>();
        Integer carry = 0;
        int indexOne = stringVal.length() - 1;
        int indexTwo = s.stringVal.length() - 1;
        
        //System.out.println(stringVal.charAt(0));
        //System.out.println(s.stringVal.charAt(0));
        
        // Deals with negative values
        if (stringVal.charAt(0) == '-' && s.stringVal.charAt(0) != '-') {
            //System.out.println("h1\n");
            first.stringVal = first.stringVal.substring(1);
            //System.out.println(first.stringVal);
            BigInt r1 = first.subtract(second);
            return r1;
        }
        if (stringVal.charAt(0) != '-' && s.stringVal.charAt(0) == '-') {
            System.out.println("h2\n");
            second.stringVal = second.stringVal.substring(1);
            //System.out.println(second.stringVal);
            BigInt r2 = second.subtract(first);
            return r2;
        }
        if (stringVal.charAt(0) == '-' && s.stringVal.charAt(0) == '-') {
            System.out.println("h3\n");
            first.stringVal = first.stringVal.substring(1);
            //System.out.println(first.stringVal);
            //second.stringVal = s.stringVal.substring(1);
            //System.out.println(second.stringVal);
            BigInt r3 = second.subtract(first);
            
            return r3;
        }
        
        // Only deals with positive values
        while(true){
            int x = Integer.parseInt(Character.toString(stringVal.charAt(indexOne)));
            int y = Integer.parseInt(Character.toString(s.stringVal.charAt(indexTwo)));
            Integer z  = x + y + carry;
            if(z > 9){
                carry = 1;
                z = z - 10;
            }else carry = 0;
            res.add(z.toString());
            indexOne--;indexTwo--;
            if(indexOne < 0){
                res.add(carry.toString());
                break;
            }
        }
        String temp = "";
        Collections.reverse(res);
        for(String r : res){
            temp += r;
        }
        //System.out.println(temp);
        if (temp.charAt(0) == '0') {
            temp = temp.substring(1);
        }
        //System.out.println(temp);
        BigInt result = new BigInt(temp);
        return result;
    }
    
    
    
    
    public BigInt subtract(BigInt s){
        BigInt first = new BigInt(this.stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt result;

        //System.out.println(first);
        //System.out.println(second);


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

            int fi = first.number.size()-1;
            int si = second.number.size()-1;
            String newString = "";

            while(si > 0){
                int newNum = 0;
                if(first.number.get(fi) < second.number.get(si)){
                    newNum = first.number.get(fi) + 10;
                    newNum -= second.number.get(si);
                    Integer val = first.number.get(fi-1);
                    val = val-1;
                    first.number.set(fi-1,val);
                    newString = String.valueOf(newNum) + newString;
                    //System.out.println(newString);
                } else if (first.number.get(fi) >= second.number.get(si)){
                    newNum = first.number.get(fi) - second.number.get(si);
                    newString = String.valueOf(newNum) + newString;
                }
                si--;
                fi--;
            }
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

    
    public void multiplyBy(BigInt s){
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
    
    public BigInt divideBy(BigInt s){
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        BigInt res = new BigInt("0");
        String ans = "";
        String remainder = "";
        boolean negative = false;

        if(stringVal.charAt(0) == '-' && s.stringVal.charAt(0) == '-'){
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
        BigInt temp = new BigInt(stringVal);
        while(temp.isGreaterThan(second) || temp.isEqualTo(second)){
            BigInt one = new BigInt("1");
            temp.subtract(second);
            res.add(one);
        }
        remainder = temp.toString();
        first.stringVal = res.toString();
        if(negative == true){
            stringVal = "-" + stringVal;
        }
        
        this.remainder = remainder;
        return first;
    }
}
