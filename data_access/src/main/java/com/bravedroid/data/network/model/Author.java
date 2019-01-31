
package com.bravedroid.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("writeCount")
    @Expose
    private Integer writeCount;
    @SerializedName("loveCount")
    @Expose
    private Integer loveCount;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("bookmarkCount")
    @Expose
    private Integer bookmarkCount;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("followingCount")
    @Expose
    private Integer followingCount;
    @SerializedName("profilePictureMedium")
    @Expose
    private ProfilePictureMedium profilePictureMedium;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("profilePictureSmall")
    @Expose
    private ProfilePictureSmall profilePictureSmall;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("followerCount")
    @Expose
    private Integer followerCount;
    @SerializedName("readCount")
    @Expose
    private Integer readCount;
    @SerializedName("firstName")
    @Expose
    private String firstName;

    public Integer getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(Integer writeCount) {
        this.writeCount = writeCount;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(Integer bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public ProfilePictureMedium getProfilePictureMedium() {
        return profilePictureMedium;
    }

    public void setProfilePictureMedium(ProfilePictureMedium profilePictureMedium) {
        this.profilePictureMedium = profilePictureMedium;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ProfilePictureSmall getProfilePictureSmall() {
        return profilePictureSmall;
    }

    public void setProfilePictureSmall(ProfilePictureSmall profilePictureSmall) {
        this.profilePictureSmall = profilePictureSmall;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
