
package com.bravedroid.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("ordinalInStory")
    @Expose
    private Integer ordinalInStory;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("mediaWidth")
    @Expose
    private Integer mediaWidth;
    @SerializedName("chargeable")
    @Expose
    private Boolean chargeable;
    @SerializedName("mediaHeight")
    @Expose
    private Integer mediaHeight;
    @SerializedName("isTypingIntroDuration")
    @Expose
    private Integer isTypingIntroDuration;

    public Integer getOrdinalInStory() {
        return ordinalInStory;
    }

    public void setOrdinalInStory(Integer ordinalInStory) {
        this.ordinalInStory = ordinalInStory;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getMediaWidth() {
        return mediaWidth;
    }

    public void setMediaWidth(Integer mediaWidth) {
        this.mediaWidth = mediaWidth;
    }

    public Boolean getChargeable() {
        return chargeable;
    }

    public void setChargeable(Boolean chargeable) {
        this.chargeable = chargeable;
    }

    public Integer getMediaHeight() {
        return mediaHeight;
    }

    public void setMediaHeight(Integer mediaHeight) {
        this.mediaHeight = mediaHeight;
    }

    public Integer getIsTypingIntroDuration() {
        return isTypingIntroDuration;
    }

    public void setIsTypingIntroDuration(Integer isTypingIntroDuration) {
        this.isTypingIntroDuration = isTypingIntroDuration;
    }

}
