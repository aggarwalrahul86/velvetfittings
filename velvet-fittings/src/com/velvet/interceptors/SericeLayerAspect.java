package com.velvet.interceptors;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.velvet.controllers.PartsManagementController;

@Aspect
@Component
public class SericeLayerAspect {
	
	/*
	 * Logger file to initialize logger
	 */
	private Logger logger = LoggerFactory
			.getLogger(PartsManagementController.class);
	
    @Pointcut("execution(* *..*Service.*(..))")
    public void serviceIntercept() {
    }
    
    @Before("serviceIntercept()")
    public void logbefore(){
    	logger.info("This is before Service Call");
    }
    
    @After("serviceIntercept()")
    public void logAfter(){
    	logger.info("This is after Service Call");
    }
    @AfterThrowing("serviceIntercept()")
    public void exceptionHandling(){
    	
    	logger.error("Exception encountered in aspect layer");
    }

}
