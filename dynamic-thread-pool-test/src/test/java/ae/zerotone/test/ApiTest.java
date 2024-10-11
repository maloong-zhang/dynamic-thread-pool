package ae.zerotone.test;

import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RTopic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

  @Resource private RTopic dynamicThreadPoolRedisTopic;

  @Test
  public void test_dynamicThreadPoolRedisTopic() throws InterruptedException {
    ThreadPoolConfigEntity threadPoolConfigEntity =
        new ThreadPoolConfigEntity("dynamic-thread-pool-test-app", "threadPoolExecutor01");
    threadPoolConfigEntity.setPoolSize(100);
    threadPoolConfigEntity.setMaximumPoolSize(100);
    dynamicThreadPoolRedisTopic.publish(threadPoolConfigEntity);

    new CountDownLatch(1).await();
  }
}
