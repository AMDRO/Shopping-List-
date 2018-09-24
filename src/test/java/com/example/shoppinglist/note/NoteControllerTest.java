package com.example.shoppinglist.note;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.shoppinglist.controller.NoteController;
import com.example.shoppinglist.model.Note;
import com.example.shoppinglist.service.NoteService;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class NoteControllerTest {

	private MockMvc mockMvc;
	@Mock
	private NoteService noteService;
	@InjectMocks
	private NoteController noteController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
	}

	@Test
	public void testGetAllNotes() throws Exception {
		List<Note> notes = new ArrayList<>();
		notes.add(new Note(1L, "Fruits", "apples, pears"));
		notes.add(new Note(2L, "Vegetables", "tomatoes, carrots"));
		System.out.println("notes: " + notes.size());
		when(noteService.findAll()).thenReturn(notes);
		mockMvc.perform(get("/api/notes")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[0].title", is("Fruits"))).andExpect(jsonPath("$[1].title", is("Vegetables")))
				.andExpect(jsonPath("$[0].content", is("apples, pears")))
				.andExpect(jsonPath("$[1].content", is("tomatoes, carrots")));
		verify(noteService, times(1)).findAll();
		verifyNoMoreInteractions(noteService);
	}
}
