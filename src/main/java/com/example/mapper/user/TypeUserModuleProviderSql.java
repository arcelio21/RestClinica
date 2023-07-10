package com.example.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TypeUserModuleProviderSql{

    public static String getTypeUserDistinctByIdModuleAndIdStatus(@Param("idStatus") final Integer idStatus){

        SQL query = new SQL();
        query.SELECT_DISTINCT("Tu.name_type_user as typeUser")
                .SELECT("Tu.id AS idTypeUser")
                .FROM("Ttypeusers_modules Ttm")
                .INNER_JOIN("Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id")
                .INNER_JOIN("Tmodules Tm on Tp.module_id = Tm.id")
                .INNER_JOIN("Tstatus Ts on Tp.status_id = Ts.id")
                .INNER_JOIN("Ttypes_users Tu on Ttm.type_user_id = Tu.id")
                .WHERE("Tp.module_id=#{idModule}");

        if ( idStatus!=null &&idStatus>0){
            query.AND().WHERE("Tp.status_id=#{idStatus}");
        }

        return query.toString();
    }

    public static String getModuleDistinctByIdTypeUserAndIdStatus(@Param("idStatus") final Integer idStatus){

        SQL query = new SQL();

        query.SELECT_DISTINCT("DISTINCT Tm.name_modules as nameModule")
                .SELECT(" Tm.id as idModule")
                .FROM("Ttypeusers_modules Ttm")
                .INNER_JOIN("Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id")
                .INNER_JOIN("Tmodules Tm on Tp.module_id = Tm.id")
                .INNER_JOIN("Ttypes_users Tu on Ttm.type_user_id = Tu.id")
                .WHERE("Ttm.type_user_id=#{idTypeUser}");

        if(idStatus!=null && idStatus>0){
            query.WHERE("Tp.status_id=#{idStatus}");
        }
        return query.toString();
    }


}
