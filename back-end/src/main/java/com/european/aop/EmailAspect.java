package com.european.aop;

import com.european.model.User;
import com.european.services.EmailService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    private EmailService emailService;

    @Around("@annotation(SendEmail)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        User user = (User) joinPoint.getArgs()[0];
        long start = System.currentTimeMillis();
        System.out.println("Sending email to " + user.getEmail() + ". . .");

        Object proceed = joinPoint.proceed();

        emailService.sendMessage(user.getEmail(), "Your id", "Your id is: " + user.getId());

        long executionTime = System.currentTimeMillis() - start;

        System.out.println("Email sent in: " + executionTime/1000 + "s");

        return proceed;
    }
}
