package com.example.easynoteaca.util.user;

import com.example.easynoteaca.pojo.User;

import java.util.List;

public abstract class UserStorage {

    public abstract void checkAndGetUser(String userName, String password, UserFoundListener actionListener);

    public abstract void findUserById(long id, UserFoundListener actionListener);

    public abstract void findUserByUsername(String username, UserFoundListener actionListener);

    public abstract void registerUser(User user, UserFoundListener actionListener);

    protected abstract List<User> getUsers();




    protected void notifyUserFound(User user, UserFoundListener actionListener){
        if(actionListener != null){
            actionListener.onUserFound(user);
        }
    }

    public interface UserFoundListener {
        void onUserFound(User user);
    }
}
