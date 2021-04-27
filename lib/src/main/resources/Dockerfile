FROM postgres
ENV POSTGRES_USER ndemo
ENV POSTGRES_PASSWORD npass
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432
