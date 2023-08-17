package com.example.tutorialv2.data.vo.v1;

import lombok.Data;



@Data
public class TutorialVO {

    private long id;


    private String title;


    private String description;


    private boolean published;


    public TutorialVO(){

    }
    public TutorialVO(String descriptionm, boolean b, String title) {
    }


}
