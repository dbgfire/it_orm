package fr.epsi.dao;

import fr.epsi.model.Channel;
import fr.epsi.model.User;
import fr.epsi.model.message;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ChannelDaoTest {
    @Test
    public void insertChannel(){
        Channel chal=new Channel();
        chal.setStatus("public");
        chal.setName("B3");
        User u=new UserDao().get(2);

        message m1 = new message();
        m1.setMsg("coucou");
        m1.setUser( u);
        m1.setId_channel(chal.getId());

        message m2 = new message();
        m2.setMsg("avion");
        m2.setUser( u);
        m2.setId_channel(chal.getId());

        chal.setMessages(Arrays.asList(m1, m2));
        long id=new ChannelDao().save(chal);
        Channel c = new ChannelDao().get(id);

        Assert.assertEquals(2, c.getMessages().size());
    }
    @Test
    public void dropUser(){
        Channel chal=new Channel();
        chal.setStatus("public");
        chal.setName("B3");
        User u=new UserDao().get(50);

        message m1 = new message();
        m1.setMsg("coucou");
        m1.setUser( u);
        m1.setId_channel(chal.getId());

        message m2 = new message();
        m2.setMsg("avion");
        m2.setUser( u);
        m2.setId_channel(chal.getId());

        chal.setMessages(Arrays.asList(m1, m2));
        long id=new ChannelDao().save(chal);
        Channel c = new ChannelDao().get(id);
        new UserDao().delete(u);

        Assert.assertEquals(2, c.getMessages().size());

    }

    @Test
    public void dropChannel(){
        Channel chal=new Channel();
        chal.setStatus("public");
        chal.setName("B3");
        User u=new UserDao().get(50);

        message m1 = new message();
        m1.setMsg("coucou");
        m1.setUser( u);
        m1.setId_channel(chal.getId());

        message m2 = new message();
        m2.setMsg("avion");
        m2.setUser( u);
        m2.setId_channel(chal.getId());

        chal.setMessages(Arrays.asList(m1, m2));
        long id=new ChannelDao().save(chal);
        Channel c = new ChannelDao().get(id);
        new ChannelDao().delete(chal);


        Assert.assertEquals(2, c.getMessages().size());

    }


}
