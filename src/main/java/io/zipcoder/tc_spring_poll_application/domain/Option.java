package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
public class Option {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "option_value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
