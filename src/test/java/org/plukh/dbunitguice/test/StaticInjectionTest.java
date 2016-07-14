package org.plukh.dbunitguice.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plukh.dbunitguice.guice.GuiceJUnitRunner;
import org.plukh.dbunitguice.guice.GuiceModules;
import org.plukh.dbunitguice.guice.MyBatisDAOModule;
import org.plukh.dbunitguice.guice.RequestStaticInjection;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

@RunWith(GuiceJUnitRunner.class)
@GuiceModules(MyBatisDAOModule.class)
@RequestStaticInjection
public class StaticInjectionTest {
    @Inject
    protected static SqlSession sqlSession;
    private static boolean sessionSetUp;

    @BeforeClass
    public static void setUpClass() {
        sessionSetUp = sqlSession != null;
    }

    @Test
    public void testStaticInjection() throws Exception {
        assertTrue(sessionSetUp);
    }
}
