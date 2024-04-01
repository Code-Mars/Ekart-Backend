package com.ekart.payment.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ekart.payment.exception.EkartException;



@Component
@Aspect
public class LoggingAspect {
    public static final Log LOGGER =LogFactory.getLog(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.ekart.payment.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(EkartException exception) {
        LOGGER.error(exception.getMessage(), exception);
    }
}