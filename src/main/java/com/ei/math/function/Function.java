package com.ei.math.function;

import com.ei.math.fraction.Fraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Function {
    @Builder.Default
    private Fraction coefficient = Fraction.of();
    @Builder.Default
    private String ingonita = "";
    @Builder.Default
    private Fraction expoent = Fraction.of();
    
    public Function of(Fraction coefficient, String ingonita, Fraction expooent){
        return  Function.builder().coefficient(coefficient).ingonita(ingonita).expoent(expoent).build();
    }
    
    public Function of(Fraction coefficient, String ingonita){
        return  of(coefficient, ingonita, Fraction.of(1));
    }
    
    public Function of(String ingonita, Fraction expooent){
        return  of(Fraction.of(1), ingonita, expooent);
    }

    public Function of(String ingonita){
        return  of(Fraction.of(1), ingonita, Fraction.of(1));
    }
    
    public Function root() {
        return new Function(Fraction.of(), ingonita, expoent);
    }
    
    public boolean equalsFunction(Function function){
        if(!expoent.equals(function.getExpoent())) return false;
        if(!ingonita.equals(function.getIngonita())) return false;
        return coefficient.equals(function.getCoefficient());
    }
    
    public String simplyText(){
        return String.format("%s^%s", ingonita, expoent.text());
    }
    
}
