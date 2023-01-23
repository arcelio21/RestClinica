package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tdiagnose;
import com.example.entity.visit.Tvisit;
import com.example.service.ServiceTemplateCrud;

public interface IServiceDiagnosesVisit<T, I> extends ServiceTemplateCrud<T, I> {

	List<T> getByVisitId(Tvisit tvisit);

	List<T> getByDiagnosesId( Tdiagnose tdiagnose);

}
