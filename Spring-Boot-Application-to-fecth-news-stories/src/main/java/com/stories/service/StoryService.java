package com.stories.service;

import java.util.List;

import com.stories.model.StoryComments;
import com.stories.model.StroryDetails;

public interface StoryService {

	List<StroryDetails> getTopStories();

	List<StoryComments> getCommnets(Long sId);

	List<StroryDetails> getPastStories();

}
