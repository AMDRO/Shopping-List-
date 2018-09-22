package com.example.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppinglist.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
