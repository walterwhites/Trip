package com.ecommerce.clientui.beans;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentBean implements Serializable {

    private int id;
    private int adventureId;
    private LocalDateTime updatedDate;
    private Long clientId;
    private String content;

    public CommentBean() {
    }

    public CommentBean(int id, int adventureId, LocalDateTime updatedDate, Long clientId, String content) {
        this.id = id;
        this.adventureId = adventureId;
        this.updatedDate = updatedDate;
        this.clientId = clientId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "id=" + id +
                ", adventureId=" + adventureId +
                ", updatedDate=" + updatedDate +
                ", clientId=" + clientId +
                ", content='" + content + '\'' +
                '}';
    }
}