package ae.zerotone.middleware.dynamic.thread.pool.sdk.trigger.job;

import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import ae.zerotone.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import com.alibaba.fastjson.JSON;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description 线程池数据上报任务
 */
public class ThreadPoolDataReportJob {

  private final Logger logger = LoggerFactory.getLogger(ThreadPoolDataReportJob.class);

  private final IDynamicThreadPoolService dynamicThreadPoolService;

  private final IRegistry registry;

  public ThreadPoolDataReportJob(
      IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
    this.dynamicThreadPoolService = dynamicThreadPoolService;
    this.registry = registry;
  }

  @Scheduled(cron = "0/20 * * * * ?")
  public void execReportThreadPoolList() {
    List<ThreadPoolConfigEntity> threadPoolConfigEntities =
        dynamicThreadPoolService.queryThreadPoolList();
    registry.reportThreadPool(threadPoolConfigEntities);
    logger.info("动态线程池，上报线程池信息：{}", JSON.toJSONString(threadPoolConfigEntities));

    for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
      registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
      logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
    }
  }
}
