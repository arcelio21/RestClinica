package com.example.mapper.modules;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.modules.Tprivilege;

@Mapper
public interface MapperPrivilege {

	@Select("SELECT * FROM Tprivileges")
	@Result(column = "name_privilege",property = "namePrivilege")
	List<Tprivilege> getAll();
	
	@Select("SELECT * FROM Tprivileges WHERE id=#{id}")
	@Result(column = "name_privilege",property = "namePrivilege")
	Tprivilege getByid(@Param("id") Integer id);
	
	@Update("UPDATE Tprivileges SET name_privilege=#{priv.namePrivilege} WHERE id=#{priv.id}")
	Integer update(@Param("priv")Tprivilege tprivilege);
	
	@Insert("INSERT INTO Tprivileges (name_privilege) VALUES (#{priv.namePrivilege})")
	Integer save(@Param("priv")Tprivilege tprivilege);
}
