package org.plukh.dbunitguice.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class GuiceJUnitRunner extends BlockJUnit4ClassRunner {
    private Injector injector;

    @Override
    public Object createTest() throws Exception {
        //log.debug("Creating test");
        return injector.getInstance(this.getTestClass().getJavaClass());
    }

    public GuiceJUnitRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        //log.debug("Creating injector");
        Class<?>[] moduleClasses = getModulesFor(clazz);
        injector = createInjectorFor(moduleClasses, clazz, isStaticInjectionRequested(clazz));
    }

    private boolean isStaticInjectionRequested(Class<?> clazz) {
        return clazz.getAnnotation(RequestStaticInjection.class) != null;
    }

    private Injector createInjectorFor(Class<?>[] moduleClasses, Class<?> clazz, boolean staticInjectionRequested)
            throws InitializationError {
        Module[] modules = new Module[moduleClasses.length + 1];
        for (int i = 0; i < moduleClasses.length; i++) {
            try {
                modules[i] = (Module) (moduleClasses[i]).newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new InitializationError(e);
            }
        }
        //Last module will be our custom module
        modules[moduleClasses.length] = new StaticInjectionRequestingModule(staticInjectionRequested, clazz);
        return Guice.createInjector(modules);
    }

    private Class<?>[] getModulesFor(Class<?> clazz) throws InitializationError {
        GuiceModules annotation = clazz.getAnnotation(GuiceModules.class);
        if (annotation == null)
            throw new InitializationError(
                    "Missing @GuiceModules annotation for unit test '" + clazz.getName()
                            + "'");
        return annotation.value();
    }
}