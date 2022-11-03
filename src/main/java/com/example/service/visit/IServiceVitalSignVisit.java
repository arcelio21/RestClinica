package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tvisit;
import com.example.entity.visit.TvitalSign;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVitalSignVisit<T, I> extends ServiceTemplateCrud<T, I> {

	List<T> getByVisitId(Tvisit tvisit);

	List<T> getByVitalSignId(TvitalSign sign);

}
