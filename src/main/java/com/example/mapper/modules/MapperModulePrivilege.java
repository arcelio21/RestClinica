package com.example.mapper.modules;

import com.example.entity.modules.TmodulePrivilege;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperModulePrivilege {

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

	@Select(value = """
		SELECT mp.id AS id, pr.name_privilege AS namePrivilege, m.name_modules AS nameModule, s.name_status AS nameStatus  
			FROM Tmodules_privileges  mp 
				INNER JOIN Tprivileges pr ON mp.privilege_id=pr.id
				INNER JOIN Tstatus s ON s.id = mp.status_id
				INNER JOIN Tmodules m ON m.id = mp.module_id
			WHERE mp.status_id=1;
	""")
	@Results(
		id = "detailsModulePrivilege",
		value = {
			@Result(column = "id", property = "id"),
			@Result(column = "namePrivilege", property = "privilege.namePrivilege"),
			@Result(column = "nameModule", property = "module.nameModule"),
			@Result(column = "nameStatus", property = "status.name")
		}
	)
	List<TmodulePrivilege> getModulePrivilegeDetails();

	@Select(value = """
		SELECT mp.id AS id, pr.name_privilege AS namePrivilege, m.name_modules AS nameModule, s.name_status AS nameStatus  
			FROM Tmodules_privileges  mp 
				INNER JOIN Tprivileges pr ON mp.privilege_id=pr.id
				INNER JOIN Tstatus s ON s.id = mp.status_id
				INNER JOIN Tmodules m ON m.id = mp.module_id
			WHERE mp.status_id=1 AND mp.module_id=#{idModule};
	""")
	@ResultMap(value = "detailsModulePrivilege")
	List<TmodulePrivilege> getModulePrivilegeDetailsByModuleId(@Param(value = "idModule") Long idModule);

	@Select(value = """
		SELECT mp.id AS id, pr.name_privilege AS namePrivilege, m.name_modules AS nameModule, s.name_status AS nameStatus  
			FROM Tmodules_privileges  mp 
				INNER JOIN Tprivileges pr ON mp.privilege_id=pr.id
				INNER JOIN Tstatus s ON s.id = mp.status_id
				INNER JOIN Tmodules m ON m.id = mp.module_id
			WHERE mp.status_id=1 AND mp.id=#{id};
	""")
	@ResultMap(value = "detailsModulePrivilege")
	TmodulePrivilege getModulePrivilegeDetailsById(@Param(value = "id") Long id);
	
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
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Integer save(@Param("modPriv") TmodulePrivilege tmodulePrivilege);
}
