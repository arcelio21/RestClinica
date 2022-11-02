package com.example.service.visit;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.entity.visit.Tvisit;
import com.example.mapper.visit.MapperVisit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceVisitImpl implements IServiceVisit<Tvisit, Integer> {

	private MapperVisit mapperVisit;

	public ServiceVisitImpl(MapperVisit mapperVisit) {
		this.mapperVisit = mapperVisit;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getAll() {

		List<Tvisit> tvisits = this.mapperVisit.getAll();

		if (tvisits == null || tvisits.size() <= 0) {
			return Collections.emptyList();
		}

		return tvisits;
	}

	@Override
	@Transactional(readOnly = true)
	public Tvisit getById(Integer id) {

		if (id == null || id <= 0) {
			return null;
		}

		return this.mapperVisit.getById(id);
	}

	@Override
	@Transactional
	public Integer update(Tvisit tvisit) {

		if (tvisit == null || tvisit.getId() == null || tvisit.getId() <= 0) {
			return 0;
		}
		return this.mapperVisit.update(tvisit);
	}

	@Override
	@Transactional
	public Integer save(Tvisit tvisit) {

		if (tvisit == null || tvisit.getId() <= 0) {
			return 0;
		}
		return this.mapperVisit.update(tvisit);
	}

	/**
	 * @param reg : Usuario de tipo paciente con su ID
	 * @param tstatus      : Status de las consulta{Tvisit} que se esta buscando
	 * @return tvisits: retornara una lista con las consultas encontradas, en
	 *         caso de no haber devolvera una lista vacia}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getByPatientId(TuserTypeReg reg, Tstatus tstatus) {

		if (reg == null || reg.getId() == 0 || reg.getId() <= 0 || tstatus == null || tstatus.getId() == null
				|| tstatus.getId() <= 0) {
			return Collections.emptyList();
		}

		List<Tvisit> tvisits = this.mapperVisit.getByPatientId(reg, tstatus);

		if (tvisits == null || tvisits.size() <= 0) {
			return Collections.emptyList();
		}

		return tvisits;
	}

	/**
	 * @apiNote Este metodo se encargara de buscar los registros de consultas
	 *          filtradas por la especialidad de una enfermera y el estado de las
	 *          consultas que busca
	 * @param 	tuserSpeciality 
	 *                          especialidad de la enfermera por la que filtrara la
	 *
	 *                          consulta con su ID
	 * @param tstatus
	 *                          Status de las consulta{Tvisit} que se esta buscando
	 * @return tvisits 
	 *         Lista de consultas encontradas, en caso de no
	 *         encontrar registro devolvera una lista vacia
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getByNurseId(TuserSpeciality tuserSpeciality, Tstatus tstatus) {

		if (tuserSpeciality == null || tuserSpeciality.getId() == null || tuserSpeciality.getId() <= 0
				|| tstatus == null || tstatus.getId() == null || tstatus.getId() <= 0) {
			return Collections.emptyList();
		}

		List<Tvisit> tvisits = this.mapperVisit.getByNurseId(tuserSpeciality, tstatus);

		if (tvisits == null || tvisits.size() <= 0) {
			return Collections.emptyList();
		}
		return tvisits;
	}

	/**
	 * @apiNote Este metodo se encargar de buscar las consultas disponibles filtrado
	 *          por el valor de los parametros enviados al metodo
	 * @param speciality : Especialidad del medico por el que se filtrara las
	 *                        consultas
	 * @param  tstatus  : Tipo de estado de las consultas que se desean buscar
	 * @return tvisits : Devolvera una lista de consultas
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getByPhysicianId(TuserSpeciality speciality, Tstatus tstatus) {

		if (speciality == null || speciality.getId() == null || speciality.getId() <= 0 || tstatus == null
		|| tstatus.getId() == null || tstatus.getId() <= 0){
			return Collections.emptyList();
		}

		List<Tvisit> tvisits = this.mapperVisit.getByPhysicianId(speciality, tstatus);

		if(tvisits==null || tvisits.size()<=0){
			return Collections.emptyList();
		}
		return tvisits;
	}

	/**
	 *@apiNote Metodo que buscara las consultas disponible filtrada por espcialidad y estado de acuerdo a los valores enviado por parametro
	 *@param tspeciality : Especialidad 
	 *@param tstatus : Estado de las consultas
	 *@return tvisits : Listado de las consultas encontradas
	 * */
	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getBySpecialityId(Tspeciality tspeciality, Tstatus tstatus) {

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getByCreationDate(Tstatus tstatus, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tvisit> getByStatusId(Tstatus tstatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
