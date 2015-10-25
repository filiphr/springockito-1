package org.kubek2k.springockito.annotations.experimental.boot.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Filip Hrisafov
 */
public class SpringockitoTestExecutionListener extends AbstractTestExecutionListener implements Ordered {

  public static final String TEST_SYSTEM_PROPERTY = "springockito.testing.class";

  @Override
  public void beforeTestClass(TestContext testContext) throws Exception {
    super.beforeTestClass(testContext);
    System.getProperties().setProperty(TEST_SYSTEM_PROPERTY, testContext.getTestClass().getName());
  }

  @Override
  public void prepareTestInstance(TestContext testContext) throws Exception {
    super.prepareTestInstance(testContext);
  }

  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
