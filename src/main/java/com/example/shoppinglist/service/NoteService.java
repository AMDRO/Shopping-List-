package com.example.shoppinglist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.shoppinglist.exception.ResourceNotFoundException;
import com.example.shoppinglist.model.Note;
import com.example.shoppinglist.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	public Note save(Note note) {
		return noteRepository.save(note);
	}

	public Note findById(Long id) {
		return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}

	public Note update(Long id, Note editDetails) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		note.setTitle(editDetails.getTitle());
		note.setContent(editDetails.getContent());
		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	public ResponseEntity<?> delete(Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
}
