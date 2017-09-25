package com.blackboxproject.service;

import java.util.List;

import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;

public interface PostService {

	public List<PostVO> getPosts(int courseId, int boardId) throws Exception;

	public void createPost(PostVO vo) throws Exception;

	public PostVO getPostByPostId(int postId) throws Exception;

	public List<FileVO> getFilesByPostId(int postId) throws Exception;

	public void modifyPost(PostVO pvo) throws Exception;

	public PostVO getLecturePostById(int postId) throws Exception;

	public List<PostVO> getLectureList(int courseId);

	public FileVO getFilePathForLecture(int postId) throws Exception;
	
	
	
}
