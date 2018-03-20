package Collections.java;

import java.util.Date;
import java.util.List;

/**
 * Created by prefert on 2018/3/18.
 * 文章类
 */
public class Article {

    private String title;
    private String author;
    private List<String> tags;

    Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}


