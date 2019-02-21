package model.Interceptors;

import model.logic.Player;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PlayerInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("PlayerInterceptor - Logging BEFORE calling method :"+ context.getMethod().getName());

        Object result = context.proceed();

        System.out.println("PlayerInterceptor - Logging AFTER calling method :"+context.getMethod().getName() );

        return result;
    }
}
