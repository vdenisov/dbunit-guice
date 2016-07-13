package org.plukh.dbunitguice.dao.entities;

import java.util.Objects;

public class CategoryTopic {
    private int categoryId;
    private int topicId;

    public CategoryTopic() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public CategoryTopic withCategoryId(final int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public CategoryTopic withTopicId(final int topicId) {
        this.topicId = topicId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryTopic)) return false;
        CategoryTopic topic = (CategoryTopic) o;
        return Objects.equals(categoryId, topic.categoryId) &&
                Objects.equals(topicId, topic.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, topicId);
    }
}
