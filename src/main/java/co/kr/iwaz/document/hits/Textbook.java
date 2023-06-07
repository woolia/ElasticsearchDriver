package co.kr.iwaz.document.hits;

public class Textbook {

    private String title;
    private String contents;
    private Integer startPage;
    private Integer endPage;
    private String thumbnailLink;
    private String link;

    public Textbook(String title, String contents, Integer startPage, Integer endPage, String thumbnailLink, String link) {
        this.title = title;
        this.contents = contents;
        this.startPage = startPage;
        this.endPage = endPage;
        this.thumbnailLink = thumbnailLink;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
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
        return "Textbook{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
