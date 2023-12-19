package com.javadiary.javadiary;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("diary", diaryRepository.findAll());

        return "index";
    }

    @PostMapping("/addNewDiaryEntry")
    public String addNewDiaryEntry(@RequestParam("heading") String heading, @RequestParam("text") String text,
            @RequestParam("date") Date date) {
        Diary diary = new Diary();
        diary.setHeading(heading);
        diary.setText(text);
        diary.setDate(date);
        diaryRepository.save(diary);
        System.out.println("Funkar /addNewDiaryEntry?");
        return "redirect:/newDiaryEntry";
    }

    @GetMapping("/newDiaryEntry")
    String newDiaryEntry(Model model) {
        return "/newDiaryEntry";
    }

    // @GetMapping("/newDiaryEntry/{diaryEntry}")
    // String getDiaryEntry(Model model, @RequestParam int diaryEntry) {
    // model.addAttribute("diaryEntry", diaryRepository.findById(diaryEntry));
    // System.out.println("händer något?");
    // return "/newDiaryEntry";
    // }

    @GetMapping("/diaryEntry/{id}")
    public String getDiaryEntry(Model model, @PathVariable(value = "id") int diaryEntry) {
        Optional<Diary> diary = diaryRepository.findById(diaryEntry);
        if (diary.isPresent()) {
            model.addAttribute("diaryEntry", diary); // här ska det göras grejer
            System.out.println("sker jag?");
            return "/diaryEntry";
        }

        // for (Diary diary : diaryRepository.findDiaryId()) {
        // if (diary.getId() == (diaryEntry.getId())) {
        // currentDiary.setId(diaryEntry);
        // return "redirect:/{diaryEntry}";
        // }
        // }

        return "redirect:/";
    }

    @GetMapping("/editDiaryEntry")
    String editDiaryEntry(Model model) {
        return "/editDiaryEntry";
    }
}
