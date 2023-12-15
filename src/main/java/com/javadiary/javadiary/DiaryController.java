package com.javadiary.javadiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Fixa senare. Jannes första video
    @GetMapping("/new")
    public String addNew() {
        Diary diary = new Diary();
        diary.setHeading("Överskrift");
        diary.setText("Brödtext dagboksinlägg");
        diary.setDate(null);
        diaryRepository.save(diary);
        System.out.println("Funkar /new?");
        return "redirect:/";
    }

}
