package com.ei.math.function.text;

import com.ei.math.Step;
import com.ei.math.function.Function;

public class FunctionFormatter {
    
    public static Step joinStepForFunction(Function function,Step step){
        String text = FunctionText.joinFunction(step,function);
        String html = FunctionHtml.joinFunction(function,step);
        return Step.builder().text(text).html(html).build();
    }
    
    public static Step joinFunctionGroup(String text, String html,int cod) {
        String exp = "<div class=\"fraction-group-function\">"+html+"</div>";
        if(!html.contains("denominator"))
            exp = "<div class=\"fraction-group-function signal-simple\">"+html+"</div>";
        return new Step(cod, text, exp,"");
    }
    
    public static Step joinFunctionGroup(String text, String html) {
        return  joinFunctionGroup(text, html, 0);
    }
        
}
