# pegasus-data

Repository for the Atlas Explorer Tool service layer

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
