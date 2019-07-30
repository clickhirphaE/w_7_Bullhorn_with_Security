
package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity  //this annotation tells u a table should be created in your database
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    private String title;

    @NotNull
    @Size(min=3)
    private String content;

    @NotNull
    @Size(min=10)
    private String postedby;
    @ManyToOne(fetch=FetchType.EAGER) //many to one
    @JoinColumn(name="user_id")
    private User user;
    public Message() {
    }

    public Message(long id, String title, String content, String postedby) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postedby = postedby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }


}
