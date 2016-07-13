package org.plukh.dbunitguice.dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.plukh.dbunitguice.dao.entities.Category;
import org.plukh.dbunitguice.dao.entities.Keyword;
import org.plukh.dbunitguice.dao.entities.TopicWithKeywords;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class MyBatisTopicDAOImpl implements TopicDAO {
    public static final Logger log = LogManager.getLogger(MyBatisTopicDAOImpl.class);

    private final TopicMapper topicMapper;

    @Inject
    public MyBatisTopicDAOImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Category> getCategories(String environmentId) {
        return topicMapper.getCategories(environmentId);
    }

    @Override
    public List<TopicWithKeywords> getTopicsWithKeywords(String environmentId) {
        List<TopicWithKeywords> topics = topicMapper.getTopicsWithKeywords(environmentId);
        Set<Keyword> keywords = topicMapper.getKeywords(environmentId);

        //Prepare keyword sets via map
        SetMultimap<Integer, String> keywordSet = HashMultimap.create();
        keywords.forEach(keyword -> keywordSet.put(keyword.getTopicId(), keyword.getKeyword()));
        topics.forEach(topic -> topic.setKeywords(keywordSet.get(topic.getId())));

        return topics;
    }

    @Override
    public void updateTopicSetKeywords(int topicId, Set<String> keywords, DateTime modificationDate) {
        //Delete old, insert new keywords
        topicMapper.deleteKeywords(topicId);
        if (keywords != null && !keywords.isEmpty()) {
            topicMapper.createKeywords(topicId, keywords);
        }
    }

    @Override
    public void updateTopicSetCategories(int topicId, List<Category> categories) {
        topicMapper.deleteCategoryTopicsByTopic(topicId);
        if (categories != null && !categories.isEmpty()) {
            topicMapper.createCategoryTopicsByTopic(topicId, categories);
        }
    }
}
