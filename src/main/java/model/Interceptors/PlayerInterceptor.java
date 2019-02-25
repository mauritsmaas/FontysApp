package model.Interceptors;

import model.logic.Player;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PlayerInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("PlayerInterceptor - Logging BEFORE calling method : "+ context.getMethod().getName());

        Object[] result = context.getParameters();
        Player p = (Player) result[0];
        if(p.getName()!= null && p.getRating()!= null && p.getPrice() != null) {
            System.out.println(p.getName() + " - "+ p.getRating() +" ("+p.getPrice()+ ")");
            System.out.println("PlayerInterceptor - Logging AFTER succesfully calling method :"+ context.getMethod().getName() );
            return context.proceed();
        }
        System.out.println("PlayerInterceptor - Logging AFTER canceled calling method :"+ context.getMethod().getName() );

        return null;
    }
}
