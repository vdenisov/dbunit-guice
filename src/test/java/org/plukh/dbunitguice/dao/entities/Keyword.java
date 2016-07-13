package org.plukh.dbunitguice.dao.entities;

import java.util.Objects;

public final class Keyword {
    private int topicId;
    private String keyword;

    public Keyword() {
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Keyword)) return false;
        Keyword keyword1 = (Keyword) o;
        return Objects.equals(topicId, keyword1.topicId) &&
                Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, keyword);
    }
}
