package com.example.service.address;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.entity.address.Taddress;
import com.example.mapper.address.MapperAddress;

@Service
public class ServiceAddressImpl implements IServiceAddress<Taddress, Integer> {

	private MapperAddress mapper;
	
	public ServiceAddressImpl(MapperAddress mapper) {
		this.mapper=mapper;
	}
	

	@Override
	public List<Taddress> getAll() {
		
		return this.mapper.getAll();
	}

	@Override
	public Taddress getById(Integer id) {

		if(id!=null && id>0) {
			return this.mapper.getById(id);
		}else {
			return null;
		}
		
	}

	@Override
	public Integer update(Taddress taddress) {
		
		if(taddress!=null) {
			return this.update(taddress);
		}
		
		return 0;
	}

	@Override
	public Integer save(Taddress taddress) {
		
		if(taddress!=null){
			return this.mapper.save(taddress);
		}
		return 0;
	}

}
