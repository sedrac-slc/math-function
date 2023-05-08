package com.ei.math.function;

import com.ei.math.fraction.FractionRegex;

public final class FunctionRegex {
    public static final String SEPARATOR = "[a-zA-z]";
    public static final String FRACTION = FractionRegex.FRACTION;
    public static final String EXPOENT = "[\\+|\\-]?\\d+";
    public static final String EXPOENT_SIGNAL  = "[\\^]";
    
    
    public static final String COEF_AND_INC = FRACTION+"[a-zA-z]";
    public static final String INC_AND_EXP = "[a-zA-z]\\^"+FRACTION;
    public static final String COF_AND_EXP = FRACTION+"\\^"+FRACTION;
    
    public static final String FUNCTION = "("+FRACTION+")?("+SEPARATOR+")?("+EXPOENT_SIGNAL+FRACTION+")?";
    
    public static final String SEPARATOR_SIG_ALL = SEPARATOR.substring(0, SEPARATOR.length()-1)
            +"|"+EXPOENT_SIGNAL.substring(1);
    
   
    public static final String SUM_SUB = "[\\+|\\-]?"+FUNCTION+"([\\+|\\-]"+FUNCTION+")+";
    
}
