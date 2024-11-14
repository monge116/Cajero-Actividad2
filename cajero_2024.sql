create database cajero_2024;
use cajero_2024;
create table cuentas
(id_cuenta int not null primary key,
saldo double not null,
tipo_cuenta varchar(20) not null,
check (tipo_cuenta in ('AHORRO','CORRIENTE','JUVENIL', 'NOMINA'))
);
INSERT INTO CUENTAS VALUES(1000, 2000,'AHORRO');
INSERT INTO CUENTAS VALUES(2000, 500,'CORRIENTE');
INSERT INTO CUENTAS VALUES(3000, 12000,'CORRIENTE');
INSERT INTO CUENTAS VALUES(4000, 24030,'AHORRO');
commit;
CREATE TABLE MOVIMIENTOS
(ID_MOVIMIENTO INT NOT NULL auto_increment PRIMARY KEY,
ID_CUENTA INT NOT NULL,
FECHA DATETIME,
CANTIDAD DOUBLE,
OPERACION VARCHAR(45),
FOREIGN KEY(ID_CUENTA) REFERENCES CUENTAS(ID_CUENTA)
);

INSERT INTO MOVIMIENTOS (ID_CUENTA, FECHA, CANTIDAD, OPERACION) VALUES
(1000, '2024-11-01 10:00:00', 1000.00, 'Depósito'),
(2000, '2024-11-02 11:30:00', 500.00, 'Retiro'),
(1000, '2024-11-03 14:45:00', 200.00, 'Depósito'),
(2000, '2024-11-05 16:00:00', 300.00, 'Depósito'),
(3000, '2024-11-06 12:30:00', 450.00, 'Depósito'),
(1000, '2024-11-07 08:20:00', 200.00, 'Retiro'),
(4000, '2024-11-08 15:50:00', 700.00, 'Depósito'),
(2000, '2024-11-09 10:10:00', 100.00, 'Retiro'),
(4000, '2024-11-10 17:25:00', 350.00, 'Depósito'),
(3000, '2024-11-11 13:40:00', 250.00, 'Retiro'),
(1000, '2024-11-12 09:00:00', 500.00, 'Depósito'),
(2000, '2024-11-13 14:30:00', 300.00, 'Retiro'),
(3000, '2024-11-14 11:00:00', 600.00, 'Depósito'),
(1000, '2024-11-15 10:30:00', 150.00, 'Retiro'),
(4000, '2024-11-16 12:45:00', 800.00, 'Depósito'),
(2000, '2024-11-17 14:20:00', 200.00, 'Retiro'),
(3000, '2024-11-18 09:50:00', 400.00, 'Depósito'),
(1000, '2024-11-19 16:10:00', 250.00, 'Retiro'),
(4000, '2024-11-20 11:30:00', 500.00, 'Depósito'),
(2000, '2024-11-21 13:00:00', 150.00, 'Retiro'),
(3000, '2024-11-22 15:40:00', 350.00, 'Depósito'),
(1000, '2024-11-23 10:20:00', 100.00, 'Retiro'),
(4000, '2024-11-24 12:00:00', 450.00, 'Depósito'),
(2000, '2024-11-25 14:30:00', 250.00, 'Retiro'),
(3000, '2024-11-26 09:10:00', 300.00, 'Depósito'),
(1000, '2024-11-27 11:50:00', 200.00, 'Retiro'),
(4000, '2024-11-28 13:20:00', 600.00, 'Depósito'),
(2000, '2024-11-29 15:00:00', 100.00, 'Retiro'),
(3000, '2024-11-30 10:40:00', 500.00, 'Depósito'),
(1000, '2024-12-01 12:10:00', 300.00, 'Retiro');


-- drop user ucajero;
CREATE USER ucajero identified by 'ucajero';
grant all privileges on cajero_2024.* to ucajero;
