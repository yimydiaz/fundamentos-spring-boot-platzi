package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.componet.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.Post;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;


    public FundamentosApplication(@Qualifier("componenteTwoImplement") ComponentDependency componentDependency,
                                  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
                                  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
                                  UserRepository userRepository) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;

    }

    public static void main(String[] args) {

        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUser();

        /*
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
        }*/
    }

    private void getInformationJpqlFromUser() {
        LOGGER.info("Usuario con metodo findByUserEmail " +
                userRepository.findByUserEmail("juliet@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se enconto Usuario")));

        userRepository.findAndSort("user", Sort.by("id").descending())
                .stream().forEach(user -> LOGGER.info("User whith method findAndSort " + user));
    }

    private void saveUsersInDataBase() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 03, 20), new ArrayList<Post>());
        User user2 = new User("Juliet", "juliet@domain.com", LocalDate.of(2021, 05, 21), new ArrayList<Post>());
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 07, 22), new ArrayList<Post>());
        User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021, 02, 23), new ArrayList<Post>());
        User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 03, 24), new ArrayList<Post>());
        User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 10, 25), new ArrayList<Post>());
        User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 12, 26), new ArrayList<Post>());
        User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 1, 27), new ArrayList<Post>());
        User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 2, 28), new ArrayList<Post>());
        User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 3, 29), new ArrayList<Post>());
        User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 4, 30), new ArrayList<Post>());
        User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 5, 31), new ArrayList<Post>());

        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);

        userList.stream().forEach(userRepository::save);
    }

    private void ejemplosAnteriores() {
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
