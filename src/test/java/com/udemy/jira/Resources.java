package com.udemy.jira;

public class Resources {
	
	public static String createSessionId() {
		String resource = "/rest/auth/1/session";
		return resource;
	}
	
	public static String createIssue() {
		String issue = "/rest/api/2/issue";
		return issue;
	}
	
	public static String deleteIssue(String key) {
		String delete = "/rest/api/2/issue/" + key;
		return delete;
	}
	
	public static String addComment(String key) {
		String addComment = "/rest/api/2/issue/"+key+"/comment/";
		return addComment;
	}
	
	public static String updateComment(String key, int id) {
		String updateComment = "/rest/api/2/issue/"+key+"/comment/"+id;
		return updateComment;
	}

}
