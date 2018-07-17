package fr.epsi.dao;


import fr.epsi.model.User;
import fr.epsi.model.message;
import org.junit.Test;


public class messageDaoTest {
    @Test
    public void insertMessage() {

        message m = new message();
        m.setMsg("coucou");
        User u=new UserDao().get(50);
        m.setUser( u);
        System.out.println( new messageDao().save(m));
    }

    @Test
    public void getMessage(){
        message m=new messageDao().get(59);
        System.out.println(m.getMsg());
    }

    @Test
    public void getUser(){
        message m= new messageDao().get(59);
        System.out.println(m.getUser().getPseudo());
    }
    @Test
    public void deleteMessage(){
        message m=new messageDao().get(59);
        System.out.println(m.getMsg());
        new messageDao().delete(m);
    }
}
