package SpringBootDataJPA.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class Logging {

    private final LoggerMap executionMap;

    @Pointcut("@annotation(SpringBootDataJPA.aop.annotation.MET)")
    public void loggableMethod() {
    }

    @Pointcut("@annotation(SpringBootDataJPA.aop.annotation.MyDeprecated)")
    public void deprecated() {
    }

    @Pointcut("execution(* SpringBootDataJPA.services.*.*(..))")
    public void infoAboutService() {}

    @Pointcut("execution(* org.springframework.shell.standard.commands.Quit.quit()))")
    public void allMethodTimeAfterExecution() {}

    @After("allMethodTimeAfterExecution()")
    public void sortedMethodList() {
        executionMap.get();
    }


    @Around("infoAboutService())")
    public Object serviceLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        try {
            return (joinPoint.proceed());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            log.info("ВЫЗОВ МЕТОДА: " + methodName + " из класса: " + className);
        }
    }


    @Around("loggableMethod()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            String res = "Время выполнения: " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms";
            log.info(res);
            executionMap.add("Метод: " + methodName + "." + " Время выполнения(мс)", stopWatch.getTotalTimeMillis());
        }
    }

    @Around("deprecated()")
    public Object logDeprecated(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            log.warn("ВЫЗОВ DEPRECATED-МЕТОДА: " + className + "." +methodName);
        }
    }
}

