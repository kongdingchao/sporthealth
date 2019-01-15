@echo off
set nls_lang=AMERICAN_AMERICA.UTF8

@set DB_SERV=ORCL
@set DB_USER=kdc
@set DB_PWD=kdc

:begin
sqlplus %DB_USER%/%DB_PWD%@%DB_SERV% @sporthealth_init.sql
pause
:end