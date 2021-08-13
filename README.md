# pegasus-data

Repository for the Atlas Explorer Tool service layer

# Relevant Knowledge Environment Tables

* [Atlas Explorer UI Database Depencies](https://github.com/KPMP/KPMP-Documentation/blob/develop/atlasExplorerUIDBDependencies.md)

* [Atlas Explorer Database Depencies](https://github.com/KPMP/KPMP-Documentation/blob/develop/atlasExplorerDBDependencies.md)

# Test

`./gradlew test`

# Build

`./gradlew build docker`

# Restart Spring

`docker kill spring`

`cd ~/proj/heavens-docker/atlas/knowledge-environment`

`docker-compose -f docker-compose.dev.yml up -d`

# View Spring Logs

`docker-compose -f docker-compose.dev.yml logs --f spring`

# Connect to QA database

1. Update .env ENV_MYSQL_HOST to the IP address of QA KE
2. Update .env ENV_MYSQL_PASSWORD to the password for QA KE
3. Add your IP address to a new security group called sg-0597e054f11d2e31a (Developer to KE Mariadb) 
4. Restart your knowledge-environment (don't worry that you have your own copy of mariadb, it'll be ignored)
