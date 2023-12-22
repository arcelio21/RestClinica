SELECT * from Ttypes_users;
INSERT INTO Tmodules (name_modules) values('users');
INSERT INTO Tmodules (name_modules) values('typeusers');
INSERT INTO Tmodules (name_modules) values('citas');
SELECT * FROM Tmodules;
SELECT * FROM Tmodules_privileges;
SELECT * FROM Tprivileges;
SELECT * FROM Tstatus;

INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (1,3,1);
INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (2,3,1);
INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (1,4,1);
INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (3,4,1);
INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (1,5,1);
INSERT INTO Tmodules_privileges (privilege_id, module_id, status_id) VALUES (3,5,1);

SELECT * FROM Ttypeusers_modules;
INSERT INTO Ttypeusers_modules (modls_privgs_id, type_user_id) VALUES (2,1),(3,1),(4,1),(7,1),(5,2),(6,2);

# VER SOLO LOS MODULOS A LOS QUE LOS TIPOS DE USUARIOS TIENEN PRIVILEGIO
SELECT DISTINCT(Tm.name_modules),Tm.id, Tu.name_type_user, Tu.id
FROM Ttypeusers_modules Ttm
			INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id
			INNER JOIN Tmodules Tm on Tp.module_id = Tm.id
			INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id WHERE Ttm.id=2;

# VER TODOS LOS PRIVILEGIOS DE MODULOS ASIGNADOS A LOS TIPOS DE USUARIOS
SELECT Ttm.id, Tm.name_modules, Tu.name_type_user, tpr.name_privilege
FROM Ttypeusers_modules Ttm
         INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id
         INNER JOIN Tmodules Tm on Tp.module_id = Tm.id
        INNER JOIN Tprivileges tpr ON Tp.privilege_id = tpr.id
         INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id;

# BUSCAR PRIVILEGIOS QUE TIENE SOBRE UN MODULO UN TIPO DE USUARIO
SELECT tpm.id, Tpr.name_privilege,Ts.id ,Ts.name_status
FROM Ttypeusers_modules tpm
INNER JOIN Tmodules_privileges Tp on tpm.modls_privgs_id = Tp.id
INNER JOIN Tprivileges Tpr on Tp.privilege_id = Tpr.id
INNER JOIN Tstatus Ts on Tp.status_id = Ts.id
WHERE tpm.type_user_id = 1 AND Tp.module_id=4;

SELECT mo.name_modules AS nameMOdule, tu.name_type_user AS typeUser, pr.name_privilege AS namePrivilege
    FROM Ttypeusers_modules tm
    INNER JOIN Tmodules_privileges mp  on tm.modls_privgs_id = mp.id
    INNER JOIN Ttypes_users tu ON tm.type_user_id = tu.id
    INNER JOIN Tmodules mo ON mp.module_id = mo.id
    INNER JOIN Tprivileges pr ON mp.privilege_id = pr.id
WHERE mp.status_id = 1;


# OBTENER DATOS DE TIPOS DE USUARIOS Y USUARIOS ASOCIADOS

SELECT utr.id AS ID, concat(ur.name, ' ', ur.last_name) AS Nombre, ur.iden_card AS Cedula,tu.name_type_user as TypeUser, st.name_status AS Estado
    FROM Tusers_types_regs utr
    INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
    INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
    INNER JOIN Tstatus st ON utr.status_id = st.id;

#OTBENER ESPECIALIDADES DE USUARIOS POR TIPOS DE ESPECIALIDADRES
SELECT us.id AS id, st.name_status AS nameStatus,usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser
				FROM Tusers_specialties us
			INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id
			INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id
			INNER JOIN Tstatus st ON us.status_id = st.id
			INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
			WHERE us.speciality_id=1