package com.example.study.controller;

import com.example.study.data.entity.NoteEntity;
import com.example.study.service.NoteServiceDB;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RestController
public class NoteController {
    NoteEntity note;
    @Autowired
    NoteServiceDB noteService;

    @GetMapping(value = "/V2/note/list")
    public ModelAndView getNotesList() {
        ModelAndView result = new ModelAndView("V2/note/list");
        result.addObject("list", noteService.listAll());
        return result;
    }

    @PostMapping(value = "/V2/note/create")
    public ModelAndView createNote(
            @RequestParam(value="noteID") Long id,
            @RequestParam(value="noteTitle") String title,
            @RequestParam(value="noteContent") String content) {
        note = new NoteEntity();
        note.setTitle(title);
        note.setContent(content);
        note.setId(id);
        noteService.add(note);
        return new ModelAndView("redirect:list");

    }

    @PostMapping(value = "/V2/note/delete")
    public ModelAndView deleteNoteById(@NotNull @RequestParam(value="id") Long noteId, HttpServletResponse httpServletResponse)  {
        httpServletResponse.setStatus(302);
        noteService.deleteById(noteId);
        return new ModelAndView("redirect:list");
    }

    @GetMapping (value = "/V2/note/delete")
    public ModelAndView deleteNote()  {
        return new ModelAndView("V2/note/delete");
    }

    @GetMapping(value = "/V2/note/edit")
    public ModelAndView editNote(@RequestParam(value="id") Long noteId, String content) throws Exception {
        return editNoteById(noteId, content);
    }

    @PostMapping(value = "/V2/note/edit")
    public ModelAndView editNoteById(@NotNull @RequestParam(value="id") Long noteId, String content) throws Exception {
        ModelAndView result = new ModelAndView("V2/note/edit");
        result.setStatus(HttpStatusCode.valueOf(302));
        noteService.update(noteId, content);
        return new ModelAndView("redirect:list");
    }
}
