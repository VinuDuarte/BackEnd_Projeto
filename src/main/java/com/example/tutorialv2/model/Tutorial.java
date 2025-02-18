package com.example.tutorialv2.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tutorials")
@Data
@SequenceGenerator(name = "tutorial_seq", sequenceName = "tutorial_seq",
        initialValue = 1, allocationSize = 1)
public class Tutorial {

    @Id
    @GeneratedValue(strategy =
            GenerationType.SEQUENCE, generator = "tutorial_seq")
    private long id;

    @Column(name = "title")
    @Nullable
    private String title;

    @Column(name = "description")
    @Nullable
    private String description;

    @Column(name = "published")
    private boolean published;


    public Tutorial(){

    }
    public Tutorial(String descriptionm, boolean b, String title) {
    }


}
