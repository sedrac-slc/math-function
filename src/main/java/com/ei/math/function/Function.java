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
    private String ingonita;
    @Builder.Default
    private Fraction expoent = Fraction.of(1);
    
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
    
   public String simplyText(){
        return String.format("%s%s^%s",coefficient.text(), ingonita ,expoent.text());
    }
   
    public Function root() {
        return new Function(Fraction.of(),ingonita, expoent);
    }
       
   public boolean equalsFunction(Function function){
       return ingonita.equals(function.getIngonita()) && expoent.equals(function.getExpoent());
   }
       
      
    
    
}
