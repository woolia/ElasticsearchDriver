package co.kr.iwaz.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import co.kr.iwaz.document.hits.Source;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElasticResponse {

    private String totalCategory;
    private String contentsCategory;
    private String subjectCategory;
    private String serviceCategory;
    private String contentsTitle;
    private String contentsThumbnailLink;
    private String contentsLink;
    private String questionTitle;
    private String questionSubject;
    private String questionGrade;
    private String questionDifficulty;
    private String textbookTitle;
    private String textbookContents;
    private Integer textbookStartPage;
    private Integer textbookEndPage;
    private String textbookThumbnailLink;
    private String textbookLink;
    private String serviceCenterTitle;
    private String serviceCenterContents;
    private String classLaboratoryTitle;
    private String classLaboratoryThumbnailLink;
    private String classLaboratoryLink;

    public ElasticResponse() {
    }


    public String getTotalCategory() {
        return totalCategory;
    }

    public void setTotalCategory(String totalCategory) {
        this.totalCategory = totalCategory;
    }

    public String getContentsCategory() {
        return contentsCategory;
    }

    public void setContentsCategory(String contentsCategory) {
        this.contentsCategory = contentsCategory;
    }

    public String getSubjectCategory() {
        return subjectCategory;
    }

    public void setSubjectCategory(String subjectCategory) {
        this.subjectCategory = subjectCategory;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getContentsTitle() {
        return contentsTitle;
    }

    public void setContentsTitle(String contentsTitle) {
        this.contentsTitle = contentsTitle;
    }

    public String getContentsThumbnailLink() {
        return contentsThumbnailLink;
    }

    public void setContentsThumbnailLink(String contentsThumbnailLink) {
        this.contentsThumbnailLink = contentsThumbnailLink;
    }

    public String getContentsLink() {
        return contentsLink;
    }

    public void setContentsLink(String contentsLink) {
        this.contentsLink = contentsLink;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    public String getQuestionGrade() {
        return questionGrade;
    }

    public void setQuestionGrade(String questionGrade) {
        this.questionGrade = questionGrade;
    }

    public String getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public String getTextbookTitle() {
        return textbookTitle;
    }

    public void setTextbookTitle(String textbookTitle) {
        this.textbookTitle = textbookTitle;
    }

    public String getTextbookContents() {
        return textbookContents;
    }

    public void setTextbookContents(String textbookContents) {
        this.textbookContents = textbookContents;
    }

    public Integer getTextbookStartPage() {
        return textbookStartPage;
    }

    public void setTextbookStartPage(Integer textbookStartPage) {
        this.textbookStartPage = textbookStartPage;
    }

    public Integer getTextbookEndPage() {
        return textbookEndPage;
    }

    public void setTextbookEndPage(Integer textbookEndPage) {
        this.textbookEndPage = textbookEndPage;
    }

    public String getTextbookThumbnailLink() {
        return textbookThumbnailLink;
    }

    public void setTextbookThumbnailLink(String textbookThumbnailLink) {
        this.textbookThumbnailLink = textbookThumbnailLink;
    }

    public String getTextbookLink() {
        return textbookLink;
    }

    public void setTextbookLink(String textbookLink) {
        this.textbookLink = textbookLink;
    }

    public String getServiceCenterTitle() {
        return serviceCenterTitle;
    }

    public void setServiceCenterTitle(String serviceCenterTitle) {
        this.serviceCenterTitle = serviceCenterTitle;
    }

    public String getServiceCenterContents() {
        return serviceCenterContents;
    }

    public void setServiceCenterContents(String serviceCenterContents) {
        this.serviceCenterContents = serviceCenterContents;
    }

    public String getClassLaboratoryTitle() {
        return classLaboratoryTitle;
    }

    public void setClassLaboratoryTitle(String classLaboratoryTitle) {
        this.classLaboratoryTitle = classLaboratoryTitle;
    }

    public String getClassLaboratoryThumbnailLink() {
        return classLaboratoryThumbnailLink;
    }

    public void setClassLaboratoryThumbnailLink(String classLaboratoryThumbnailLink) {
        this.classLaboratoryThumbnailLink = classLaboratoryThumbnailLink;
    }

    public String getClassLaboratoryLink() {
        return classLaboratoryLink;
    }

    public void setClassLaboratoryLink(String classLaboratoryLink) {
        this.classLaboratoryLink = classLaboratoryLink;
    }

    public Source createSource(String key, long docCount , String categoryName){
        Source source = new Source();
        source.setCategory(categoryName);
        source.setClassLaboratory(this.classLaboratoryTitle, this.classLaboratoryThumbnailLink, this.classLaboratoryLink);
        source.setContents(this.contentsTitle, this.contentsThumbnailLink, this.contentsLink);
        source.setQuestion(this.questionTitle, this.questionSubject, this.questionGrade, this.questionDifficulty);
        source.setServiceCenter(this.serviceCenterTitle, this.serviceCenterContents);
        source.setTextbook(this.textbookTitle, this.textbookContents, this.textbookStartPage, this.textbookEndPage, this.textbookThumbnailLink, this
                .textbookLink);
        return source;
    }

    @Override
    public String toString() {
        return "Result{" +
                "totalCategory='" + totalCategory + '\'' +
                ", contentsCategory='" + contentsCategory + '\'' +
                ", subjectCategory='" + subjectCategory + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", contentsTitle='" + contentsTitle + '\'' +
                ", contentsThumbnailLink='" + contentsThumbnailLink + '\'' +
                ", contentsLink='" + contentsLink + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionSubject='" + questionSubject + '\'' +
                ", questionGrade='" + questionGrade + '\'' +
                ", questionDifficulty='" + questionDifficulty + '\'' +
                ", textBookTitle='" + textbookTitle + '\'' +
                ", textBookContents='" + textbookContents + '\'' +
                ", textBookStartPage=" + textbookStartPage +
                ", textBookEndPage=" + textbookEndPage +
                ", textBookThumbnailLink='" + textbookThumbnailLink + '\'' +
                ", textBookLink='" + textbookLink + '\'' +
                ", serviceCenterTitle='" + serviceCenterTitle + '\'' +
                ", serviceCenterContents='" + serviceCenterContents + '\'' +
                ", classLaboratoryTitle='" + classLaboratoryTitle + '\'' +
                ", classLaboratoryThumbnailLink='" + classLaboratoryThumbnailLink + '\'' +
                ", classLaboratoryLink='" + classLaboratoryLink + '\'' +
                '}';
    }
}
