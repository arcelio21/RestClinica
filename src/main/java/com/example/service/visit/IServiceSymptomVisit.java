package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.Tsymptom;
import com.example.entity.visit.Tvisit;
import com.example.service.ServiceTemplateCrud;

public interface IServiceSymptomVisit<T, I> extends ServiceTemplateCrud<T, I> {

	List<T> getByVisitId(Tvisit tvisit);

	List<T> getBySymptomId(Tsymptom tsymptom);

}
