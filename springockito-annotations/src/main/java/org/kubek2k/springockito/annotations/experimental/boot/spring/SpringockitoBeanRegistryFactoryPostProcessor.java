package org.kubek2k.springockito.annotations.experimental.boot.spring;

import org.kubek2k.springockito.annotations.internal.Loader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * {@link BeanDefinitionRegistryPostProcessor} that is used to modify the spring bean
 * definitions for the beans that need to be replaced with Mock.
 *
 * @author Filip Hrisafov
 */
@Component
public class SpringockitoBeanRegistryFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {

  public static final String SPRINGOCKITO_TESTING_CLASS = "springockito.testing.class.name";

  private Loader loader = new Loader();

  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

  }

  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }

  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    String testing = System.getProperties().getProperty(SPRINGOCKITO_TESTING_CLASS);
    if (testing == null) {
      return;
    }
    try {
      Class<?> testingClass = Class.forName(testing);
      this.loader.defineMocksAndSpies(testingClass);
    } catch (ClassNotFoundException e) {
      return;
    }
    this.loader.registerMocksAndSpies(registry);
  }
}
