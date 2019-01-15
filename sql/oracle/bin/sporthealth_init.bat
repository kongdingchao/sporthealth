@echo off
set nls_lang=AMERICAN_AMERICA.UTF8

@set DB_SERV=ORCL
@set DB_USER=QE_P
@set DB_PWD=QE_P

:begin
sqlplus %DB_USER%/%DB_PWD%@%DB_SERV% @sporthealth_init.sql
pause
:end