package me.geik.invmng.model;

import lombok.Data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

@Data
public class BaseEntity implements Serializable {

    @Id
    private Integer id;
    public boolean isNew() {
        return this.id == null;
    }
}
