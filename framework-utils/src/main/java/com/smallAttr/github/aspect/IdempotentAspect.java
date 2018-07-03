package com.smallAttr.github.aspect;

import com.smallAttr.github.annotation.Idempotent;
import com.smallAttr.github.bean.IdempotentMarker;
import com.smallAttr.github.parser.CustomSpringExpressionLanguageParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: chenGang
 * @Date: 2018/7/3 下午2:05
 * @Description:
 */
@Aspect
@Component
public class IdempotentAspect {

    private static final Logger logger = LoggerFactory.getLogger(IdempotentAspect.class);

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;

    // 注解切点，也就是上面那个注解的路径
    @Pointcut("@annotation(com.smallAttr.github.annotation.Idempotent)")
    public void aspect() {
    }

    /**
     * 环绕
     *
     * @param joinPoint 切点
     */
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object o = null;
        try {
            boolean success = beforeHandle(joinPoint);
            if (success) {
                o = joinPoint.proceed();
                logger.info("exec current method success {}", o);
            }
        } catch (Exception e) {
            logger.error("before handle exception", e);
            e.printStackTrace();
        } catch (Throwable throwable) {
            logger.error("exec aspect error", throwable);
            throwable.printStackTrace();
        }
        return o;
    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private boolean beforeHandle(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] arguments = joinPoint.getArgs();
        // 反射加载类
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    Idempotent annotation = method.getAnnotation(Idempotent.class);
                    // 动态获取方法参数的值，作为key的一部分
                    String dynamicParametersStr = CustomSpringExpressionLanguageParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), annotation.columnListStr(), String.class);
                    String key = annotation.module() + "-" + annotation.operationType() + "-" + dynamicParametersStr + "-idempotent";
                    logger.info("current aspect key {}", key);
                    Boolean exists = stringRedisTemplate.hasKey(key);
                    if (exists) {
                        logger.info("bean has exist this key {}", key);
                        return false;
                    }
                    IdempotentMarker idempotentMarker = new IdempotentMarker(stringRedisTemplate, key, annotation.expireMillis());
                    return idempotentMarker.acquire();
                }
            }
        }
        return false;
    }
}
