package com.aknow.masterpiece.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String name;
    private String categoryCode;
    @Attribute(lob = true, unindexed = true)
    private String comment;
    @Attribute(unindexed = true)
    private String url;
    private Integer starCount;
    @Attribute(unindexed = true)
    private String starString;
    private Date postDate;
    @Attribute(unindexed = true)
    private BlobKey blobKey;
    @Attribute(unindexed = true)
    private String imageUrl;
    private String loginID;

    private ModelRef<User> userRef = new ModelRef<User>(User.class);

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStarCount() {
        return this.starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public String getStarString() {
        return this.starString;
    }

    public void setStarString(String starString) {
        this.starString = starString;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public BlobKey getBlobKey() {
        return this.blobKey;
    }

    public void setBlobKey(BlobKey blobKey) {
        this.blobKey = blobKey;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public ModelRef<User> getUserRef() {
        return this.userRef;
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
        Item other = (Item) obj;
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
