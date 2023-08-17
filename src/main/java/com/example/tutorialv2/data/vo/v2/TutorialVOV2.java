package com.example.tutorialv2.data.vo.v2;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Data
public class TutorialVOV2  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String description;
    private boolean published;

    private Date dtInclusao;

    public TutorialVOV2(){

    }
    public TutorialVOV2(String descriptionm, boolean b, String title) {
    }


}
