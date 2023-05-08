package com.ei.math.function.operation;

import com.ei.math.MathResult;
import com.ei.math.Step;
import com.ei.math.arithmetic.operator.ArithmeticSumOrSub;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.function.Function;
import com.ei.math.function.FunctionConverter;
import com.ei.math.function.FunctionRegex;
import com.ei.math.function.text.FunctionFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ResultMap {

    private Step step;
    private Integer index;
    private boolean integers; 
    
    public ResultMap(Step step, Integer index){
        this.step = step;
        this.index= index;
        this.integers = false;
    }
    
}

@Data
public class FunctionSumOrSub {

    private ArithmeticSumOrSub arithmeticSumOrSub;
    private ArithmeticParams arithmeticParams;

    {
        arithmeticParams = new ArithmeticParams();
        arithmeticSumOrSub = new ArithmeticSumOrSub();
    }

    public MathResult resolve(Function functionOne, Function functionTwo) {
        if (functionOne.equalsFunction(functionTwo)) {
            return arithmeticSumOrSub.solve(functionOne.getCoefficient(), functionTwo.getCoefficient());
        }
        return null;
    }

    public MathResult resolve(List<Function> functions) {
        arithmeticSumOrSub.setArithmeticParams(arithmeticParams);
        
        long start = System.currentTimeMillis();

        Map<Function, List<Function>> maps = functions.stream().collect(Collectors.groupingBy(Function::root));

        Map<Function, MathResult> results = new HashMap<>();

        maps.entrySet().forEach(item -> {
            MathResult solve = arithmeticSumOrSub.solve(
                item.getValue().stream().map(Function::getCoefficient).collect(Collectors.toList())
            );
            results.put(item.getKey(), solve);
        });

        List<ResultMap> resultMaps = new ArrayList<>();

        final int tam = results.entrySet().stream().map(mapR -> mapR.getValue().getSteps().size())
                .reduce(0, (x, y) -> x > y ? x : y);

        results.entrySet().forEach((var item) -> {
            List<Step> steps = item.getValue().getSteps();
            int size = steps.size();
            boolean integers = steps.stream().allMatch(step -> !step.getHtml().contains("denominator"));
            for (int i = 0; i < tam; i++) {
                if (i < size) {
                    Step step = FunctionFormatter.joinStepForFunction(item.getKey(), steps.get(i));
                    resultMaps.add(new ResultMap(step, i, integers));
                }else{
                    Step step = FunctionFormatter.joinStepForFunction(item.getKey(), steps.get(size-1));
                    resultMaps.add(new ResultMap(step, i, integers));
                }
            }
        });
        
        Map<Integer, List<ResultMap>> resultsGroupy = resultMaps.stream().collect(
                Collectors.groupingBy(ResultMap::getIndex)
        );

        List<Step> steps = new ArrayList<>();

        resultsGroupy.entrySet().forEach((var item) -> {
            List<Step> collect = item.getValue().stream().map(ResultMap::getStep).collect(Collectors.toList());
            String text = collect.stream().map(Step::getText).collect(Collectors.joining("+"));
            String html = collect.stream().map(Step::getHtml).collect(Collectors.joining("<div>+</div>"));
            steps.add(FunctionFormatter.joinFunctionGroup(text, html));
        });
        
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("grouping")
                .status(true)
                .build();
    }

    public MathResult resolve(Function... functions) {
        return resolve(List.of(functions));
    }

    public MathResult resolve(String expression) {
        if (!expression.matches(FunctionRegex.SUM_SUB)) {
            return MathResult.builder().build();
        }
        List<Function> list = Arrays.stream(expression.split("[\\+|\\-]"))
                .map(FunctionConverter::parse).toList();
        return resolve(list);
    }
    
}
