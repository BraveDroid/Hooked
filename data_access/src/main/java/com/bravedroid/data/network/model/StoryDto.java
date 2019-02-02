
package com.bravedroid.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryDto {

    @SerializedName("storyDescription")
    @Expose
    private String storyDescription;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("loveCount")
    @Expose
    private Integer loveCount;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("coverImageLoRes")
    @Expose
    private CoverImageLoRes coverImageLoRes;
    @SerializedName("wordCount")
    @Expose
    private Integer wordCount;
    @SerializedName("editorsChoice")
    @Expose
    private Boolean editorsChoice;
    @SerializedName("seriesTitle")
    @Expose
    private String seriesTitle;
    @SerializedName("coverImageThumbnailFile")
    @Expose
    private CoverImageThumbnailFile coverImageThumbnailFile;
    @SerializedName("episodeIndex")
    @Expose
    private Integer episodeIndex;
    @SerializedName("coverImageFile")
    @Expose
    private CoverImageFile coverImageFile;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("readCount")
    @Expose
    private Integer readCount;
    @SerializedName("episodeCount")
    @Expose
    private Integer episodeCount;
    @SerializedName("seriesIdentifier")
    @Expose
    private String seriesIdentifier;
    @SerializedName("commentCount")
    @Expose
    private Integer commentCount;

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public CoverImageLoRes getCoverImageLoRes() {
        return coverImageLoRes;
    }

    public void setCoverImageLoRes(CoverImageLoRes coverImageLoRes) {
        this.coverImageLoRes = coverImageLoRes;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Boolean getEditorsChoice() {
        return editorsChoice;
    }

    public void setEditorsChoice(Boolean editorsChoice) {
        this.editorsChoice = editorsChoice;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public CoverImageThumbnailFile getCoverImageThumbnailFile() {
        return coverImageThumbnailFile;
    }

    public void setCoverImageThumbnailFile(CoverImageThumbnailFile coverImageThumbnailFile) {
        this.coverImageThumbnailFile = coverImageThumbnailFile;
    }

    public Integer getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(Integer episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public CoverImageFile getCoverImageFile() {
        return coverImageFile;
    }

    public void setCoverImageFile(CoverImageFile coverImageFile) {
        this.coverImageFile = coverImageFile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getSeriesIdentifier() {
        return seriesIdentifier;
    }

    public void setSeriesIdentifier(String seriesIdentifier) {
        this.seriesIdentifier = seriesIdentifier;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

}
