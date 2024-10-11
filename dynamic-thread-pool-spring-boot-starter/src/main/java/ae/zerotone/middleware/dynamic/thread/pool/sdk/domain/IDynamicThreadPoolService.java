package ae.zerotone.middleware.dynamic.thread.pool.sdk.domain;

import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import java.util.List;

/**
 * @description 动态线程池服务
 */
public interface IDynamicThreadPoolService {

  List<ThreadPoolConfigEntity> queryThreadPoolList();

  ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName);

  void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity);
}
