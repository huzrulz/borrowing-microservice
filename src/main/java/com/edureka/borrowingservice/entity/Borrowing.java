package com.edureka.borrowingservice.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="authorId")
    private long authorId;

    @Column(name ="bookId")
    private long bookId;
    
    public Borrowing() {
        
    }

    public Borrowing(long authorId, long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
        
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
      }
    
      public void setAuthorId(long authorId) {
        this.authorId = authorId;
      }
      public Long getAuthorId() {
        return authorId;
      }

      public void setBookId(long bookId) {
        this.bookId = bookId;
      }
      public Long getBookId() {
        return bookId;
      }
    
}

