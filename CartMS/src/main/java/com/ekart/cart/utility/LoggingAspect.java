package com.ekart.cart.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ekart.cart.exception.EkartException;




@Component
@Aspect
public class LoggingAspect {
    public static final Log LOGGER =LogFactory.getLog(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.ekart.cart.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(EkartException exception) {
        LOGGER.error(exception.getMessage(), exception);
    }
}