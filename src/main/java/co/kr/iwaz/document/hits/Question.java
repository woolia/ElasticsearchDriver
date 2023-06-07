package co.kr.iwaz.document.hits;

public class Question {

    private String title;
    private String subject;
    private String grade;
    private String difficulty;

    public Question(String title, String subject, String grade, String difficulty) {
        this.title = title;
        this.subject = subject;
        this.grade = grade;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
