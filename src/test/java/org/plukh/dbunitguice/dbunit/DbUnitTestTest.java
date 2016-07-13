package org.plukh.dbunitguice.dbunit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DataType;
import org.junit.Test;
import org.mybatis.guice.transactional.Transactional;
import org.plukh.dbunitguice.dao.TopicDAO;
import org.plukh.dbunitguice.dao.entities.Category;
import org.plukh.dbunitguice.dao.entities.TopicWithKeywords;
import org.plukh.dbunitguice.guice.GuiceModules;
import org.plukh.dbunitguice.guice.MyBatisDAOModule;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

import static org.dbunit.Assertion.assertEqualsByQuery;
import static org.junit.Assert.assertEquals;

@DataSets({
        "/org/plukh/dbunitguice/dbunit/environments.xml",
        "/org/plukh/dbunitguice/dbunit/topics.xml",
        "/org/plukh/dbunitguice/dbunit/categories.xml",
        "/org/plukh/dbunitguice/dbunit/category_topics.xml"
})
@GuiceModules(MyBatisDAOModule.class)
public class DbUnitTestTest extends DbUnitTest {
    private static final Logger log = LogManager.getLogger(DbUnitTestTest.class);

    @Inject
    protected TopicDAO topicDAO;

    @Test
    @Transactional
    public void testTopic() throws Exception {
        List<TopicWithKeywords> topics = topicDAO.getTopicsWithKeywords("test");
        assertEquals(3, topics.size());
    }

    @Test
    @Transactional
    public void testAssertManual() throws Exception {
        DefaultTable expectedTable = new DefaultTable("CATEGORY_TOPICS",
                new Column[]{new Column("CAT_ID", DataType.INTEGER), new Column("TP_ID", DataType.INTEGER)});
        expectedTable.addRow(new Object[]{1, 1});

        DefaultDataSet expected = new DefaultDataSet(expectedTable);
        log.info(expected.getTable("CATEGORY_TOPICS"));

        List<Category> categories = new LinkedList<>();
        categories.add(new Category().withId(1));

        topicDAO.updateTopicSetCategories(1, categories);

        assertEqualsByQuery(expected, getTestConnection(),
                "select * from CATEGORY_TOPICS where CAT_ID = 1", "CATEGORY_TOPICS", new String[0]);
    }

    @Test
    @Transactional
    public void testAssertResource() throws Exception {
        ITable expected = getDataSet("/org/plukh/dbunitguice/dbunit/expected_category_topics.xml")
                .getTable("CATEGORY_TOPICS");

        List<Category> categories = new LinkedList<>();
        categories.add(new Category().withId(1));

        topicDAO.updateTopicSetCategories(1, categories);

        assertEqualsByQuery(expected, getTestConnection(), "CATEGORY_TOPICS",
                "select * from CATEGORY_TOPICS where CAT_ID = 1", new String[0]);
    }
}
