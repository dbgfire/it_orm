package fr.epsi.dao;

import fr.epsi.model.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setMail("aa@dff.fr");
        user.setPseudo("blabla");
        user.setPassword("lala");


        System.out.println(new UserDao().save(user));
    }

    @Test
    public void getUser() {
        User u = new UserDao().get(2);
        System.out.println(u.getPseudo()+" "+u.getMail());
    }

    @Test
    public void deleteUser(){
        User u = new UserDao().get(2);
        new UserDao().delete(u);
    }

}
