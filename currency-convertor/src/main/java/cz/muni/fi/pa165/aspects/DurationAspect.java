package cz.muni.fi.pa165.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import javax.inject.Named;

@Named
@Aspect
public class DurationAspect {

    @Around("execution(public * *(..))")
    public Object printMethodDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object returnValue = joinPoint.proceed();
        stopWatch.stop();

        System.out.println("Execution duration of " + joinPoint.getSignature().getName() + " method was " + stopWatch.getLastTaskTimeNanos() / 1_000_000.0 + "ms");
        return returnValue;
    }

}

