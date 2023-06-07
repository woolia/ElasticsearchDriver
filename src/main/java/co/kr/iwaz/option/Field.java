package co.kr.iwaz.option;

public enum Field {

    TOTAL_CATEGORY("totalCategory"),
    CONTENTS_CATEGORY("contentsCategory"),
    CONTENTS_TITLE("contentsTitle"),
    CONTENTS_THUMBNAIL_LINK("contentsThumbnailLink"),
    CONTENTS_LINK("contentsLink"),
    QUESTION_TITLE("questionTitle"),
    QUESTION_SUBJECT("questionSubject"),
    QUESTION_GRADE("questionGrade"),
    QUESTION_DIFFICULTY("questionDifficulty"),
    SUBJECT_CATEGORY("subjectCategory"),
    TEXTBOOK_TITLE("textbookTitle"),
    TEXTBOOK_CONTENTS("textbookContents"),
    TEXTBOOK_START_PAGE("textbookStartPage"),
    TEXTBOOK_END_PAGE("textbookEndPage"),
    TEXTBOOK_THUMBNAIL_LINK("textbookThumbnailLink"),
    TEXTBOOK_LINK("textbookLink"),
    SERVICE_CATEGORY("serviceCategory"),
    SERVICE_CENTER_TITLE("serviceCenterTitle"),
    SERVICE_CENTER_CONTENTS("serviceCenterContents"),
    CLASS_LABORATORY_TITLE("classLaboratoryTitle"),
    CLASS_LABORATORY_THUMBNAIL_LINK("classLaboratoryThumbnailLink"),
    CLASS_LABORATORY_LINK("classLaboratoryLink"),
    SEARCH("search"),
    ;

    private final String label;

    Field(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public String label(double boost) {
        return label +":"+String.valueOf(boost);
    }

}
