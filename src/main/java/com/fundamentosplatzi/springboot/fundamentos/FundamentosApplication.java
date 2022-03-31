package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.componet.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;


    public FundamentosApplication(@Qualifier("componenteTwoImplement") ComponentDependency componentDependency,
                                  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
                                  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;

    }

    public static void main(String[] args) {

        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(" datas :  " + myBeanWithProperties.function());
        System.out.println(" credentials :  " + userPojo.getMail() + " " + userPojo.getPassword());
        LOGGER.error("Esto en un error ni el hp del aplicativo ");

        try {
            int value = 111 / 0;
            LOGGER.debug("El valor es ::  " + value);
        } catch (Exception e) {
            LOGGER.error("Esto en un error por dividir por ZERO " + e.getMessage());
        }


    }
}
