package com.ei.math.function.text;

import com.ei.math.Step;
import com.ei.math.function.Function;

public class FunctionText {

    public static String joinFunction(Step step, Function function) {
     return "("+step.getText()+")"+function.simplyText();
    }
    
}
