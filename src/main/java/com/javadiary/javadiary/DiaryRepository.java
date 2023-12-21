package com.javadiary.javadiary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {

    @Query("SELECT d FROM Diary d WHERE d.date <= CURRENT_DATE()")
    List<Diary> findDiaryEntriesForTodayAndEarlier();

    @Query("SELECT d, d FROM Diary d WHERE d.date BETWEEN :fromDate AND :toDate ")
    List<Diary> findDiaryEntriesBetweenTwoDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
