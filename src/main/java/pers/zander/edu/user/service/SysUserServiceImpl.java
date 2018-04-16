package pers.zander.edu.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Expression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import pers.zander.edu.user.domain.SysUser;
import pers.zander.edu.user.repository.SysUserRepository;
import pers.zander.edu.user.web.model.SysUserModel;
/**
 * 业务实现类
 * @author 赵召
 * Mar 21, 2016 10:01:08 AM
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService{

	@Autowired  
	private StringRedisTemplate template; 
	@Autowired
	private SysUserRepository sysUserRepository;
	@Override
	public void addBean(SysUser bean) {
		// TODO Auto-generated method stub
		sysUserRepository.save(bean);
	}
	@Override
	public List<SysUser> getBean(SysUserModel model) {
		return sysUserRepository.findAll(getSpecification(model));
	}
	@Override
	public List<SysUser> getAllBean() {
		// TODO Auto-generated method stub
		return (List<SysUser>) sysUserRepository.findAll();
	}
	@Override
	public void delBean(Long id) {
		// TODO Auto-generated method stub
		sysUserRepository.delete(id);
	}
	@Override
	public SysUser getBeanById(SysUserModel model) {
		// TODO Auto-generated method stub
		return sysUserRepository.findById(model.getId());
	}
	@Override
	public Page<SysUser> getBeanByPage(SysUserModel model,Pageable pageable) {
		// TODO Auto-generated method stub
		return sysUserRepository.findAll(getSpecification(model),pageable);
	}
	
	
	public Specification<SysUser> getSpecification(final SysUserModel model){
		return new Specification<SysUser>(){
		@Override
		public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
			
			Predicate namePredicate = null;
			if(model.getId()!=null&&model.getId()>-1){
				namePredicate = cb.and(cb.equal(root.<Long>get("id"), model.getId()));
				andPredicates.add(namePredicate);
			}
			if(model.getTel()!=null&&model.getTel().length()>0){
				namePredicate = cb.and(cb.equal(root.<Long>get("tel"), model.getTel()));
				andPredicates.add(namePredicate);
			}
			if(model.getName()!=null&&model.getName().length()>0){
				namePredicate = cb.and(cb.like(root.<String>get("name"), model.getName()));
				andPredicates.add(namePredicate);
			}
			
			if(andPredicates.isEmpty()){
				return null;
			}else{
				Predicate predicate = cb.conjunction();
				predicate.getExpressions().addAll(andPredicates);
				return predicate;
			}
		}
		
	};
	}
}
