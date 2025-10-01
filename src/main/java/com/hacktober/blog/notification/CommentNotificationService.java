package com.hacktober.blog.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hacktober.blog.blog.Blog;
import com.hacktober.blog.email.EmailService;
import com.hacktober.blog.user.User;
import com.hacktober.blog.user.UserService; // adjust if package/name differs

// Firestore fallback imports
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CommentNotificationService {

    @Autowired
    private EmailService emailService;

    // UserService is optional — if present, we'll use it
    @Autowired(required = false)
    private UserService userService;

    @Value("${notifications.enabled:true}")
    private boolean notificationsEnabled;

    @Value("${app.frontend.url:}")
    private String frontendUrl;

    /**
     * Asynchronously notifies the blog owner about a new comment.
     * - blogId: ID of the blog document (used for a link)
     * - blog: the Blog object after saving
     * - commenterUsername: the username of who commented
     * - commentText: the comment content
     */
    @Async
    public void notifyOwnerOfNewComment(String blogId, Blog blog, String commenterUsername, String commentText) {
        if (!notificationsEnabled) return;
        if (blog == null) return;

        String ownerUsername = blog.getUsername();
        if (ownerUsername == null || ownerUsername.isBlank()) return;

        // don't notify if owner commented on their own post
        if (ownerUsername.equalsIgnoreCase(commenterUsername)) return;

        String ownerEmail = null;

        // Try UserService lookup first (adapt method name if needed)
        try {
            if (userService != null) {
                // Adjust method name if your UserService exposes a different method
                User owner = userService.getByUsername(ownerUsername);
                if (owner != null) {
                    ownerEmail = owner.getEmail();
                }
            }
        } catch (Exception ex) {
            // ignore and fallback to Firestore
        }

        // Fallback: query Firestore 'users' collection for ownerUsername
        if (ownerEmail == null) {
            try {
                Firestore db = FirestoreClient.getFirestore();
                ApiFuture<QuerySnapshot> future = db.collection("users")
                        .whereEqualTo("username", ownerUsername)
                        .get();
                List<QueryDocumentSnapshot> docs = future.get().getDocuments();
                if (!docs.isEmpty()) {
                    User owner = docs.get(0).toObject(User.class);
                    if (owner != null) ownerEmail = owner.getEmail();
                }
            } catch (Exception ex) {
                // if fallback fails, just return
            }
        }

        if (ownerEmail == null || ownerEmail.isBlank()) return;

        String subject = "New comment on your post: " + (blog.getTitle() == null ? "" : blog.getTitle());
        StringBuilder body = new StringBuilder();
        body.append("Hello ").append(ownerUsername).append(",\n\n");
        body.append("A new comment was added to your post: ").append(blog.getTitle()).append("\n\n");
        body.append("Comment by: ").append(commenterUsername == null ? "Anonymous" : commenterUsername).append("\n\n");
        body.append("Comment:\n").append(commentText == null ? "" : commentText).append("\n\n");

        if (frontendUrl != null && !frontendUrl.isBlank()) {
            // assumes frontend has a route like /blog/{blogId} — update path if your frontend differs
            body.append("View it here: ").append(frontendUrl);
            if (!frontendUrl.endsWith("/")) body.append("/");
            body.append("blog/").append(blogId).append("\n\n");
        }

        body.append("—\nThis is an automated notification from HacktoberBlog.");

        // send email (uses your existing EmailService)
        try {
            emailService.sendEmail(ownerEmail, subject, body.toString());
            System.out.println("Notification sent to " + ownerEmail);
        } catch (Exception ex) {
            // swallow errors — do not break comment creation
            System.err.println("Failed to send comment notification: " + ex.getMessage());
        }
    }
}
