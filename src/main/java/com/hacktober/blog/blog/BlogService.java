package com.hacktober.blog.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import com.hacktober.blog.notification.CommentNotificationService;

@Service
public class BlogService {

    private static final String COLLECTION_NAME = "blogs";
    @Autowired
    private CommentNotificationService commentNotificationService;

    /** Create a new blog */
    public String create(Blog blog) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // Firestore generates document ID automatically
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        ApiFuture<WriteResult> result = docRef.set(blog);
        return docRef.getId(); // return generated document ID
    }

    /** Get all blogs */
    public List<Blog> getAll() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        List<Blog> blogs = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            blogs.add(doc.toObject(Blog.class));
        }
        return blogs;
    }

    /** Increment likes for a blog by documentId and username */
    public String incrementLikes(String blogId, String username) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(blogId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) return "Blog not found";

        Blog blog = snapshot.toObject(Blog.class);

        Map<String, Object> likes = blog.getLikes();
        List<String> likedBy = (List<String>) likes.get("likedBy");

        if (!likedBy.contains(username)) {
            likedBy.add(username);
            likes.put("count", likedBy.size());
            likes.put("likedBy", likedBy);
            blog.setLikes(likes);
            ApiFuture<WriteResult> result = docRef.set(blog);
            return result.get().getUpdateTime().toString();
        }

        return "User already liked this blog";
    }

    /** Add comment to a blog */
    public String addComment(String blogId, String username, String commentText) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(blogId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) return "Blog not found";

        Blog blog = snapshot.toObject(Blog.class);

        List<Map<String, String>> comments = blog.getComments();
        Map<String, String> comment = Map.of(
                "username", username,
                "comment", commentText
        );
        comments.add(comment);
        blog.setComments(comments);

        ApiFuture<WriteResult> result = docRef.set(blog);
        try {
            commentNotificationService.notifyOwnerOfNewComment(blogId, blog, username, commentText);
        } catch (Exception ex) {
        System.err.println("Failed to schedule comment notification: " + ex.getMessage());
        }
        
        return result.get().getUpdateTime().toString();
    }
    
    /** Get all blogs by a specific username */
    public List<Blog> getBlogsByUsername(String username) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // Query where username matches
        ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_NAME)
                                           .whereEqualTo("username", username)
                                           .get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        List<Blog> blogs = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            blogs.add(doc.toObject(Blog.class));
        }
        return blogs;
    }
}
