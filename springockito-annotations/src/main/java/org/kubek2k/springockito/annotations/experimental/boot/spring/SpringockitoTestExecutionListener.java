package org.kubek2k.springockito.annotations.experimental.boot.spring;

import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * {@link org.springframework.test.context.TestExecutionListener} that sets the test class into a System variable
 * to be used by the {@link SpringockitoBeanRegistryFactoryPostProcessor}. It is really important that
 * the {@link SpringockitoBeanRegistryFactoryPostProcessor} bean is created by the application context.
 *
 * @author Filip Hrisafov
 */
public class SpringockitoTestExecutionListener extends AbstractTestExecutionListener implements Ordered {

  @Override
  public void beforeTestClass(TestContext testContext) throws Exception {
    super.beforeTestClass(testContext);

    System.getProperties().setProperty(SpringockitoBeanRegistryFactoryPostProcessor.SPRINGOCKITO_TESTING_CLASS, testContext.getTestClass().getName());
  }

  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
