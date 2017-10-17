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
    
    
    public BigInt add(BigInt s){
        BigInt ONE = new BigInt("1");
        BigInt ZERO = new BigInt("0");
        
        BigInt first = new BigInt(stringVal);
        BigInt second = new BigInt(s.stringVal);
        ArrayList<String> res = new ArrayList<String>();
        Integer carry = 0;
        int indexOne = first.stringVal.length() - 1;
        int indexTwo = second.stringVal.length() - 1;
        
        if(first.isEqualTo(ZERO) && second.isEqualTo(ZERO)){
            return ZERO;
        }
        
        
        
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
        ArrayList<Integer> newNumber = new ArrayList<Integer>();
        
        // System.out.println("first: "+first);
        // System.out.println("second: "+second);
        while(si >= 0){
            //System.out.println(newNumber);
            int newNum = first.number.get(fi) + second.number.get(si);
            newNumber.add(0,newNum);
            
            si--;
            fi--;
        }
        while(fi > si){
            newNumber.add(0,first.number.get(fi));
            fi--;
        }
        
       // System.out.println(newNumber);
        
        
        for(int i = newNumber.size()-1;i>=0;i--){
           // System.out.println(newNumber);
            
            int val = newNumber.get(i).intValue();
            if(val > 9){
                val -= 10;
                newNumber.set(i,val);
                if(i == 0){
                    newNumber.add(0,1);
                } else {
                    newNumber.set(i-1, newNumber.get(i-1)+1);
                }
                i++;
            }
        }
        

        
        
        
        String newString = "";
        
        for(Integer num : newNumber){
            newString = newString + num.toString();
        }
        BigInt result = new BigInt(newString);
        //System.out.println(result);
        
        
       // System.out.println(result.number);
        return result;
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
    
    
    public BigInt multiplyBy(BigInt s) {
      BigInt first = new BigInt(stringVal);
      BigInt second = new BigInt(s.stringVal);
      BigInt subtractionInt;
      String subtraction = "";
      boolean negative = false;
      int smallest = 0;
      
      
      if (first.negative && !second.negative) {
        negative = true;
      } else if (!first.negative && second.negative) {
        negative = true;
      }
      
      BigInt result = new BigInt("0");
      
      if (first.stringVal.charAt(0) == '-') {
        first = new BigInt(first.stringVal.substring(1));
        //System.out.println(first);
      }
      
      if (second.stringVal.charAt(0) == '-') {
        second = new BigInt(second.stringVal.substring(1));
        //System.out.println(second);
      }
      
      ArrayList<Integer> one = first.number;
      ArrayList<Integer> two = second.number;
//      System.out.println(one);
//      System.out.println(one);
      int numlength = 0;
      
      if (one.size()<two.size()) {
        //System.out.println("here");
        numlength = one.size();
        one = two;
        two = first.number;
        first = second;
        
      } else {
        
        //System.out.println("here1");
        numlength = two.size();
      }
      
      int pad = 0;
      
      
      for (int i=numlength; i>0; i--) {
        //System.out.println(pad);
        int times = two.get(i - 1);
        BigInt num = new BigInt("0");
        String zero = "";
        for (int j=0; j<pad; j++) {
          //System.out.println("here");
          zero += "0";
        }
        //System.out.println("here1");
        //Sysem.out.println(zero);
        for (int j=0; j<times; j++) {
          num = num.add(first);
          
        }
        //System.out.println("out");
        //System.out.println(num);
        String newString = num.stringVal + zero;
        
        int check = 0;

        for (int j = 0; j < newString.length(); j++) {
          if (newString.charAt(j) == '0') {
            check++;
          }
        }
        
        boolean through = false;
        if (check == newString.length()) {
          newString = "1" + newString;
          through = true;

          //System.out.println(second.stringVal.length() + " dsfgsdfg");
          if (stringVal.length() < second.stringVal.length() && i == numlength) {
            for (int k = stringVal.length()-1; k < stringVal.length(); k--) {
              if (stringVal.charAt(k) != '0') break;
              smallest++;
            }
          }
          else if (stringVal.length() > second.stringVal.length() && i == numlength) {
            for (int k = second.stringVal.length()-1; k < second.stringVal.length(); k--) {
              if (second.stringVal.charAt(k) != '0') break;
              smallest++;
            }
          }
          else if (stringVal.length() == second.stringVal.length() && i == numlength) {
            for (int k = stringVal.length()-1; k < stringVal.length(); k--) {
              if (stringVal.charAt(k) != '0') break;
              smallest++;
            } 
          }
        }
        
    
        //System.out.println(newString);
        num = new BigInt(newString);
        //System.out.println("i: " + i);
        result = result.add(num);
        pad++;
        
        if (check != 0 && i == 1) {
          //System.out.println(smallest);
          for (int j = 0; j < smallest; j++) {
            //System.out.println("inin");
            subtraction += "1";
          }
        }
      }
      //System.out.println(subtraction);
      subtraction += "0";
      subtractionInt = new BigInt(subtraction);
      result = result.subtract(subtractionInt);
      
      char c = result.stringVal.charAt(result.stringVal.length() - 1);
      
      int a = 0;
      int b = 0;
      ArrayList<Integer> tempList = new ArrayList<Integer>();
      tempList.add(0);
      String temp = "";
      if (stringVal.length() < second.stringVal.length()) temp = stringVal;
      if (stringVal.length() > second.stringVal.length()) temp = second.stringVal;
      if (stringVal.length() == second.stringVal.length()) {
        for (int j = stringVal.length()-1; j > 0; j--) {
          if (stringVal.charAt(j) == '0') {
            a = j;
            break;
          }
        }
        for (int j = second.stringVal.length()-1; j > 0; j--) {
          if (second.stringVal.charAt(j) == '0') {
            b = j;
            break;
          }
        }
        
        
        if (a > b) {
          temp = stringVal;
        } else {
          temp = second.stringVal;
        }
      }

      System.out.println(temp);
      
      int remain = 0;
      if (c != '0') {
 
        for (int j = temp.length()-1; j > 0; j--) {
          if (temp.charAt(j) == '0') tempList.add(j);
        }
        

        if (tempList.size() > 1) {
          for (int j = tempList.size() -1; j > 0; j--) {
            System.out.println(j);
            remain += Math.pow(10.0, (double)tempList.get(j).intValue() + 1.0);
          }
        }
        
      }
      
      System.out.println(tempList);
      
      BigInt sub1 = new BigInt(Integer.toString(remain));
      
      result = result.subtract(sub1);
      
      if (negative) {
        result = new BigInt("-" + result.stringVal);
      }
      
      return result;
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
