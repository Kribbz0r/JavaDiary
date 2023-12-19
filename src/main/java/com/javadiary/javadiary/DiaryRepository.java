package com.javadiary.javadiary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {

    @Query("SELECT d FROM Diary d")
    List<Diary> findDiaryId();
}
