package com.example.mapper.modules;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.modules.TmodulePrivilege;

@Mapper
public interface MapperModulePrivilege {

	@Select("SELECT * FROM Tmodules_privileges")
	@Results(
			id = "modulPrivMap",
			value = {
				@Result(column = "id",property = "id"),
				@Result(column = "privilege_id", property = "privilege.id"),
				@Result(column = "module_id",property = "module.id"),
				@Result(column = "status_id",property = "status.id")
			}
	)
	List<TmodulePrivilege> getAll();
	
	@Select("SELECT * FROM Tmodules_privileges WHERE id=#{id}")
	@ResultMap(value = "modulPrivMap")
	TmodulePrivilege getById(@Param("id") Integer id);
	
	@Update("UPDATE Tmodules_privileges "
			+ "SET privilege_id=#{modPriv.privilege.id},module_id=#{modPriv.module.id},status_id=#{modPriv.status.id} "
			+ "WHERE id=#{modPriv.id}")
	Integer update(@Param("modPriv") TmodulePrivilege tmodulePrivilege);
	
	@Insert("INSERT INTO Tmodules_privileges "
			+ "(privilege_id,module_id,status_id)"
			+ "VALUES(#{modPriv.privilege.id},#{modPriv.module.id},#{modPriv.status.id})")
	Integer save(@Param("modPriv") TmodulePrivilege tmodulePrivilege);
}
