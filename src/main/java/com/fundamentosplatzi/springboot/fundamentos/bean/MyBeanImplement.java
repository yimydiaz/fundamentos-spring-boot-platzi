package com.fundamentosplatzi.springboot.fundamentos.bean;

//import org.springframework.stereotype.Component;

//@Component
public class MyBeanImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("hola mundo desde implementacion propia del BEANN");
    }
}
