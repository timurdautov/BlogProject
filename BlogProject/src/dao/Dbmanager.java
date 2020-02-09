package dao;

import models.BlogPost;
import models.Comment;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dbmanager {


    public Connection connection;
    public Dbmanager(){}

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //launching the Java MySQL driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_project?useUnicode=true&serverTimezone=UTC", "root", "");//db name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User auth(String email, String password){

        ArrayList<User> users = new ArrayList<User>();

        User userReturn = null;

        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users_table");
            ResultSet res = st.executeQuery();

            while(res.next()){
                int id = res.getInt("id");
                String eMail = res.getString("email");
                String passWord = res.getString("password");
                String fullName = res.getString("full_name");
                users.add(new User(id, eMail, passWord, fullName));
            }

            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(User u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                userReturn = u;
            }
        }

        return userReturn;

    }

    public boolean register(String email, String password, String fullname){

        ArrayList<User> users = new ArrayList<User>();
        boolean success = true;

        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users_table");
            ResultSet res = st.executeQuery();

            while(res.next()){
                int id = res.getInt("id");
                String eMail = res.getString("email");
                String passWord = res.getString("password");
                String fullName = res.getString("full_name");
                users.add(new User(id, eMail, passWord, fullName));
            }

            st.close();

            for(User u : users){
                if(u.getEmail().equals(email)){
                    success = false;
                }
            }

            if(success){
                PreparedStatement stSecond = connection.prepareStatement("INSERT INTO users_table(`id`, `email`, `password`, `full_name`) VALUES (null, ?, ?, ?)");
                stSecond.setString(1, email);
                stSecond.setString(2, password);
                stSecond.setString(3, fullname);
                stSecond.executeUpdate();
                stSecond.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return success;
    }

    public void addPost(BlogPost post){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO posts_table(`id`, `user_id`, `title`, `content`, `post_date`) VALUES (null, ?, ?, ?, ?)");
            st.setInt(1, post.getUserId());
            st.setString(2, post.getTitle());
            st.setString(3, post.getText());
            st.setString(4, post.getDate());
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<BlogPost> getPosts(int userId){
        ArrayList<BlogPost> posts = new ArrayList<BlogPost>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM posts_table WHERE user_id = " + userId);
            ResultSet res = st.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                int user_Id = res.getInt("user_id");
                String title = res.getString("title");
                String text = res.getString("content");
                String date = res.getString("post_date");
                BlogPost temp = new BlogPost(id, user_Id, title, text, date);
                ArrayList<Comment> tempComments = getCommentsArray(id);
                temp.setComments(tempComments);
                posts.add(temp);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return posts;

    }

    public ArrayList<User> getUsers(){

        ArrayList<User> users = new ArrayList<User>();

        try{

            PreparedStatement st = connection.prepareStatement("SELECT * FROM users_table");
            ResultSet res = st.executeQuery();

            while(res.next()){
                int id = res.getInt("id");
                String eMail = res.getString("email");
                String passWord = res.getString("password");
                String fullName = res.getString("full_name");
                users.add(new User(id, eMail, passWord, fullName));
            }

            st.close();


        }catch(Exception e){
            e.printStackTrace();
        }

        return users;

    }

    public User getUserById(int id){

        User u = null;

        try{

            PreparedStatement st = connection.prepareStatement("SELECT * FROM users_table WHERE id = " + id);
            ResultSet res = st.executeQuery();
            while(res.next()){
                int userId = res.getInt("id");
                String eMail = res.getString("email");
                String passWord = res.getString("password");
                String fullName = res.getString("full_name");
                u = new User(userId, eMail, passWord, fullName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return u;

    }

    public BlogPost getPostById(int id){
        BlogPost p = null;
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM posts_table WHERE id = " + id);
            ResultSet res = st.executeQuery();
            while(res.next()){
                int post_id = res.getInt("id");
                int user_Id = res.getInt("user_id");
                String title = res.getString("title");
                String text = res.getString("content");
                String date = res.getString("post_date");
                p = new BlogPost(post_id, user_Id, title, text, date);
                p.setComments(getCommentsArray(id));
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return p;

    }

    public void editPost(int id, String title, String text){
        try{

            PreparedStatement st = connection.prepareStatement("UPDATE posts_table SET title = '" + title + "', content = '" + text + "' WHERE id = '"+ id +"'");
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editUserName(int id, String userName){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE users_table SET full_name = '" + userName + "' WHERE id = " + id);
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editPassword(int id, String password){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE users_table SET password = '" + password + "' WHERE id = " + id);
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Comment> getCommentsArray(int post_id_input){

        ArrayList<Comment> comments = new ArrayList<Comment>();

        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM comments_table WHERE post_id = " + post_id_input);
            ResultSet res = st.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                int user_id = res.getInt("user_id");
                int post_id = res.getInt("post_id");
                String content = res.getString("content");
                String post_date = res.getString("post_date");
                comments.add(new Comment(id, user_id, post_id, content, post_date));
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return comments;
    }

    public void deletePost(int id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM posts_table WHERE id = " + id);
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addComment(Comment comment){

        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO comments_table(`id`, `user_id`, `post_id`, `content`, `post_date`) VALUES (null, ?, ?, ?, ?)");
            st.setInt(1, comment.getUser_id());
            st.setInt(2, comment.getPost_id());
            st.setString(3, comment.getContent());
            st.setString(4, comment.getPost_date());
            st.executeUpdate();
            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<BlogPost> getPostsAll(){
        ArrayList<BlogPost> posts = new ArrayList<BlogPost>();

        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM posts_table");
            ResultSet res = st.executeQuery();
            while(res.next()){
                int post_id = res.getInt("id");
                int user_Id = res.getInt("user_id");
                String title = res.getString("title");
                String text = res.getString("content");
                String date = res.getString("post_date");
                BlogPost temp = new BlogPost(post_id, user_Id, title, text, date);
                ArrayList<Comment> tempComments = getCommentsArray(user_Id);
                temp.setComments(tempComments);

                posts.add(temp);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return posts;

    }

    public void deleteComment(int id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM comments_table WHERE id = " + id);
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
