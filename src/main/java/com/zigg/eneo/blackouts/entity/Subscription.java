package com.zigg.eneo.blackouts.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Subscription extends PanacheEntity {

    @ManyToOne
    public KeyPair keys;

    @NotBlank
    public String endpoint;

    public static Subscription findByEndpoint(String endpoint){
        return find("endpoint", endpoint).firstResult();
    }

    @Override
    public String toString() {
        return "Subscription{" + "endpoint=" + endpoint + ", keys=" + keys + '}';
    }
}
