# order-service project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running application in Kubernetes

Let's package the application as Docker image.
```shell script
./mvnw clean package -Dquarkus.container-image.build=true
```
Once successfully, the image `order-service` will be created. Verify by list all Docker images.
```shell script
docker images
```

Then just deploy to Kubernetes cluster!
```shell script
kubectl apply -f target/kubernetes/kubernetes.yaml
```

To verify the application is running, we can forward port 8080 inside container to our localhost..
```shell script
kubectl port-forward service/order-service 8181:8080 <namespace-if-needed>
```

..and call order API.
```shell script
curl http://localhost:8181/api/orders
```