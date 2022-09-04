package com.example.mapper.status;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.status.Tstatus;

@Mapper
public interface MapperStatus {

	@Select("SELECT * FROM Tstatus")
	@Result(column = "name_status",property = "name")
	public List<Tstatus> getAll();
	
	@Select("SELECT * FROM Tstatus WHERE id=#{id}")
	@Result(column = "name_status",property = "name")
	public Tstatus getById(@Param("id") Integer id);
	
	@Update("UPDATE Tstatus SET name_status=#{status.name} WHERE id=#{status.id}")
	public Integer update(@Param("status") Tstatus status);
	
	@Insert("INSERT INTO Tstatus (name_status) VALUES(#{status.name})")
	public Integer save(@Param("status") Tstatus status);
}
