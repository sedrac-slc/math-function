package com.ei.math.function.text;

import com.ei.math.Step;
import com.ei.math.function.Function;

public class FunctionHtml {

    private static String functionFormat(Function function){
        boolean functionTwo = function.getExpoent().getDenominator() == 2;
        return "<div class=\"function-group\">" +
                    ( functionTwo ? "" : "<sup class=\"expoent\">"+function.getExpoent().getDenominator()+"</sup>" )+
                    "<div class=\"signal-root\">"+function.getIngonita()+"</div>" +
                    "<div class=\"expoent\">"+function.getExpoent()+"</div>" +
                "</div>";
    }
    
    private static String fractionGroupOne(Function function , String html){
        
        return "<div class='fraction-arithmetic-group'>" +
                    "<div class='fraction-arithmetic-group-item'>"
                        +html+
                    "</div>"
                    + functionFormat(function)+
                "</div>";
    }
    
    private static String verifyType(Function function, String html){
      return fractionGroupOne(function, html); 
    }
    
    public static String joinFunction(Function function , Step step) {
        return verifyType(function, step.getHtml());
    }
    
}
