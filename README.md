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

notifier-backend:32002
request-reply:32001
notifier:32000

--no-cache
docker build --no-cache -t l3marc/notifier-backend:latest .
docker build --no-cache -t l3marc/notifier-frontend:latest .
docker build --no-cache -t l3marc/request-reply:latest .

docker image push --all-tags l3marc/notifier-backend
docker image push --all-tags l3marc/notifier-frontend
docker image push --all-tags l3marc/request-reply

minikube start
kubectl apply -f nats-configure.yml -f nats-deployment.yml -f nats-svc.yml
kubectl delete -f nats-configure.yml -f nats-deployment.yml -f nats-svc.yml

kubectl apply -f mongodb-svc.yml -f mongodb-deployment.yml -f mongodb-pv.yml -f mongodb-pvc.yml -f mongodb-secrets.yml
kubectl delete -f mongodb-svc.yml -f mongodb-deployment.yml -f mongodb-pv.yml -f mongodb-pvc.yml -f mongodb-secrets.yml

kubectl apply -f request-reply-deployment.yml -f request-reply-svc.yml -f request-reply-nodeport-svc.yml
kubectl delete -f request-reply-deployment.yml -f request-reply-svc.yml -f request-reply-nodeport-svc.yml

kubectl apply -f notifier-backend-deployment.yml -f notifier-backend-svc.yml -f notifier-backend-nodeport-svc.yml
kubectl delete -f notifier-backend-deployment.yml -f notifier-backend-svc.yml -f notifier-backend-nodeport-svc.yml

kubectl apply -f notifier-frontend-deployment.yml -f notifier-frontend-nodeport-svc.yml -f notifier-frontend-svc.yml
kubectl delete -f notifier-frontend-deployment.yml -f notifier-frontend-nodeport-svc.yml -f notifier-frontend-svc.yml



kubectl port-forward service/mongo-svc 27017:27017
kubectl port-forward service/nats-svc 4222:4222

kubectl port-forward service/request-reply-svc 32001:8080
kubectl port-forward service/notifier-backend-svc 32002:8081
kubectl port-forward service/notifier-frontend-svc 32000:80

kubectl logs deployment.apps/nats
kubectl logs deployment.apps/notifier-backend

nats context add nats-local --server 127.0.0.1:4222 --description "NATS 4222" --select

nats sub sms.order
nats pub sms.order "hello world"

docker image push --all-tags l3marc/notifier-backend
docker image push --all-tags l3marc/notifier-frontend

minikube start --driver=docker --mount --mount-string $(PWD)/dev/volumes:/volumes


