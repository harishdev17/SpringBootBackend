package com.hacktober.blog.blog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.hacktober.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<String>> createBlog(@RequestBody Blog blog) throws InterruptedException, ExecutionException {
        String result = blogService.create(blog);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Blog created successfully"));
    }

    /** Get all blogs */
    @GetMapping("/all")
    @Operation(summary = "List blogs", description = "Fetch all blogs currently stored in the database.")
    public ResponseEntity<ApiResponse<List<Blog>>> getAllBlogs() throws InterruptedException, ExecutionException {
        List<Blog> blogs = blogService.getAll();
        return ResponseEntity.ok(ApiResponse.success(blogs, "Blogs retrieved successfully"));
    }

    /** Increment likes */
    @PostMapping("/{blogId}/like")
    @Operation(summary = "Like a blog", description = "Increment the like counter for a specific blog and user.")
    public ResponseEntity<ApiResponse<String>> likeBlog( @PathVariable String blogId, @RequestParam String username) throws InterruptedException, ExecutionException {
        String result = blogService.incrementLikes(blogId, username);
        return ResponseEntity.ok(ApiResponse.success(result, "Blog liked successfully"));
    }

    /** Add comment */
    @PostMapping("/{blogId}/comment")
    @Operation(summary = "Comment on a blog", description = "Attach a comment to a blog on behalf of a user.")
    public ResponseEntity<ApiResponse<String>> commentBlog(@PathVariable String blogId, @RequestParam String username, @RequestParam String comment) throws InterruptedException, ExecutionException {
        String result = blogService.addComment(blogId, username, comment);
        return ResponseEntity.ok(ApiResponse.success(result, "Comment added successfully"));
    }

    /** Get all blogs by username */
    @GetMapping("/user/{username}")
    @Operation(summary = "List user blogs", description = "Retrieve all blogs authored by the specified user.")
    public ResponseEntity<ApiResponse<List<Blog>>> getBlogsByUsername(@PathVariable String username)
            throws InterruptedException, ExecutionException {
        List<Blog> blogs = blogService.getBlogsByUsername(username);
        return ResponseEntity.ok(ApiResponse.success(blogs, "Blogs retrieved successfully"));
    }

}
