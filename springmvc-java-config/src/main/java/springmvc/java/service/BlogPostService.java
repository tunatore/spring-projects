package springmvc.java.service;

import java.util.List;

import springmvc.java.domain.BlogPost;
import springmvc.java.domain.User;

public interface BlogPostService {

	void savePost(BlogPost blogPost);
	void saveAsDraft(BlogPost blogPost);
	void deletePost(BlogPost blogPost);
	List<BlogPost> listAllBlogPostsByUserAndDraftStatus(User user, boolean draft);
	List<BlogPost> listAllBlogPostsByUserAndTitleLike(User user, String title);
	BlogPost findBlogPostById(long id);
	
}
