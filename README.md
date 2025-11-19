📦 Application Repository README

This repository contains the Spring Boot sample application, its Docker image build configuration, and the Kubernetes manifests required for deploying it to an Amazon EKS cluster.
It also includes a CI/CD pipeline that automatically builds, containerizes, and pushes the application to Amazon ECR.

This repository focuses ONLY on deploying the application to an existing EKS cluster.
Infrastructure (VPC, EKS, IAM, networking) is fully managed by the separate Infrastructure Repository:

🔗 https://github.com/jatharthan/aws-fintech-infra-cicd-pipeline

📌 Current Working Commit SHA

889907d488baa186244c82ef51fa832cf2e41ac0

This commit represents the stable point before introducing GitOps using ArgoCD.

🔍 Overview

This repository contains:

1. Spring Boot Application

Standard Maven-based structure

pom.xml for dependency and build management

Minimal REST endpoints for sample demonstration

2. Dockerfile

Used to build a Docker image of the Spring Boot application.

3. GitHub Actions CI/CD Pipeline

The pipeline:

Builds

Tags

Pushes Docker images to AWS ECR

4. Kubernetes Manifests

deployment.yaml

service.yaml

These files are used to manually deploy or update pods in the EKS cluster.

🚀 CI/CD Pipeline Behavior

This repository uses GitHub Actions for building and deploying the application.

Pipeline Flow

Checkout Code

Set up JDK & Maven

Build & Test Spring Boot App

Build Docker Image

Authenticate to ECR

Tag & Push Image to ECR

Deploy to EKS Cluster

Updates the existing Deployment to the newly pushed image

Uses kubectl to apply manifests

What Triggers the Pipeline?

Push to main branch (configurable)

🔑 Prerequisites

Before using this repo, ensure:

1. ECR Repository Exists

Example:
<aws_account_id>.dkr.ecr.<region>.amazonaws.com/springboot-app
(Managed by infra repo.)

2. EKS Cluster Is Created

(Managed by infra repo.)

3. GitHub Secrets Configured
Secret Name	Description
AWS_ACCESS_KEY_ID	Access key for CI user
AWS_SECRET_ACCESS_KEY	Secret key for CI user
AWS_REGION	Deployment region
ECR_REPOSITORY	ECR URI without tag
CLUSTER_NAME	Name of EKS cluster
4. IAM Permissions for CI/CD User

Must allow:

ECR push

EKS DescribeCluster

STS AssumeRole

EKS authentication

kubectl actions via IAM Authenticator

📁 Kubernetes Deployment

This repo contains:

1. deployment.yaml

Defines:

Pods

Container image (updated automatically by pipeline)

Replicas

Environment variables (none yet)

Labels/selectors

2. service.yaml

Defines:

NodePort or LoadBalancer service

Connects external traffic to cluster pods

Manual First-Time Deployment

Apply both manifests manually the first time:

kubectl apply -f deployment.yaml
kubectl apply -f service.yaml

Check pod status
kubectl get pods
kubectl get svc

🔄 Updating the Application

Simply push code to main.

Pipeline will:

Build and package the app

Build a Docker image

Push to ECR

Update Deployment with new image

Roll out pods

Verify update:

kubectl rollout status deployment/springboot-app

🧪 Testing Locally
Build JAR:
mvn clean package

Run locally:
mvn spring-boot:run

Build Docker image locally:
docker build -t springboot-app .

🗺️ Future Enhancements (Planned)

GitOps with ArgoCD

Deployments will be managed automatically via GitOps

Kubernetes manifests will move to a dedicated GitOps repo

Helm Charts

Replace raw YAML manifests

Allow parameterized deployments

Multiple Environments

dev / stage / prod folders

Environment-specific values

🎯 Summary

This repository handles:

✔ Spring Boot app source code
✔ Dockerization
✔ CI/CD pipeline to build & push images
✔ Kubernetes deployment to EKS
✔ Future-ready for GitOps + ArgoCD migration