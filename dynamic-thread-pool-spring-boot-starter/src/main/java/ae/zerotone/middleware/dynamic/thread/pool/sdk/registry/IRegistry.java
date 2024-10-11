package ae.zerotone.middleware.dynamic.thread.pool.sdk.registry;

import ae.zerotone.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import java.util.List;

/**
 * @description 注册中心接口
 */
public interface IRegistry {

  void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolEntities);

  void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
