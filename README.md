
# Compute Meta-service 

Initial Commit for the Compute Meta-Service includes models for tracking requests, including software catalog and hardware configurations on different cloud providers. 

Includes Quartz scheduler for the workflow implementation later. 

Terraform APIs: https://www.terraform.io/docs/enterprise/api/configuration-versions.html#upload-configuration-files
https://learn.hashicorp.com/terraform/getting-started/change

compute meta-serivce wiki: http://confluenceconsumerbanking.us.bank-dns.com:8092/display/AIML/Compute+Request

# Setting up postgres

to setup postgres, install brew, then run:

```
brew install postgres

echo 'export PATH="/usr/local/opt/postgresql@9.6/bin:$PATH"' >> ~/.bash_profile

pg_ctl -D /usr/local/var/postgresql@9.6 start

createdb computemeta

createuser -P -s computemeta

```

# Catalog API

```
GET /catalog?cpuCount=<..>&gpuCount=<..>&gpuType=[NVIDIA|NONE]&ramCount=<..>&cloudProvider=[AWS|GCP|USBANK]
```

 finds configurations available with given parameters satisfied. If no parameters are specified, returns all configurations: 

```json
[
  {
    "cloudProvider": {
      "id": "AWS",
      "curatedSoftwares": [
        {
          "id": "9e0b96f2-d1e5-42e0-bb1e-3c90d1abd1f9",
          "name": "name1",
          "uri": "uri://1"
        },
        {
          "id": "01e0d303-45b4-44fa-942f-9a06137bfc58",
          "name": "name2",
          "uri": "uri://2"
        }
      ]
    },
    "cpuCount": 1,
    "ramCount": 1,
    "gpuType": "NVIDIA",
    "gpuCount": 1,
    "displayName": "Machine 1"
  },
  {
    "cloudProvider": {
      "id": "GCP",
      "curatedSoftwares": [
        {
          "id": "674c1457-967d-4434-a7ff-0aa2bb11f220",
          "name": "name3",
          "uri": "uri://3"
        },
        {
          "id": "fe763503-e795-44a1-b19d-23511ba52604",
          "name": "name4",
          "uri": "uri://4"
        }
      ]
    },
    "cpuCount": 4,
    "ramCount": 16,
    "gpuType": "NVIDIA",
    "gpuCount": 2,
    "displayName": "Machine 2"
  }
]
```

# Create Request API

```
POST /request/create
```

Posts a ComputeRequest.java to the endpoint. The curateMachine comes from the catalog. The curatedSoftwares on the ComputeRequest come from the softwares available for that machine. If the RequestUser isn't provided, one will be created automatically. User info available in select * from request_users; 

```json
{
	"curatedMachine" : {
    "id": "46c23aa7-d425-4d60-bd42-7c263899d4be",
    "cloudProvider": {
      "id": "AWS",
      "curatedSoftwares": [
        {
          "id": "ab45dd79-2d45-4a30-ba01-6d11603bb751",
          "name": "name1",
          "uri": "uri://1"
        },
        {
          "id": "a23df2db-3f01-4d19-89ad-d9233e4753ee",
          "name": "name2",
          "uri": "uri://2"
        }
      ]
    },
    "cpuCount": 1,
    "ramCount": 1,
    "gpuType": "NVIDIA",
    "gpuCount": 1,
    "displayName": "Machine 1"
  },
	"curatedSoftwares": [
        {
          "id": "ab45dd79-2d45-4a30-ba01-6d11603bb751",
          "name": "name1",
          "uri": "uri://1"
        },
        {
          "id": "a23df2db-3f01-4d19-89ad-d9233e4753ee",
          "name": "name2",
          "uri": "uri://2"
        }
	],
	"numberOfMachines" : 1
}
```

# Deployment 

Deploy to Staging environment configs with 
```
java -jar artifacts/stage/demo-0.0.1-SNAPSHOT-stage.jar --spring.profiles.active=stage
```
