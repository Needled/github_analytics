# github_analytics

This web-service takes a github user name and a repository name that belongs to this user and returns an analysis of the sentiment of open issues on this repository (only first page for now).
It interfaces with the github v3 (REST) API to retrieve the issue data and requires an Azure text analytics API connection that handles the sentiment analysis.
To run this service you need a working azure text analytics running resource. You need to specify the azure endpoint and access key parameters through the application properties (see template), as well as a valid OAUth access token from github (you can generate one with your github account, but the access token controls the access the service will have).

Working example (through OpenAPI UI):

![OpenAPI working demo](/working_demo.png?raw=true)
