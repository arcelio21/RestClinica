package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.Tvisit;
import com.example.service.ServiceTemplateCrud;

public interface IServicePrescriptionsVisit<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE> {

	List<GET> getByVisitId(Tvisit tvisit);

	List<GET> getByPrescriptionId(Tprescription tprescription);

}
