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
192.168.49.2

minikube start
kubectl apply -f nats-configure.yml -f nats-deployment.yml -f nats-nodeport-svc.yml -f nats-svc.yml
kubectl delete -f nats-configure.yml -f nats-deployment.yml -f nats-nodeport-svc.yml -f nats-svc.yml

kubectl apply -f mongodb-svc.yml -f mongodb-deployment.yml -f mongodb-nodeport-svc.yml -f mongodb-pv.yml -f mongodb-pvc.yml -f mongodb-secrets.yml
kubectl delete -f mongodb-svc.yml -f mongodb-deployment.yml -f mongodb-nodeport-svc.yml -f mongodb-pv.yml -f mongodb-pvc.yml -f mongodb-secrets.yml

kubectl apply -f request-reply-deployment.yml -f request-reply-svc.yml
kubectl delete -f request-reply-deployment.yml -f request-reply-svc.yml

kubectl port-forward service/mongo-svc 27017:27017
kubectl port-forward service/nats-svc 4222:4222
kubectl port-forward service/request-reply-svc 8080:8080

kubectl logs deployment.apps/nats

nats context add nats-local --server 127.0.0.1:4222 --description "NATS 4222" --select

nats sub sms.order
nats pub sms.order "hello world"

docker build -t l3marc/request-reply:latest .
