package model.Repository;


import model.Interceptors.SoccergameInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Local
@Stateless
@Interceptors(SoccergameInterceptor.class)
public abstract class BaseRepo {

    @PersistenceContext(unitName = "myPU")
    protected EntityManager em;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

}
