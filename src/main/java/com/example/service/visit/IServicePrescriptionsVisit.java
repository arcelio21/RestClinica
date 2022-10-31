package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.TprescriptionVisit;
import com.example.entity.visit.Tvisit;
import com.example.service.ServiceTemplateCrud;

public interface IServicePrescriptionsVisit<T, I> extends ServiceTemplateCrud<T, I> {

	List<T> getByVisitId(Tvisit tvisit);

	List<T> getByPrescriptionId(Tprescription tprescription);

}
