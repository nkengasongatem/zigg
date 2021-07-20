package com.zigg.eneo.blackouts.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class KeyPair extends PanacheEntity {
    @NotBlank
    public String auth;
    @NotBlank
    public String p256dh;
}
