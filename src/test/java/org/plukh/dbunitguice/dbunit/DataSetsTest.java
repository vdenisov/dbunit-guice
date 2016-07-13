package org.plukh.dbunitguice.dbunit;

import org.junit.Test;
import org.plukh.dbunitguice.guice.GuiceModules;
import org.plukh.dbunitguice.guice.MyBatisDAOModule;

import static org.junit.Assert.assertEquals;

@DataSets({
        "/org/plukh/dbunitguice/dbunit/environments.xml"
})
@GuiceModules(MyBatisDAOModule.class)
public class DataSetsTest extends DbUnitTest {
    @Test
    public void testClassOnly() throws Exception {
        String[] tables = getDataSet().getTableNames();
        assertEquals(1, tables.length);
        assertEquals("ENVIRONMENTS", tables[0]);
    }

    @Test
    @DataSets("/org/plukh/dbunitguice/dbunit/topics.xml")
    public void testClassAndMethod() throws Exception {
        String[] tables = getDataSet().getTableNames();
        assertEquals(2, tables.length);
        assertEquals("ENVIRONMENTS", tables[0]);
        assertEquals("TOPICS", tables[1]);
    }

    @Test
    @DataSets(value = "/org/plukh/dbunitguice/dbunit/topics.xml", override = true)
    public void testClassAndMethodOverride() throws Exception {
        String[] tables = getDataSet().getTableNames();
        assertEquals(1, tables.length);
        assertEquals("TOPICS", tables[0]);
    }
}
