package org.mengyun.tcctransaction.spring;

import org.aspectj.lang.annotation.Aspect;
import org.mengyun.tcctransaction.TransactionManager;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.interceptor.CompensableTransactionAspect;
import org.mengyun.tcctransaction.interceptor.CompensableTransactionInterceptor;
import org.mengyun.tcctransaction.support.TransactionConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

/**
 * Created by changmingxie on 10/30/15.
 */
@Aspect
public class ConfigurableTransactionAspect extends CompensableTransactionAspect implements Ordered {

    private TransactionConfigurator transactionConfigurator;

    public void init() {

        TransactionManager transactionManager = transactionConfigurator.getTransactionManager();

        CompensableTransactionInterceptor compensableTransactionInterceptor = new CompensableTransactionInterceptor();
        compensableTransactionInterceptor.setTransactionManager(transactionManager);

        this.setCompensableTransactionInterceptor(compensableTransactionInterceptor);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public void setTransactionConfigurator(TransactionConfigurator transactionConfigurator) {
        this.transactionConfigurator = transactionConfigurator;
    }
}
