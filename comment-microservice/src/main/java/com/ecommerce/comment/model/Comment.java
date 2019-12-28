package com.ecommerce.comment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "comment")
@Entity
@Getter
@Setter
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, name = "adventureId")
    private int adventureId;

    @Column(name="updatedDate")
    private LocalDateTime updatedDate;

    @Column(name="client")
    private Long clientId;

    @Column(name="content")
    private String content;

    public Comment() {
    }

    public Comment(int id, int adventureId, LocalDateTime updatedDate, Long clientId, String content) {
        this.id = id;
        this.adventureId = adventureId;
        this.updatedDate = updatedDate;
        this.clientId = clientId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", adventureId=" + adventureId +
                ", updatedDate=" + updatedDate +
                ", content=" + content +
                ", clientId=" + clientId +
                '}';
    }
}
