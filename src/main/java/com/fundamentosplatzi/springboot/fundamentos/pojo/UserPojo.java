package com.fundamentosplatzi.springboot.fundamentos.pojo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "user")// prefijo que representa el prefijo "user" en el archivo application.properties (user.mail=test@gmail.com)
public class UserPojo {

    private String mail;
    private String password;
    private int age;

    public UserPojo(String mail, String password, int age) {
        this.mail = mail;
        this.password = password;
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
