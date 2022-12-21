package com.stories.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stories.model.StoryComments;
import com.stories.model.StroryDetails;

@Service
public class StoryServiceImpl implements StoryService {

	private Date date = null;

	private List<StroryDetails> sortedStories = new ArrayList<StroryDetails>();

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<StroryDetails> getTopStories() {
		String resourceUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";
		List<Integer> userList = restTemplate.getForObject(resourceUrl, List.class);
		List<StroryDetails> list = new ArrayList<StroryDetails>();
		System.out.println("Fecth Date and Time:" + date);
		if (date != null) {
			int xMinutes = 2 * 60 * 1000;
			long dateIsoinMillis = date.getTime();
			long xMinsAgo = System.currentTimeMillis() - xMinutes;

			if (dateIsoinMillis > xMinsAgo) {
				return sortedStories;
			} else {
				System.out.println("not null Last Call is more than 15 min::" + new Date());
				for (int i = 0; i < userList.size(); i++) {
					resourceUrl = "https://hacker-news.firebaseio.com/v0/item/" + userList.get(i) + ".json";
					StroryDetails storyList = restTemplate.getForObject(resourceUrl, StroryDetails.class);

					/*------Get last 15 min stories commenting coz last 15 min stories not available most time-----*/
					/*
					 * Date newDate = new java.util.Date(storyList.getTime() * 1000L);
					 * SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 * sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4")); String
					 * formattedDate = sdf.format(newDate);
					 * 
					 * System.out.println(formattedDate); int xMinutesNew = 15 * 60 * 1000; long
					 * dateIsoinMillisNew = newDate.getTime(); long xMinsAgoNew =
					 * System.currentTimeMillis() - xMinutesNew; if (dateIsoinMillisNew >
					 * xMinsAgoNew) { list.add(storyList); }
					 */
					list.add(storyList);
					date = new Date();
				}
				sortedStories = list.stream().sorted(Comparator.comparing(StroryDetails::getScore)).limit(10)
						.collect(Collectors.toList());
				return sortedStories;
			}
		} else {
			for (int i = 0; i < userList.size(); i++) {
				resourceUrl = "https://hacker-news.firebaseio.com/v0/item/" + userList.get(i) + ".json";
				StroryDetails storyList = restTemplate.getForObject(resourceUrl, StroryDetails.class);

				/*------Get last 15 min stories commenting coz last 15 min stories not available most time-----*/
				/*
				 * Date newDate = new java.util.Date(storyList.getTime() * 1000L);
				 * SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 * sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4")); String
				 * formattedDate = sdf.format(newDate);
				 * 
				 * System.out.println(formattedDate); int xMinutes = 15 * 60 * 1000; long
				 * dateIsoinMillis = newDate.getTime(); long xMinsAgo =
				 * System.currentTimeMillis() - xMinutes; if (dateIsoinMillis > xMinsAgo) {
				 * list.add(storyList); }
				 */
				list.add(storyList);
				date = new Date();
			}
			System.out.println("First Call api::" + new Date());
			sortedStories = list.stream().sorted(Comparator.comparing(StroryDetails::getScore)).limit(10)
					.collect(Collectors.toList());
			return sortedStories;
		}
	}

	@Override
	public List<StroryDetails> getPastStories() {
		return sortedStories;
	}

	@Override
	public List<StoryComments> getCommnets(Long sId) {
		String resourceUrl = "https://hacker-news.firebaseio.com/v0/item/" + sId + ".json";
		StroryDetails storyList = restTemplate.getForObject(resourceUrl, StroryDetails.class);
		List<StoryComments> commList = new ArrayList<StoryComments>();
		for (int i = 0; i < storyList.getKids().size(); i++) {
			resourceUrl = "https://hacker-news.firebaseio.com/v0/item/" + storyList.getKids().get(i) + ".json";
			StoryComments storyComments = restTemplate.getForObject(resourceUrl, StoryComments.class);
			commList.add(storyComments);
		}
		return commList.stream().limit(10).collect(Collectors.toList());
	}

}
