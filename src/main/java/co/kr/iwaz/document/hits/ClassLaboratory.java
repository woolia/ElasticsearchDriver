package co.kr.iwaz.document.hits;

public class ClassLaboratory {

    private String title;
    private String thumbnailLink;
    private String link;

    public ClassLaboratory(String title, String thumbnailLink, String link) {
        this.title = title;
        this.thumbnailLink = thumbnailLink;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ClassLaboratory{" +
                "title='" + title + '\'' +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
