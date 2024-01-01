# notifier

## Architecture

![Architecture](docs/img/architecture.png)

### request-reply
In charge of handling sync HTTP requests from external.

### notifier-backend
In charge of backend for notifier

### notifier-frontend
In charge of frontend for notifier

## Deployment

## Local run

### Install minikube
Follow [minikube get started](https://minikube.sigs.k8s.io/docs/start/) for installation instruction. 

minikube start
kubectl apply -f deployment.yml -f service.yml -f configure.yml


kubectl logs deployment.apps/nats
kubectl delete -f deployment.yml -f service.yml -f configure.yml
minikube service nats
nats context add nats-local --server 127.0.0.1:49915 --description "NATS local" --select

nats sub sms.order
nats pub sms.order "hello world" 
