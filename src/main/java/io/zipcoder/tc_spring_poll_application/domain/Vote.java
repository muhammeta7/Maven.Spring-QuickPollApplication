package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="vote_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="option_id")
    private Option option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
