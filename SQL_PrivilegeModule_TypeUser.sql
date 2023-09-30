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
