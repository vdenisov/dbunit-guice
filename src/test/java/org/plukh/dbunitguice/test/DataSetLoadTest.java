package org.plukh.dbunitguice.test;

import org.junit.Test;
import org.mybatis.guice.transactional.Transactional;
import org.plukh.dbunitguice.dao.TopicDAO;
import org.plukh.dbunitguice.dao.entities.TopicWithKeywords;
import org.plukh.dbunitguice.dbunit.DataSets;
import org.plukh.dbunitguice.dbunit.DbUnitTest;
import org.plukh.dbunitguice.guice.GuiceModules;
import org.plukh.dbunitguice.guice.MyBatisDAOModule;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataSets({"environments.xml", "topics.xml"})
@GuiceModules(MyBatisDAOModule.class)
public class DataSetLoadTest extends DbUnitTest {
    @Inject
    protected TopicDAO topicDAO;

    @Test
    @Transactional
    public void testDataSetsLoadedFromTestClassClassloader() throws Exception {
        List<TopicWithKeywords> topics = topicDAO.getTopicsWithKeywords("staging");
        assertEquals(1, topics.size());
    }

}
