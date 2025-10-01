package com.hacktober.blog.blog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/blogs")
@Tag(name = "Blogs", description = "Operations for managing blog posts")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /** Create a new blog */
    @PostMapping("/create")
    @Operation(summary = "Create a blog", description = "Create a new blog entry with metadata, content and author.")
    public String createBlog(@RequestBody Blog blog) throws InterruptedException, ExecutionException {
        return blogService.create(blog);
    }

    /** Get all blogs */
    @GetMapping("/all")
    @Operation(summary = "List blogs", description = "Fetch all blogs currently stored in the database.")
    public List<Blog> getAllBlogs() throws InterruptedException, ExecutionException {
        return blogService.getAll();
    }

    /** Increment likes */
    @PostMapping("/{blogId}/like")
    @Operation(summary = "Like a blog", description = "Increment the like counter for a specific blog and user.")
    public String likeBlog(@PathVariable String blogId, @RequestParam String username) throws InterruptedException, ExecutionException {
        return blogService.incrementLikes(blogId, username);
    }

    /** Add comment */
    @PostMapping("/{blogId}/comment")
    @Operation(summary = "Comment on a blog", description = "Attach a comment to a blog on behalf of a user.")
    public String commentBlog(@PathVariable String blogId,
                              @RequestParam String username,
                              @RequestParam String comment) throws InterruptedException, ExecutionException {
        return blogService.addComment(blogId, username, comment);
    }
    
    /** Get all blogs by username */
    @GetMapping("/user/{username}")
    public List<Blog> getBlogsByUsername(@PathVariable String username)
    @Operation(summary = "List user blogs", description = "Retrieve all blogs authored by the specified user.")
            throws InterruptedException, ExecutionException {
        return blogService.getBlogsByUsername(username);
    }

}
