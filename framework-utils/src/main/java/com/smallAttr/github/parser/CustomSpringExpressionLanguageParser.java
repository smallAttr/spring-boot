package com.smallAttr.github.parser;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenGang
 * @Date: 2018/5/18 下午4:36
 * @Description:
 */
public class CustomSpringExpressionLanguageParser {

    public static <T> T getDynamicValue(String[] parameterNames, Object[] args, String key, Class<T> keyClazz) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, keyClazz);
    }


    public static <T> List<T> getListDynamicValue(String[] parameterNames, Object[] args, List<String> keys, Class<T> keyClazz) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return keys.stream().map(key -> parser.parseExpression(key).getValue(context, keyClazz)).collect(Collectors.toList());
    }
}
