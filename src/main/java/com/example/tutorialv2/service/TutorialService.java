package com.example.tutorialv2.service;

import com.example.tutorialv2.data.vo.v1.TutorialVO;
import com.example.tutorialv2.data.vo.v2.TutorialVOV2;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.mapper.custom.TutorialMapper;
import com.example.tutorialv2.model.Tutorial;
import com.example.tutorialv2.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TutorialService {

    @Autowired
    private TutorialMapper tutorialMapper;


    @Autowired
    TutorialRepository tutorialRepository;

    public List<TutorialVO> findAll(String title) throws Exception{

            List<TutorialVO> tutorials = new ArrayList<TutorialVO>();


            if (title == null){
                //var enity = tutorials.addAll(tutorialRepository.findAll());
                return DozerMapper.parseListObjects(tutorialRepository.findAll(),TutorialVO.class);
            }else {
                //tutorials.addAll(tutorialRepository.findByTitleContaining(title));
                return DozerMapper.parseListObjects(tutorialRepository.findByTitleContaining(title),TutorialVO.class);
            }

    }

    public TutorialVO findById(long id) throws Exception{

       var tutorialData = tutorialRepository.findById(id)
               .orElseThrow(() -> new NaoEncontradoException("Tutorinal não Cadastrado"));

           return DozerMapper.parseObject(tutorialData, TutorialVO.class);

    }

    public List<TutorialVO> searchTutorials(String buscar) throws Exception{
        //List<TutorialVO> tutorials = tutorialRepository.searchTutorials(buscar);
        var enity = tutorialRepository.searchTutorials(buscar);
        return DozerMapper.parseListObjects(enity, TutorialVO.class);
    }

    public TutorialVO createTutorial(TutorialVO tutorial) throws Exception{
            var enity = DozerMapper.parseObject(tutorial, Tutorial.class);
            var vo = DozerMapper.parseObject(tutorialRepository.save(enity),TutorialVO.class);

            return vo;
    }

    public TutorialVOV2 createTutorialV2(TutorialVOV2 tutorial) throws Exception{
        var enity = tutorialMapper.convertVoToEnity(tutorial);

        var vo = tutorialMapper.convertEntityToVo(tutorialRepository.save(enity));

        return vo;
    }

    public TutorialVO updateTutorial(TutorialVO tutorial) throws Exception {
       var tutorialData = tutorialRepository.findById(tutorial.getId())
               .orElseThrow(() -> new NaoEncontradoException("ID Não ENCONTRADO"));


        tutorialData.setTitle(tutorial.getTitle());
        tutorialData.setDescription(tutorial.getDescription());
        tutorialData.setPublished(tutorial.isPublished());

           var vo = DozerMapper.parseObject(tutorialRepository.save(tutorialData), TutorialVO.class);
           return vo;
    }

    public void deleteTutorial(Long id) throws Exception {
           var enity = tutorialRepository.findById(id)
                   .orElseThrow(() -> new NaoEncontradoException("ID ENCONTRADO"));
            tutorialRepository.delete(enity);

    }

    public List<TutorialVO> findByPublished() throws Exception{

            var  tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()){
               throw  new NaoEncontradoException("Registro não encontrado");
            }

        return DozerMapper.parseListObjects(tutorials, TutorialVO.class);


    }







}
