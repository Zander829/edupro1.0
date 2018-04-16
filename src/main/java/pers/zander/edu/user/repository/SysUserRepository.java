package pers.zander.edu.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pers.zander.edu.user.domain.SysUser;
/**
 * 数据层
 * @author 赵召
 * Mar 21, 2016 10:01:30 AM
 */
@Repository
public interface SysUserRepository extends CrudRepository<SysUser,Long>,JpaSpecificationExecutor<SysUser>{

	@Query("from SysUser where name like %?1%")
	public List<SysUser> findByName(String name);	
	
	public SysUser findById(Long id);
	
	
}
