package com.example.tutorialv2.repository;

import com.example.tutorialv2.data.vo.v1.TutorialVO;
import com.example.tutorialv2.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Query(value = "SELECT * FROM tutorials t WHERE t.published=true", nativeQuery = true)
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);

    @Query(value = "SELECT * FROM tutorials t WHERE " +
            "t.title LIKE LOWER(CONCAT('%',:buscar, '%'))" +
            "Or t.description LIKE  LOWER(CONCAT('%', :buscar, '%'))" ,nativeQuery = true)
    List<Tutorial> searchTutorials(String buscar);

    @Query(value = "SELECT * FROM tutorials t WHERE " +
            "t.title LIKE CONCAT('%',:buscar, '%')" +
            "Or t.description LIKE CONCAT('%', :buscar, '%')" , nativeQuery = true)
    List<Tutorial> searchTutorialsSQL(String buscar);



}
