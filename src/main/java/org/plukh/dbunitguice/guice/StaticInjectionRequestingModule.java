package org.plukh.dbunitguice.guice;

import com.google.inject.AbstractModule;

public class StaticInjectionRequestingModule extends AbstractModule{
    private final boolean requestStaticInjection;
    private final Class<?> requestingClass;

    public StaticInjectionRequestingModule(boolean requestStaticInjection, Class<?> aClass) {
        this.requestStaticInjection = requestStaticInjection;
        requestingClass = aClass;
    }

    @Override
    protected void configure() {
        if (requestStaticInjection) requestStaticInjection(requestingClass);
    }
}
