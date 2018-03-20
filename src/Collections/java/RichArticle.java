package Collections.java;

import java.util.Date;
import java.util.List;

/**
 * Created by prefert on 2018/3/20.
 */
public class RichArticle {
        private String title;
        private String author;
        private List<String> tags;
        private Date createTime;
        private Boolean isPinned;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getPinned() {
        return isPinned;
    }

    public void setPinned(Boolean pinned) {
        isPinned = pinned;
    }

    public RichArticle(String title, String author, List<String> tags, Date createTime, Boolean isPinned) {
        this.title = title;
        this.author = author;
        this.tags = tags;
        this.createTime = createTime;
        this.isPinned = isPinned;
    }

    @Override
    public String toString() {
        return "RichArticle{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                ", createTime=" + createTime +
                ", isPinned=" + isPinned +
                '}';
    }
}
