
package com.revyouviddb;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Project: RevYou --  
 * Sub-Project: DBControls For Video --  
 * Class: VideoDb --  
 * Description: Entity class for relational database row
 * @author Otto O. Legon 
 * @version 2.0
 * 
 */
@Entity
@Table(name = "video_db", catalog = "dd0cstm5kg94ae", schema = "public")
@NamedQueries({
    @NamedQuery(name = "VideoDb.findAll", query = "SELECT v FROM VideoDb v"),
    @NamedQuery(name = "VideoDb.findById", query = "SELECT v FROM VideoDb v WHERE v.id = :id"),
    @NamedQuery(name = "VideoDb.findByOwner", query = "SELECT v FROM VideoDb v WHERE v.owner = :owner"),
    @NamedQuery(name = "VideoDb.findByVideoUrl", query = "SELECT v FROM VideoDb v WHERE v.videoUrl = :videoUrl"),
    @NamedQuery(name = "VideoDb.findByVisibility", query = "SELECT v FROM VideoDb v WHERE v.visibility = :visibility"),
    @NamedQuery(name = "VideoDb.findByDescription", query = "SELECT v FROM VideoDb v WHERE v.description = :description"),
    @NamedQuery(name = "VideoDb.findByVideoName", query = "SELECT v FROM VideoDb v WHERE v.videoName = :videoName"),
    @NamedQuery(name = "VideoDb.findByTags", query = "SELECT v FROM VideoDb v WHERE v.tags = :tags"),
    @NamedQuery(name = "VideoDb.findByCommentsUrl", query = "SELECT v FROM VideoDb v WHERE v.commentsUrl = :commentsUrl")
    })
public class VideoDb implements Serializable {
    /**
     * The Change Support variable for entity object
     */
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    /**
     * The Serial Version UID of the class
     */
    private static final long serialVersionUID = 1L;
    /**
     * Primary key for the entity 
     * Will be binded as a column representing entity ID
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    /**
     * Field in database video table representing owner of the video
     * Will be binded as a column representing Owner 
     */
    @Basic(optional = false)
    @Column(name = "owner")
    private String owner;
    /**
     * Field in database video table representing URL location of the video
     * Will be binded as a column representing URL
     */
    @Basic(optional = false)
    @Column(name = "video_url")
    private String videoUrl;
    /**
     * Field in database video table representing visibility of the video
     * Will be binded as a column representing Visibility
     */
    @Basic(optional = false)
    @Column(name = "visibility")
    private boolean visibility;
    /**
     * Field in database video table representing description of the video
     * Will be binded as a column representing Description 
     */
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    /**
     * Field in database video table representing name of the video
     * Will be binded as a column representing Video Name
     */
    @Basic(optional = false)
    @Column(name = "video_name")
    private String videoName;
    /**
     * Field in database video table representing tags of the video
     * Will be binded as a column representing Tags
     */
    @Basic(optional = true)
    @Column(name = "tags")
    private String tags;
    /**
     * Field in database video table representing URL location of the video comments file
     * Will be binded as a column representing Comments
     */
    @Basic(optional = true)
    @Column(name = "comments_url")
    private String commentsUrl;

    /**
     * Video object entity constructor
     */
    public VideoDb() {
    }

    /**
     * Video object entity constructor.
     * Requires primary key to be predefined.
     * @param id a short to be set as primary key of video
     * 
     */
    public VideoDb(Short id) {
        this.id = id;
    }

    /**
     * Video object entity constructor.
     * Requires all column fields to be defined.
     * @param id a short to be set as primary key of video 
     * @param owner a String to be set as owner of video
     * @param videoUrl a String to be set as Url of video
     * @param visibility a boolean to be set as visibility of video
     * @param description a String to be set as description of video
     * @param videoName a String to be set as name of video
     * @param tags a String to be set as tags of video
     * @param commentsUrl a String to be set as Url of comments file for video
     * 
     */
    public VideoDb(Short id, String owner, String videoUrl, boolean visibility, String description, String videoName, String tags, String commentsUrl) {
        this.id = id;
        this.owner = owner;
        this.videoUrl = videoUrl;
        this.visibility = visibility;
        this.description = description;
        this.videoName = videoName;
        this.tags = tags;
        this.commentsUrl = commentsUrl;
    }

    /**
     * @return id - a short representing primary key of video
     */
    public Short getId() {
        return id;
    }

    /**
     * @param id a short to be set as primary key of video
     */
    public void setId(Short id) {
        Short oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    /**
     * @return owner - a String representing owner of video
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner a String to be set as owner of video
     */
    public void setOwner(String owner) {
        String oldOwner = this.owner;
        this.owner = owner;
        changeSupport.firePropertyChange("owner", oldOwner, owner);
    }

    /**
     * @return videoUrl - a String representing Url of video
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl a String to be set as Url of video
     */
    public void setVideoUrl(String videoUrl) {
        String oldVideoUrl = this.videoUrl;
        this.videoUrl = videoUrl;
        changeSupport.firePropertyChange("videoUrl", oldVideoUrl, videoUrl);
    }

    /**
     * @return visibility - a boolean representing visibility of video
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * @param visibility a boolean to be set as visibility of video
     */
    public void setVisibility(boolean visibility) {
        boolean oldVisibility = this.visibility;
        this.visibility = visibility;
        changeSupport.firePropertyChange("visibility", oldVisibility, visibility);
    }

    /**
     * @return description - a String representing description of video
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description a String to be set as description of video
     */
    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }
    
    /**
     * @return videoName - a String representing name of video
     */
    public String getVideoName() {
        return videoName;
    }

    /**
     * @param videoName a String to be set as name of video
     */
    public void setVideoName(String videoName) {
        String oldDescription = this.videoName;
        this.videoName = videoName;
        changeSupport.firePropertyChange("videoName", oldDescription, videoName);
    }
    
    /**
     * @return tags - a String representing tags of video
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags a String to be set as tags of video
     */
    public void setTags(String tags) {
        String oldDescription = this.tags;
        this.tags = tags;
        changeSupport.firePropertyChange("tags", oldDescription, tags);
    }
    
    /**
     * @return commentsUrl - a String representing Url of comments file for video
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * @param commentsUrl a String to be set as Url of comments file for video
     */
    public void setCommentsUrl(String commentsUrl) {
        String oldDescription = this.commentsUrl;
        this.commentsUrl = commentsUrl;
        changeSupport.firePropertyChange("commentsUrl", oldDescription, commentsUrl);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoDb)) {
            return false;
        }
        VideoDb other = (VideoDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return videoName;
    }

    /**
     * @param listener 
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

