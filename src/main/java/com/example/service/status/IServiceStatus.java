package com.example.service.status;

import java.util.List;

import com.example.entity.status.Tstatus;
import com.example.service.ServiceTemplateCrud;

public interface IServiceStatus extends ServiceTemplateCrud<Tstatus, Integer>{

	List<Tstatus> getByName( String name);
}
