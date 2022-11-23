package tech.meliora.mulika.spring.monitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import tech.meliora.mulika.spring.web.rest.dto.Request;

@Aspect
@Configuration
public class MonitoringControllerAspect {
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

        //before
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        boolean successful = true;
        int queueSize = 0;

        log.info("invoked");

        try {
            Object result = joinPoint.proceed(); //execution happens...

            //after

            for (Object arg : joinPoint.getArgs()) {
                log.info("arg: " + arg);

                if (arg instanceof Request) {

                    String telco = ((Request) arg).getTelco();
                    String channel = ((Request) arg).getChannel();

                    if (telco != null) {
                        //report telco stats
                        MulikaConnector.reportClient(methodName + "." + telco, successful,
                                (int) (System.currentTimeMillis() - startTime), queueSize);
                    }

                    if (channel != null) {
                        //repoort the channel
                        MulikaConnector.reportClient(methodName + "." + channel, successful,
                                (int) (System.currentTimeMillis() - startTime), queueSize);
                    }
                }
            }

            //total service requests
            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            log.info("finished");

            return result;

        } catch (Exception e) {
            successful = false;

            for (Object arg : joinPoint.getArgs()) {
                log.info("arg: " + arg);

                if (arg instanceof Request) {

                    String telco = ((Request) arg).getTelco();
                    String channel = ((Request) arg).getChannel();

                    if (telco != null) {
                        //report telco stats
                        MulikaConnector.reportClient(methodName + "." + telco, successful,
                                (int) (System.currentTimeMillis() - startTime), queueSize);
                    }

                    if (channel != null) {
                        //repoort the channel
                        MulikaConnector.reportClient(methodName + "." + channel, successful,
                                (int) (System.currentTimeMillis() - startTime), queueSize);
                    }
                }
            }

            MulikaConnector.report(className, methodName, successful, (int) (System.currentTimeMillis() - startTime), queueSize);

            throw e;
        }
    }

}
