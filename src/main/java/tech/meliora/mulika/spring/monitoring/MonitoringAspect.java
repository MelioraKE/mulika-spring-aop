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
public class MonitoringAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void webRestPointcut() {

        log.info("invoked");
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("webRestPointcut()")
    public Object reportEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        boolean successful = true;
        int queueSize = 0;

        log.info("invoked");

        try {
            Object result = joinPoint.proceed();

            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            log.info("finished");

            return result;

        } catch (Exception e) {
            successful = false;
            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            throw e;
        }
    }

}