package com.example.springBootTestDemo.service;

import com.example.springBootTestDemo.dto.UserDTO;
import com.example.springBootTestDemo.model.User;
import com.example.springBootTestDemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long userId){
        try{
            return modelMapper.map(userRepository.findById(userId).orElseThrow(EntityExistsException::new), UserDTO.class);
        }catch(Exception e){
            LOGGER.error("[ UserServiceImpl ][ getUserById(Long userId) ] throws : " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<UserDTO> listAllUsers(){
        try{
            return userRepository.findAll().stream().map(element -> modelMapper.map(element, UserDTO.class))
                    .collect(Collectors.toList());
        }catch(Exception e){
            LOGGER.error("[ UserServiceImpl ][ listAllUsers() ] throws : " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void saveUser(UserDTO userDTO){
        try{
            userRepository.save(modelMapper.map(userDTO, User.class));
        }catch(Exception e){
            LOGGER.error("[ UserServiceImpl ][ saveUser(UserDTO userDTO) ] throws : " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
