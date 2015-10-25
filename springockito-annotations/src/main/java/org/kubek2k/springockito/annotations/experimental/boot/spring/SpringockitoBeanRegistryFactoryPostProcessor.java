package org.kubek2k.springockito.annotations.experimental.boot.spring;

import org.kubek2k.springockito.annotations.internal.DefinitionRegistry;
import org.kubek2k.springockito.annotations.internal.Loader;
import org.kubek2k.springockito.annotations.internal.SpringockitoDefinitionFinder;
import org.kubek2k.springockito.annotations.internal.definitions.SpringockitoDefinition;
import org.kubek2k.springockito.annotations.internal.definitions.bean.SpringockitoBeanDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author Filip Hrisafov
 */
@Component
public class SpringockitoBeanRegistryFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {

  private Loader loader = new Loader();

  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

  }

  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }

  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    String testing = System.getProperties().getProperty(SpringockitoTestExecutionListener.TEST_SYSTEM_PROPERTY);
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
