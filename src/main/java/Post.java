public class Post {

    private String title;
    private String post;
    private long id;

    public Post (String title, String post){
        this.title = title;
        this.post = post;
    }

    public Post (String title, String post, long id){
        this.title = title;
        this.post = post;
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }
}

