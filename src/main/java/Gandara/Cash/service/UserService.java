package Gandara.Cash.service;


import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements IUserService{
    @Autowired
    public UserDao user;


    }

