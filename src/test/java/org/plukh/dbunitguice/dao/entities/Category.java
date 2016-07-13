package org.plukh.dbunitguice.dao.entities;

import java.util.Objects;

public class Category {

    protected int id;
    protected String environmentId;
    protected Integer parentId;
    protected String name;

    public Category() {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category withId(final int id) {
        this.id = id;
        return this;
    }

    public Category withEnvironmentId(final String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public Category withParentId(final Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Category withName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(environmentId, category.environmentId) &&
                Objects.equals(parentId, category.parentId) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environmentId, parentId, name);
    }
}
