package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tdiagnose;
import com.example.entity.visit.Tvisit;
import com.example.service.ServiceTemplateCrud;

public interface IServiceDiagnosesVisit<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE> {

	List<GET> getByVisitId(Tvisit tvisit);

	List<GET> getByDiagnosesId( Tdiagnose tdiagnose);

}
