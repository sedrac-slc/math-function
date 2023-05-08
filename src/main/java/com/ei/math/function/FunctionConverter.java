package com.ei.math.function;

import com.ei.math.fraction.FractionConverter;
import com.ei.math.function.exception.FunctionRegexException;

public class FunctionConverter {
    
    public static Function parse(String expression){
        if(expression.trim().isEmpty() || !expression.matches(FunctionRegex.FUNCTION))
            throw new FunctionRegexException();
 
        if(expression.matches(FunctionRegex.FRACTION))
            return Function.builder().coefficient(FractionConverter.parse(expression)).build();
        
        if(expression.matches(FunctionRegex.COEF_AND_INC)){
            String[] split = expression.split("(?<=\\d)(?=\\D)");
            return Function.builder()
                    .coefficient(FractionConverter.parse(split[0]))
                    .ingonita(split[1])
                    .build();
        }
        
        if(expression.matches(FunctionRegex.INC_AND_EXP)){
            String[] split = expression.split("(?<=[a-zA-Z])\\^");
            return Function.builder()
                    .ingonita(split[0])
                    .expoent(FractionConverter.parse(split[1]))
                    .build();
        }        
        
        if(expression.matches(FunctionRegex.COF_AND_EXP)){
            String[] split = expression.split("(?<=\\d)\\^");
            return Function.builder()
                    .coefficient(FractionConverter.parse(split[0]))
                    .expoent(FractionConverter.parse(split[1]))
                    .build();
        }       
        String rgx = "(?<=[a-zA-Z^])|(?=[a-zA-Z^])";
        String[] split = expression.split(rgx);
        return Function.builder()
                    .coefficient(FractionConverter.parse(split[0]))
                    .ingonita(split[1])
                    .expoent(FractionConverter.parse(split[3]))
                    .build();
    } 
    
}
