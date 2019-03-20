package model.Services;
import model.Interceptors.SoccergameInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.logging.Logger;

@Local
@Stateless
@Interceptors(SoccergameInterceptor.class)
public abstract class BaseService{
    protected Logger logger = Logger.getLogger(this.getClass().getName());

}
