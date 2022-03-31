package com.fundamentosplatzi.springboot.fundamentos.componet;

import org.springframework.stereotype.Component;

@Component
public class ComponenteTwoImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("hola mundo desde componente 2 dos");
    }
}
