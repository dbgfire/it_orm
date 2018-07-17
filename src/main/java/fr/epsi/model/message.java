package fr.epsi.model;


import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "message")
public class message implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String msg;
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private User user;
    private long id_channel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId_channel() {
        return id_channel;
    }

    public void setId_channel(long id_channel) {
        this.id_channel = id_channel;
    }
}
