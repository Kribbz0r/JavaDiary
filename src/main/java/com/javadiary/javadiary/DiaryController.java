package com.javadiary.javadiary;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    Diary currentDiary;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("diary", diaryRepository.findDiaryEntriesForTodayAndEarlier());

        return "index";
    }

    @GetMapping("/getSelectedDates")
    public String getSelectedDates(Model model, @PathVariable(value = "date1") Date date1,
            @PathVariable(value = "date2") Date date2) {
        model.addAttribute("date1", diaryRepository.findDiaryEntriesBetweenTwoDates());
        return "index";
    }

    @PostMapping("/addNewDiaryEntry")
    public String addNewDiaryEntry(@ModelAttribute("diary") Diary diary) {
        diaryRepository.save(diary);
        System.out.println("Dagboksinlägg med ID: " + diary.getId() + " är sparat till databasen");
        return "redirect:/";
    }

    @GetMapping("/showNewDiaryEntry")
    String showNewDiaryEntry(Model model) {
        Diary diary = new Diary();
        model.addAttribute("diary", diary);
        return "/newDiaryEntry";
    }

    @GetMapping("/showEditDiaryEntry/{id}")
    String showEditDiaryEntry(@PathVariable(value = "id") int id, Model model) {
        Optional<Diary> diary = diaryRepository.findById(id);
        model.addAttribute("diary", diary);
        return "editDiaryEntry";

    }

    @GetMapping("/diaryEntry/{id}")
    public String getDiaryEntry(Model model, @PathVariable(value = "id") int diaryEntry) {
        Optional<Diary> diary = diaryRepository.findById(diaryEntry);
        if (diary.isPresent()) {
            model.addAttribute("diaryEntry", diary.get());
            System.out.println("Detta är mitt id: " + diaryEntry);
            System.out.println("detta är min överskrift: " + diary.get().getHeading());
            return "/diaryEntry";
        }
        return "redirect:/";
    }

    @GetMapping("/editDiaryEntry/{id}")
    String editDiaryEntry(@PathVariable(value = "id") int id, Model model) {
        Diary diary = diaryRepository.findById(id).get();
        model.addAttribute("diary", diary);
        System.out.println("GetMapping för editDiaryEntry/{id}");
        return "editDiaryEntry";
    }

    @GetMapping("/deleteDiaryEntry/{id}")
    String deleteDiaryEntry(@PathVariable(value = "id") int id) {

        System.out.println("delete mapping: " + id);
        diaryRepository.deleteById(id);
        return "redirect:/";
    }

}
