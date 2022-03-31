package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("ingreso al metodo printWithDependency");
        int numero = 1;
        LOGGER.debug("numero enviado parametrod dependencia es:  " + numero);
        System.out.println("hola >> " + myOperation.sum(numero));
        System.out.println("hola mundo desde la implementacion de un BEANN con dependencia");
    }
}
