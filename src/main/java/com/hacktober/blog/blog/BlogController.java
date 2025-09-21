package com.hacktober.blog.blog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /** Create a new blog */
    @PostMapping("/create")
    public String createBlog(@RequestBody Blog blog) throws InterruptedException, ExecutionException {
        return blogService.create(blog);
    }

    /** Get all blogs */
    @GetMapping("/all")
    public List<Blog> getAllBlogs() throws InterruptedException, ExecutionException {
        return blogService.getAll();
    }

    /** Increment likes */
    @PostMapping("/{blogId}/like")
    public String likeBlog(@PathVariable String blogId, @RequestParam String username) throws InterruptedException, ExecutionException {
        return blogService.incrementLikes(blogId, username);
    }

    /** Add comment */
    @PostMapping("/{blogId}/comment")
    public String commentBlog(@PathVariable String blogId,
                              @RequestParam String username,
                              @RequestParam String comment) throws InterruptedException, ExecutionException {
        return blogService.addComment(blogId, username, comment);
    }
    
    /** Get all blogs by username */
    @GetMapping("/user/{username}")
    public List<Blog> getBlogsByUsername(@PathVariable String username) 
            throws InterruptedException, ExecutionException {
        return blogService.getBlogsByUsername(username);
    }

}
