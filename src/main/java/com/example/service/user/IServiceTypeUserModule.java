package com.example.service.user;
import com.example.service.ServiceTemplateCrud;

import java.util.List;

import com.example.entity.user.TtypeUserModule;

public interface IServiceTypeUserModule extends ServiceTemplateCrud<TtypeUserModule,Integer,TtypeUserModule,TtypeUserModule>{
    List<TtypeUserModule> getPrivTypeUser(Integer typeUserId);
    List<TtypeUserModule> getModulePriv(Integer modulePrivilegeId);
    Integer update (TtypeUserModule idTypUserModule,Integer modulPrivBef);
}
