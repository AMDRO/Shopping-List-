package com.example.shoppinglist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppinglist.model.Note;
import com.example.shoppinglist.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteService.findAll();
	}

	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteService.save(note);
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteService.findById(noteId);
	}

	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {
		return noteService.update(noteId, noteDetails);
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		return noteService.delete(noteId);
	}
}
