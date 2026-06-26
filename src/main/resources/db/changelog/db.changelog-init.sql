--liquibase formatted sql

--changeset analyse-and-ask-abhiraj:0-setup-roles splitStatements:false endDelimiter:$$
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'my-local-dev') THEN
CREATE ROLE "my-local-dev" WITH LOGIN PASSWORD 'my-local-dev-pswd';
END IF;
    ALTER ROLE "my-local-dev" WITH SUPERUSER;
    GRANT ALL PRIVILEGES ON DATABASE vector_db TO "my-local-dev";
END
$$;

--changeset analyse-and-ask-abhiraj:1-init-vector-extension
CREATE EXTENSION IF NOT EXISTS vector;

--changeset analyse-and-ask-abhiraj:2-create-vector-store-table
CREATE TABLE IF NOT EXISTS vector_store (
    id UUID PRIMARY KEY,
    content TEXT,
    metadata JSONB,
    embedding VECTOR(384) -- 384 dimensions perfectly maps HuggingFace/Qwen text embedding arrays
);
