package com.aknow.masterpiece.model;

import java.io.Serializable;

import com.aknow.masterpiece.meta.ActivityMeta;
import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.meta.MessageMeta;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.Sort;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;

@Model(schemaVersion = 1)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String loginID;

    @Attribute(unindexed = true)
    private String password;
    private Integer postCount;
    private Integer starCount;
    @Attribute(unindexed = true)
    private String url;
    @Attribute(unindexed = true)
    private String introduction;
    @Attribute(unindexed = true)
    private BlobKey iconFileBlobKey;
    @Attribute(unindexed = true)
    private String imageUrl;
    @Attribute(unindexed = true)
    private String twitterAccessToken;
    @Attribute(unindexed = true)
    private String twitterAccessTokenSecret;

    @Attribute(persistent = false)
    private InverseModelListRef<Item, User> itemRefs =
        new InverseModelListRef<Item, User>(Item.class, ItemMeta.get().userRef.getName(),
        this, new Sort(ItemMeta.get().postDate.getName(), SortDirection.DESCENDING));

    @Attribute(persistent = false)
    private InverseModelListRef<Activity, User> activityRefs =
        new InverseModelListRef<Activity, User>(Activity.class, ActivityMeta.get().userRef.getName(),
        this, new Sort(ActivityMeta.get().activityDate.getName(), SortDirection.DESCENDING));

    @Attribute(persistent = false)
    private InverseModelListRef<Message, User> messageRefs =
        new InverseModelListRef<Message, User>(Message.class, MessageMeta.get().userRef.getName(),
        this, new Sort(MessageMeta.get().sendDate.getName(), SortDirection.DESCENDING));
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPostCount() {
        return this.postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public InverseModelListRef<Item, User> getItemRefs() {
        return this.itemRefs;
    }

    public InverseModelListRef<Activity, User> getActivityRefs() {
        return this.activityRefs;
    }

    public InverseModelListRef<Message, User> getMessageRefs() {
        return this.messageRefs;
    }

    public Integer getStarCount() {
        return this.starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BlobKey getIconFileBlobKey() {
        return this.iconFileBlobKey;
    }

    public void setIconFileBlobKey(BlobKey iconFileBlobKey) {
        this.iconFileBlobKey = iconFileBlobKey;
    }

    public String getTwitterAccessToken() {
        return this.twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterAccessTokenSecret() {
        return this.twitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (this.key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!this.key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
