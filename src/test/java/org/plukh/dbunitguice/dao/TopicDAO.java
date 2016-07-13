package org.plukh.dbunitguice.dao;

import org.joda.time.DateTime;
import org.plukh.dbunitguice.dao.entities.Category;
import org.plukh.dbunitguice.dao.entities.TopicWithKeywords;

import java.util.List;
import java.util.Set;


public interface TopicDAO {
    //Category methods
    List<Category> getCategories(String environmentId);

    //Topic methods
    List<TopicWithKeywords> getTopicsWithKeywords(String environmentId);
    void updateTopicSetKeywords(int topicId, Set<String> keywords, DateTime modificationDate);
    void updateTopicSetCategories(int topicId, List<Category> categories);
}
