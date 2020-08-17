package com.yk.bike.utils.application;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 92058
 */
public abstract class AbstractComponentRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    /**
     * 扫描的类
     */
    public abstract Class<?>[] classes();

    private Environment environment;

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata, @NonNull BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(
                registry, false, environment, resourceLoader);
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            for (Class<?> aClass : classes()) {
                if (aClass.getName().equals(metadataReader.getClassMetadata().getClassName())) {
                    return true;
                }
            }
            return false;
        });
        scanner.scan(classPath());
    }

    private String[] classPath() {
        Set<String> pathSet = Arrays.stream(classes()).map(Class::getPackageName).collect(Collectors.toSet());
        String[] paths = new String[classes().length];
        pathSet.toArray(paths);
        return paths;
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(@NonNull ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
