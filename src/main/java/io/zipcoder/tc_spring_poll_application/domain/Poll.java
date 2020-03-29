package io.zipcoder.tc_spring_poll_application.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private Long id;

    @NotEmpty
    @Column(name = "question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    @OrderBy
    @Size(min = 2, max = 6)
    private Set<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
