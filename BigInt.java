import java.util.*;
import java.lang.*;


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
        //convert(input);
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
                this.stringVal = "0";
                
            }else while(input.charAt(0) == '0' && input.length() > 0){
                input.substring(1);
            }
            for(int i = 0; i < input.length();i++){
                this.number.add(Character.getNumericValue(input.charAt(i)));
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
    
    
public BigInt add(BigInt s) {
  BigInt first = new BigInt(stringVal);
  BigInt second = s;
  BigInt result;
  
    if (!negative && second.stringVal.charAt(0) == '-') {
      result = new BigInt(second.toString().substring(1, second.stringVal.length()));
      return subtract(result);
    }
    if (negative && second.toString().charAt(0) != '-') {
      BigInt temp = new BigInt(stringVal);
      
      if (temp.isLessThan(second)) {
        result = second.subtract(temp);
        return result;
        
      } else if (temp.isGreaterThan(second)) {
        result = new BigInt("-" + temp.subtract(second));
        return result;
      } else {
        result = new BigInt("0");
        return result;
      }
    }
    
    if (negative && second.stringVal.charAt(0) == '-') {
      BigInt temp = new BigInt(stringVal);
      BigInt temp1 = new BigInt(second.toString().substring(1,second.stringVal.length()));
      result = new BigInt("-" + temp.add(temp1).stringVal);
      return result;
    }
    String oStr;
    if (second.toString().charAt(0) == '-') {
      oStr = second.stringVal.substring(1, second.stringVal.length());;
    } else {
      oStr = second.stringVal;
    }
    String longest;
    String shortest;
    if (stringVal.length() != oStr.length()) {
      longest = stringVal.length() > oStr.length() ? stringVal : oStr;
      shortest = stringVal.length() < oStr.length() ? stringVal : oStr;
    } else {
      longest = stringVal;
      shortest = oStr;
    }
    int carry = 0;
    String newNum = "";
    int diff = longest.length()-shortest.length();
    for (int i = longest.length()-1; i >= 0; i--) {
      if (i-diff >= 0) {
        int num = Integer.parseInt(String.valueOf(longest.charAt(i))) + 
          Integer.parseInt(String.valueOf(shortest.charAt(i-diff)));
        num += carry;
        carry = num / 10;
        newNum = num % 10 + newNum;
      } else {
        int num = Integer.parseInt(String.valueOf(longest.charAt(i)));
        num += carry;
        carry = num / 10;
        newNum = num % 10 + newNum;
      }
    }
    if (carry != 0) {
      newNum = carry + newNum;
    }
    return new BigInt(newNum);
  }
    
    
    
    
    public BigInt subtract(BigInt s){
        BigInt ONE = new BigInt("1");
        BigInt ZERO = new BigInt("0");
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
            String tempString = newString;
            if (newString.charAt(0) == '-') {
                tempString = newString.substring(1);
            }
            BigInt minusOne = new BigInt("1");
            BigInt digit;
            BigInt ans;
            while (tempString.contains("-1")) {
                int index = tempString.indexOf('-');
                tempString = tempString.replace("-1", "9");
                
                digit = new BigInt(Character.toString(tempString.charAt(index-1)));
                ans = digit.subtract(minusOne);
                
//                System.out.println(digit.stringVal + " " + minusOne.stringVal);
                
                tempString = tempString.substring(0, index-1) + ans.stringVal + tempString.substring(index);
                
                newString = tempString;
//                System.out.println(newString);
            }

            if (newString.charAt(0) == '0') {
                newString = newString.substring(1);
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
    
    
 public BigInt multiplyBy(BigInt o) {
    boolean neg = false;
    String str;
    if (toString().charAt(0) == '-') {
      str = toString().substring(1, toString().length());
      neg = true;
    } else {
      str = toString();
    }
    String oStr;
    if (o.toString().charAt(0) == '-') {
      oStr = o.toString().substring(1, o.toString().length());
      neg = !neg;
    } else {
      oStr = o.toString();
    }
    BigInt total = new BigInt("0");
    
    for (int i = 0; i < str.length(); i++) {
      int carry = 0;
      String value = "";
      for (int t = oStr.length()-1; t >= 0; t--) {
        int res = Integer.parseInt(String.valueOf(str.charAt(i))) * 
          Integer.parseInt(String.valueOf(oStr.charAt(t)));
        res += carry;
        carry = res / 10;
        res = res % 10;
        value = res + value;
      }
      value = carry + value;
      for (int z = str.length()-i-2; z >= 0; z--) {
        value += "0";
      }
      total = total.add(new BigInt(value));
    }
    total = new BigInt((neg ? "-" : "") + total.toString());
    return total; 
  }
  
    
    
    
    public BigInt truncate(){
      BigInt first = new BigInt(stringVal);
      BigInt result;
      boolean neg = false;
      
      if(negative){
        neg = true;
        first.stringVal = first.stringVal.substring(1);
      }
      
      result = new BigInt(first.stringVal);
      
      for(int i = result.number.size()-1; i >= 0; i--){
        
        int newNum = first.number.get(i).intValue();
        if(newNum % 2 != 0 && i != (result.number.size()-1)){
          result.number.set(i+1,result.number.get(i+1)+5);
        }
        newNum /= 2;
        result.number.set(i,newNum);
        
      }
      result.stringVal = "";
      if(neg){
        result.negative = true;
        result.stringVal = "-";
      } else {
        result.negative = false;
      }
      
      
      
      for(Integer num : result.number){
        result.stringVal = result.stringVal + String.valueOf(num);
      }
      
      while(result.stringVal.length() > 0 && result.stringVal.charAt(0) == '0'){
        result.stringVal = result.stringVal.substring(1);
        result.number.remove(0);
      }
      
      
      return result;
      
    }
    
    
    
    
    
    public BigInt divideBy(BigInt s){
      BigInt TWO = new BigInt("2");
      BigInt ONE = new BigInt("1");
      BigInt ZERO = new BigInt("0");
      BigInt first = new BigInt(stringVal);
      BigInt second = new BigInt(s.stringVal);
      BigInt res = new BigInt("0");
      BigInt counter = new BigInt("1");
      String ans = "";
      String remainder = "";
      boolean neg = false;
      
      
      if(first.isEqualTo(ZERO) || second.isEqualTo(ZERO)){
        return ZERO;
      }
      
      if(first.negative && second.negative){
        neg = false;
        first.stringVal = first.stringVal.substring(1);
        second.stringVal = second.stringVal.substring(1);
        //System.out.println("Here1");
      }else if(first.negative){
        neg = true;
        first.stringVal = first.stringVal.substring(1);
        first.negative = false;
      }else if(second.negative){
        neg = true;
        second.stringVal = second.stringVal.substring(1);
        second.negative = false;
        // System.out.println("Here3");
      }
      
      
      
      
      //BigInt temp = new BigInt(first.stringVal);
      //System.out.println("Divisor: " + temp.stringVal);
      while(first.isGreaterThan(second)){
        
        // System.out.println(first+ "\t"+second+"\t"+res+"\t"+counter);
        
        
        // System.out.println(temp);
        first = first.subtract(second);
        
        //System.out.println(res.stringVal);
        //  System.out.println("res: "+ res + " " + one);
        res = res.add(counter);
        counter = counter.add(counter);
        second = second.add(second);
        //System.out.println(res);
        //System.out.println("Temp: " + temp.stringVal);
        
      }
      
      if(first.isGreaterThan(s) || first.isEqualTo(s)){
        second = new BigInt(s.stringVal);
        if(second.negative){
          second.negative = false;
          second.stringVal = second.stringVal.substring(1);
        }
      }
      
      while(first.isGreaterThan(ZERO) && first.isGreaterThan(second) || first.isEqualTo(second)){
        first = first.subtract(second);
        res = res.add(ONE);
      }
      
      if( first.isLessThan(second) && first.isGreaterThan(ZERO)){
        res.remainder = first.stringVal;
      }
      //System.out.println(first+ "\t"+second+"\t"+res+"\t"+counter);
      
      if(neg){
        res.negative = true;
        res.stringVal = "-" + res.stringVal;
      }
      return res;
    }
    
    
    public BigInt greatestCommonDivisor(BigInt s){
      BigInt ONE = new BigInt("1");
      BigInt ZERO = new BigInt("0");
      BigInt first = new BigInt(stringVal);
      BigInt second = new BigInt(s.stringVal);
      BigInt temp;
      BigInt quotient;
      BigInt remainder = new BigInt("-1");
      
      if(first.isGreaterThan(second)){
        first = new BigInt(stringVal);
        second = new BigInt(s.stringVal);
      } else {
        first = new BigInt(s.stringVal);
        second = new BigInt(stringVal);
      }
      //System.out.println("hello1" + remainder.stringVal);
      while(!remainder.isEqualTo(ZERO)){
        quotient = first.divideBy(second);
        //System.out.println("hello1" + remainder.stringVal);
        remainder = new BigInt(quotient.getRemainder());
        // System.out.println(quotient);
        // System.out.println(quotient.getRemainder());
        
        if(remainder.isEqualTo(ZERO)){
          return second;
        }
        //System.out.println("hello" + remainder.stringVal);
        first = second;
        second = remainder;
      }
      return second;
    }
}
