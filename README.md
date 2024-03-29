# pegasus-data

Repository for the Atlas Explorer Tool service layer

# Relevant Knowledge Environment Tables

* [Atlas Explorer UI Database Dependencies](https://github.com/KPMP/KPMP-Documentation/blob/develop/atlasExplorerUIDBDependencies.md)

* [Atlas Explorer Database Dependencies](https://github.com/KPMP/KPMP-Documentation/blob/develop/atlasExplorerDBDependencies.md)

# Test

`./gradlew test`

# Build

`./gradlew build docker`
The default tag is the github branch name if no verison is provided
To pass a version when building the docker image execute
`./gradlew build docker -Ptag=<tagNumber>`

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

If you have already followed these steps and are still having trouble connecting, check to see if your ip address has changed, you may need to update the security group rules

# Talking to GraphQL 

1. Start up your atlas/knowledge-environment server
2. Open a tool like GraphQLPlayground and connect to "http://localhost:3030/graphql"
3. Write and execute your query

# Buyer BEWARE
Notes to ourselves about the non-dynamic nature of some things in the app (this is not exhaustive)

1. The endpoint `getDataTypeInformationByParticipant` is only partially dynamic. If we add new data types to the Spatial Viewer, it will pick those up without an issue, BUT if/when we add new data types to the Explorer, we will need to update the code that gets counts for data types in Explorer to include the new one.
