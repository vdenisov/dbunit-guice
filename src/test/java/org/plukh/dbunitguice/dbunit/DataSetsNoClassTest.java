package org.plukh.dbunitguice.dbunit;

import org.junit.Test;
import org.plukh.dbunitguice.guice.GuiceModules;
import org.plukh.dbunitguice.guice.MyBatisDAOModule;

import static org.junit.Assert.assertEquals;

@GuiceModules(MyBatisDAOModule.class)
public class DataSetsNoClassTest extends DbUnitTest {

    @Test
    public void testNoDataSets() throws Exception {
        assertEquals(0, getDataSet().getTableNames().length);
    }

    @Test
    @DataSets("/org/plukh/dbunitguice/dbunit/environments.xml")
    public void testMethod() throws Exception {
        String[] tables = getDataSet().getTableNames();
        assertEquals(1, tables.length);
        assertEquals("ENVIRONMENTS", tables[0]);
    }

    @Test
    @DataSets(value = "/org/plukh/dbunitguice/dbunit/environments.xml", override = true)
    public void testMethodOverride() throws Exception {
        String[] tables = getDataSet().getTableNames();
        assertEquals(1, tables.length);
        assertEquals("ENVIRONMENTS", tables[0]);
    }
}
