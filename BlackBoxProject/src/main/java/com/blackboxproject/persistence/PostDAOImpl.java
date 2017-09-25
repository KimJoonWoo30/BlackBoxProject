package com.blackboxproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackboxproject.domain.FileVO;
import com.blackboxproject.domain.PostVO;

@Repository
public class PostDAOImpl implements PostDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.blackboxproject.mapper.PostMapper";
	
	@Override
	public List<PostVO> getPosts(int courseId, int boardId) throws Exception {
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("courseId", courseId);
		map.put("boardId", boardId);
		
		return session.selectList(namespace+".getPosts", map);
	}

	@Override
	public void createPost(PostVO vo) throws Exception {
		
		session.insert(namespace+".createPost", vo);
		
	}

	@Override
	public void addFile(FileVO fvo) throws Exception {
		session.insert(namespace+".addFile", fvo);
	}

	@Override
	public PostVO getPostByPostId(int postId) throws Exception {
		
		return session.selectOne(namespace + ".getPostByPostId", postId);
	}

	@Override
	public List<FileVO> getFilesByPostId(int postId) throws Exception {
		return session.selectList(namespace +".getFilesByPostId", postId);
	}

	@Override
	public void updatePostView(int postId) {
		session.update(namespace+".updatePostView", postId);
	}

	@Override
	public void modifyPost(PostVO pvo) {
		session.update(namespace+".modifyPost", pvo);
	}

	@Override
	public FileVO getImageDirectory(int postId) {
		
		return session.selectOne(namespace+".getImageDirectory", postId);
	}

	@Override
	public List<PostVO> getLectureList(int courseId) {
		return session.selectList(namespace+".getLectureList", courseId);
	}

	@Override
	public FileVO getFilePathForLecture(int postId) throws Exception {
		return session.selectOne(namespace+".getFilePathForLecture", postId);
	}

	public void updateReplyCnt(Integer postId, int amount) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("postId", postId);
		paramMap.put("amount", amount);
		session.update(namespace + ".updateReplyCnt", paramMap);

	}

}
