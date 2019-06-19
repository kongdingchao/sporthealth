#!/bin/sh

read -p "Enter server address(e.g.:127.0.0.1):" db_serv
read -p "Enter database username(e.g.:root):" db_user
read -p "Enter database password:" db_pwd

sqlplus $db_user/$db_user@$db_serv <<EOF
spool eops_init.log
@../sporthealth.init.oracle.sql
commit;
spool off
exit;
EOF