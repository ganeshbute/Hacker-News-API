package com.stories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stories.service.StoryService;

@RestController
public class StoriesController {

	@Autowired
	private StoryService storyService;

	@GetMapping("/top-stories")
	ResponseEntity<?> getUser() {
		try {
			return new ResponseEntity<>(storyService.getTopStories(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/past-stories")
	public ResponseEntity<?> getCountrys() {
		try {
			if (storyService.getPastStories().isEmpty()) {
				return new ResponseEntity<>("No Past Stories Available", HttpStatus.OK);
			}
			return new ResponseEntity<>(storyService.getPastStories(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/comment")
	public ResponseEntity<?> getCommentsOnStory(@RequestParam(name = "stroryId") Long sId) {
		try {
			if (storyService.getCommnets(sId).isEmpty()) {
				return new ResponseEntity<>("No Past Stories Available", HttpStatus.OK);
			}
			return new ResponseEntity<>(storyService.getCommnets(sId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
