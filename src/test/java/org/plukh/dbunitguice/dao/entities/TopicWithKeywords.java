package org.plukh.dbunitguice.dao.entities;

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Set;

public class TopicWithKeywords extends Topic {
    protected Set<String> keywords;

    public TopicWithKeywords() {
        super();
    }

    public TopicWithKeywords withId(final int id) {
        this.id = id;
        return this;
    }

    public TopicWithKeywords withEnvironmentId(final String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public TopicWithKeywords withUpdateDate(final DateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public TopicWithKeywords withName(final String name) {
        this.name = name;
        return this;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public TopicWithKeywords withKeywords(final Set<String> keywords) {
        this.keywords = keywords;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicWithKeywords)) return false;
        if (!super.equals(o)) return false;
        TopicWithKeywords keywords1 = (TopicWithKeywords) o;
        return Objects.equals(keywords, keywords1.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), keywords);
    }
}
