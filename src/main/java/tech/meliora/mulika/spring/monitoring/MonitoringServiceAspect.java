package tech.meliora.mulika.spring.monitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MonitoringServiceAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {

        log.info("invoked");
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("servicePointcut()")
    public Object reportService(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        boolean successful = true;
        int queueSize = 0;

        log.info("service-invoked");

        try {
            Object result = joinPoint.proceed();


            for(Object arg: joinPoint.getArgs()){
                log.info("service-arg: "+ arg);
            }

//            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            log.info("service-finished");

            return result;

        } catch (Exception e) {
            successful = false;
            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            throw e;
        }
    }

}
