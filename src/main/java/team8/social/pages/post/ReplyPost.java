package team8.social.pages.post;

import team8.social.PageHandler;
import team8.social.Post;
import team8.social.Session;

import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.get;
import static spark.Spark.post;

/*****
 * Object: ReplyPost
 * Description: 
 * Author: William Graham
 * Modified: April 30, 2018
 *****/

public class ReplyPost implements PageHandler{
    private static String reply;
    
    public ReplyPost(){
        try{
            reply = new String(Files.readAllBytes(
                    Paths.get(getClass().getResource("/public/html/postViewReply.html").toURI())
            ));
        }catch(Exception e){}
    }
    
    public void pages(){
        get("/replypost", (req, res) -> {
            if (!Session.validate(req.session().id(), req.session().attribute("UserID"))) {
                res.redirect("/login");
                return null;
            }
        
            return reply;
        });
    
        post("/replypost", (req, res) -> {
            if (!Session.validate(req.session().id(), req.session().attribute("UserID"))) {
                res.redirect("/login");
                return null;
            }
        
            Post p = Post.createPost(req.session().attribute("UserID"), req.queryParams("replycontent"), "Reply",
                    Integer.parseInt(req.queryParams("postID")));
        
            if (p == null) {
                res.redirect("/replypost?postID=" + Integer.parseInt(req.queryParams("postID")));
            } else {
                res.redirect("/viewpost?postID=" + Integer.parseInt(req.queryParams("postID")));
            }
        
            return null;
        });
    }
}
