package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    // @Transactional // pertmite hacer rollbak de todad las transaciones en caso de algu error
    public void saveTransactional(List<User> userList) {
        userList.stream()
                .peek(user -> LOG.info("Usuario insertado  " + user))//peek es para poder mostrar print lo que se esta registrando, hacer seguimiento de que pasa por dentro de la funcion.
                .forEach(userRepository::save);//java 8, uso de los metodos apartir de referencia,pormedio del save va a obteer una entidad
        //  .forEach(user -> userRepository.save(user));//tambien es valido
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User updateUser) {
        return userRepository.findById(updateUser.getId()).map(
                user -> {
                    user.setBirthDate(updateUser.getBirthDate());
                    user.setEmail(updateUser.getEmail());
                    user.setName(updateUser.getName());
                    return userRepository.save(updateUser);
                }).get();
    }
}
