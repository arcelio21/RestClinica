package com.example.mapper.modules;

import com.example.entity.modules.TmodulePrivilege;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperModulePrivilege {

	/**
	 * @Results(
	 *                        id = "modulPrivMap",
	 * 			value = {
	 *                @Result(column = "id",property = "id"),
	 *                @Result(column = "privilege_id", property = "privilege", one = @One(select="com.example.mapper.modules.MapperPrivilege.getByid",fetchType = FetchType.LAZY)),
	 *                @Result(column = "module_id",property = "module", one = @One(select="com.example.mapper.modules.MapperModules.getById",fetchType = FetchType.LAZY)),
	 *                @Result(column = "status_id",property = "status", one = @One(select="com.example.mapper.status.MapperStatus.getById",fetchType = FetchType.LAZY))
	 *            }
	 * 	)
	 * @return
	 */
	@Select("SELECT id,module_id,privilege_id,status_id FROM Tmodules_privileges")
	@Results(
			id = "ModulePrivilegeSimpleDat",
			value = {
					@Result(column = "id", property = "id"),
					@Result(column = "privilege_id", property = "privilege.id"),
					@Result(column = "module_id", property = "module.id"),
					@Result(column = "status_id", property = "status.id")
			}
	)
	List<TmodulePrivilege> getAll();
	
	@Select("SELECT * FROM Tmodules_privileges WHERE id=#{id}")
	@ResultMap(value = "ModulePrivilegeSimpleDat")
	TmodulePrivilege getById(@Param("id") Long id);

	
	@Update("UPDATE Tmodules_privileges "
			+ "SET privilege_id=#{modPriv.privilege.id},module_id=#{modPriv.module.id},status_id=#{modPriv.status.id} "
			+ "WHERE id=#{modPriv.id}")
	Integer update(@Param("modPriv") TmodulePrivilege tmodulePrivilege);
	
	@Insert("INSERT INTO Tmodules_privileges "
			+ "(privilege_id,module_id,status_id)"
			+ "VALUES(#{modPriv.privilege.id},#{modPriv.module.id},#{modPriv.status.id})")
	Integer save(@Param("modPriv") TmodulePrivilege tmodulePrivilege);
}
