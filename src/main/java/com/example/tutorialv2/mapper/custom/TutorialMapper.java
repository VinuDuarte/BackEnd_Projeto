package com.example.tutorialv2.mapper.custom;

import com.example.tutorialv2.data.vo.v2.TutorialVOV2;
import com.example.tutorialv2.model.Tutorial;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TutorialMapper {

    public TutorialVOV2 convertEntityToVo(Tutorial tutorial){
        TutorialVOV2 vo = new TutorialVOV2();
        vo.setId(tutorial.getId());
        vo.setDescription(tutorial.getDescription());
        vo.setTitle(tutorial.getTitle());
        vo.setPublished(tutorial.isPublished());
        vo.setDtInclusao(new Date());

        return vo;
    }

    public Tutorial convertVoToEnity(TutorialVOV2 tutorial){
        Tutorial enity = new Tutorial();
        enity.setId(tutorial.getId());
        enity.setDescription(tutorial.getDescription());
        enity.setTitle(tutorial.getTitle());
        enity.setPublished(tutorial.isPublished());
        //enity.setDtInclusao(new Date());

        return enity;
    }
}
