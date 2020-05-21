package cn.yun.go.repository;

import cn.yun.go.entity.PermissionDTO;
import org.springframework.data.repository.CrudRepository;

/**
 * To have a component responsible for storage and retrieval,
 * we need to define a repository interface.
 *
 * CrudRepository provides CRUD and finder operations.
 *
 * @author jinyun liu
 */
public interface PermissionRepository extends CrudRepository<PermissionDTO, String> {

}
