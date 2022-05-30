FROM mysql:5.7
COPY ./create-local-db.sql /tmp
EXPOSE 3306
CMD [ "mysqld", "--init-file=/tmp/create-local-db.sql" ]