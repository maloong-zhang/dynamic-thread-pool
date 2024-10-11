package ae.zerotone.middleware.dynamic.thread.pool.sdk.trigger.listener;

import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import ae.zerotone.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import com.alibaba.fastjson.JSON;
import java.util.List;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 动态线程池变更监听
 */
public class ThreadPoolConfigAdjustListener implements MessageListener<ThreadPoolConfigEntity> {

  private final IDynamicThreadPoolService dynamicThreadPoolService;
  private final IRegistry registry;
  private Logger logger = LoggerFactory.getLogger(ThreadPoolConfigAdjustListener.class);

  public ThreadPoolConfigAdjustListener(
      IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
    this.dynamicThreadPoolService = dynamicThreadPoolService;
    this.registry = registry;
  }

  @Override
  public void onMessage(CharSequence charSequence, ThreadPoolConfigEntity threadPoolConfigEntity) {
    logger.info(
        "动态线程池，调整线程池配置。线程池名称:{} 核心线程数:{} 最大线程数:{}",
        threadPoolConfigEntity.getThreadPoolName(),
        threadPoolConfigEntity.getPoolSize(),
        threadPoolConfigEntity.getMaximumPoolSize());
    dynamicThreadPoolService.updateThreadPoolConfig(threadPoolConfigEntity);

    // 更新后上报最新数据
    List<ThreadPoolConfigEntity> threadPoolConfigEntities =
        dynamicThreadPoolService.queryThreadPoolList();
    registry.reportThreadPool(threadPoolConfigEntities);

    ThreadPoolConfigEntity threadPoolConfigEntityCurrent =
        dynamicThreadPoolService.queryThreadPoolConfigByName(
            threadPoolConfigEntity.getThreadPoolName());
    registry.reportThreadPoolConfigParameter(threadPoolConfigEntityCurrent);
    logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
  }
}
