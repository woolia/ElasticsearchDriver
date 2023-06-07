package co.kr.iwaz.document.hits;

public class Source {

    private String category;
    private ClassLaboratory classLaboratory;
    private Contents contents;
    private Question question;
    private ServiceCenter serviceCenter;
    private Textbook textbook;

    public Source() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ClassLaboratory getClassLaboratory() {
        return classLaboratory;
    }

    public void setClassLaboratory(String classLaboratoryTitle, String classLaboratoryThumbnailLink, String classLaboratoryLink) {
        this.classLaboratory = new ClassLaboratory(classLaboratoryTitle, classLaboratoryThumbnailLink, classLaboratoryLink);
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(String contentsTitle, String contentsThumbnailLink, String contentsLink) {
        this.contents = new Contents(contentsTitle, contentsThumbnailLink, contentsLink);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(String questionTitle, String questionSubject, String questionGrade, String questionDifficulty) {
        this.question = new Question(questionTitle, questionSubject, questionGrade, questionDifficulty);
    }

    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenterTitle, String serviceCenterContents) {
        this.serviceCenter = new ServiceCenter(serviceCenterTitle, serviceCenterContents);
    }

    public Textbook getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbookTitle, String textbookContents, Integer textbookStartPage, Integer textbookEndPage, String textbookThumbnailLink, String textbookLink) {
        this.textbook = new Textbook(textbookTitle, textbookContents, textbookStartPage, textbookEndPage, textbookThumbnailLink, textbookLink);
    }

    @Override
    public String toString() {
        return "Source{" +
                "category=" + category +
                ", classLaboratory=" + classLaboratory +
                ", contents=" + contents +
                ", question=" + question +
                ", serviceCenter=" + serviceCenter +
                ", textbook=" + textbook +
                '}';
    }
}
