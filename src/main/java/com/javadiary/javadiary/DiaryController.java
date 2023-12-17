package com.javadiary.javadiary;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("diary", diaryRepository.findAll());
        return "index";
    }

    @PostMapping("/addNewDiaryEntry")
    public String addNewDiaryEntry(@RequestParam("heading") String heading, @RequestParam("text") String text
    /* @RequestParam("date") Date date */) {
        Diary diary = new Diary();
        diary.setHeading(heading);
        diary.setText(text);
        diary.setDate(null); // Hur funkar Date? Vad ska jag använda?
        diaryRepository.save(diary);
        System.out.println("Funkar /addNewDiaryEntry?");
        return "redirect:/newDiaryEntry";
    }

    @GetMapping("/newDiaryEntry")
    String newDiaryEntry(Model model) {
        return "/newDiaryEntry";
    }
}
