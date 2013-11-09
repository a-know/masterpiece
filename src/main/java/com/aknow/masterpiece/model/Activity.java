package com.aknow.masterpiece.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private Date activityDate;

    /*
     * activity code is :
     *   0 : nothing
     *   1 : post new item
     */
    @Attribute(unindexed = true)
    private String activityCode;

    @Attribute(lob = true, unindexed = true)
    private HashMap<Object,Object> activityInfo;

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

    public Date getActivityDate() {
        return this.activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    /**
     * Returns the activityCode.
     *
     * @return the activityCode
     */
    public String getActivityCode() {
        return this.activityCode;
    }

    /**
     * Sets the activityCode.
     *
     * @param activityCode
     *            the vactivityCodeersion
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * Returns the activityInfo.
     *
     * @return the activityInfo
     */
    public HashMap<Object, Object> getActivityInfo() {
        return this.activityInfo;
    }

    /**
     * Sets the activityInfo.
     *
     * @param activityInfo
     *            the activityInfo
     */
    public void setActivityInfo(HashMap<Object, Object> activityInfo) {
        this.activityInfo = activityInfo;
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
        Activity other = (Activity) obj;
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
