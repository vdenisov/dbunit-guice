package org.plukh.dbunitguice.dao.entities;

import org.joda.time.DateTime;

import java.util.Objects;

public class Topic {

    protected int id;
    protected String environmentId;
    protected DateTime updateDate;
    protected String name;

    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic withId(final int id) {
        this.id = id;
        return this;
    }

    public Topic withEnvironmentId(final String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public Topic withUpdateDate(final DateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public Topic withName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) &&
                Objects.equals(environmentId, topic.environmentId) &&
                Objects.equals(updateDate, topic.updateDate) &&
                Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environmentId, updateDate, name);
    }
}
