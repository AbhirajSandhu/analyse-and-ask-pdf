-- 1. Activate the vector extension right inside our database
CREATE EXTENSION IF NOT EXISTS vector;

-- 2. Create our user with full super privileges
CREATE USER "my-local-dev" WITH PASSWORD 'my-local-dev-pswd';
ALTER USER "my-local-dev" WITH SUPERUSER;

-- 3. Grant access to our user
GRANT ALL PRIVILEGES ON DATABASE vector_db TO "my-local-dev";
