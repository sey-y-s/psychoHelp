package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.dto.QuestionsTestReponseDTO;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionsTestRepository extends JpaRepository<QuestionsTest, Integer> {
    @Query("select q from QuestionsTest q    where q.test.id=:testId order by q.id desc ")
    public List<QuestionsTest> getallQuestionsbyTestId(@Param("testId") int testId);

}
