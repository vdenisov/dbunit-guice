package org.plukh.dbunitguice.dao;

import org.apache.ibatis.annotations.Param;
import org.plukh.dbunitguice.dao.entities.Category;
import org.plukh.dbunitguice.dao.entities.Keyword;
import org.plukh.dbunitguice.dao.entities.TopicWithKeywords;

import java.util.List;
import java.util.Set;

public interface TopicMapper {
    List<Category> getCategories(@Param("environmentId") String environmentId);

    List<TopicWithKeywords> getTopicsWithKeywords(@Param("environmentId") String environmentId);
    Set<Keyword> getKeywords(@Param("environmentId") String environmentId);
    void deleteKeywords(@Param("topicId") int topicId);
    void createKeywords(@Param("topicId") int topicId, @Param("keywords") Set<String> keywords);

    void deleteCategoryTopicsByTopic(@Param("topicId") int topicId);
    void createCategoryTopicsByTopic(@Param("topicId") int topicId, @Param("categories") List<Category> categories);
}
