package org.plukh.dbunitguice.guice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.guice.XMLMyBatisModule;
import org.plukh.dbunitguice.dao.MyBatisTopicDAOImpl;
import org.plukh.dbunitguice.dao.TopicDAO;

public class MyBatisDAOModule extends XMLMyBatisModule {
    private final static Logger log = LogManager.getLogger();

    public MyBatisDAOModule() {
    }

    @Override
    protected void initialize() {
        log.debug("Test DAO Module initializing...");

        //Set environment
        setEnvironmentId("test");

        //Bind DAO implementations
        bind(TopicDAO.class).to(MyBatisTopicDAOImpl.class);

        log.info("DAO Module initialized");
    }
}
