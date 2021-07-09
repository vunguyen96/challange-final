#!/usr/bin/sh
cd ../foody-clone
docker build -t fooday .

cd ../driver
mvn clean package -Dquarkus.container-image.build=true -DskipTests -Dquarkus.container-image.group=""

cd ../foody-clone-configuration-server
mvn spring-boot:build-image -DskipTests

cd ../merchant
mvn clean package -Dquarkus.container-image.build=true -DskipTests -Dquarkus.container-image.group=""

cd ../order-service
mvn clean package -Dquarkus.container-image.build=true -DskipTests -Dquarkus.container-image.group=""

cd ../payment-service
mvn clean package -Dquarkus.container-image.build=true -DskipTests -Dquarkus.container-image.group=""

cd ..

cp -v driver/target/kubernetes/kubernetes.yml deployment/services/driver.yml
cp -v foody-clone-configuration-server/k8s.yaml deployment/services/configuration-server.yml
cp -v merchant/target/kubernetes/kubernetes.yml deployment/services/merchant.yml
cp -v order-service/target/kubernetes/kubernetes.yml deployment/services/order-service.yml
cp -v payment-service/target/kubernetes/kubernetes.yml deployment/services/payment-service.yml
cp -v foody-clone/frontend.yml deployment/services/frontend.yml

cd deployment
kubectl apply -k .