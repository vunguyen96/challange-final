# foody-clone-deployment
## Deployment structure
```
~/app-deployment
    ├── base
    |   ├── kustomization.yaml
    |   ├── app-namespace.yaml
    |   ├── gateway
    |   |   ├── kustomization.yaml
    |   |   ├── traefik-service-acc.yaml
    |   |   ├── traefik-cr.yaml
    |   |   ├── traefik-deployment.yaml
    |   |   ├── .. 
    |   ├── .. ## other cross-cutting concerns
    ├── overlays
    |   ├── default
    |   |   ├── kustomization.yaml
    |   |   ├── .. ## other patch on base
    |   |   ├── .. 
    |   ├── staging
    |   |   ├── kustomization.yaml 
    |   |   ├── .. ## other patch on base
    |   |   ├── ..
    |   ├── .. ## other environments
    
```

This structure based on the official Kustomization proposal which make easier to isolate the environment and to patch the common deployment located on `base` directory.

For applying these setup into Kubernetes cluster, instead of `kubectl apply -f <kubernetes file>`, we can use `kubectl apply -k <kustomization directory>` which `-k` to let command enable the Kustomization
```bash
For staging environment, e.g : kubectl apply -k ~/app-deployment/overlays/staging
For default, e.g : kubectl apply -k ~/app-deployment/overlays/default
```

The idea is: `Kustomization` will merge all other Kubernetes mannifests into single file and deploy it to Kubernetes cluster. If we want to see how the merged file looks like, we can run:
```bash
kubectl kustomize ./overlays/default
kubectl kustomize ./overlays/staging
```

Then the output is printed out in the console, it merged into one separated by `---`

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: foody-clone
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: traefik-ingress
  namespace: foody-clone
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: traefik-ingress
rules:
- apiGroups:
  - ""
  resources:
  - services
  - endpoints
  - secrets
  verbs:
  - get
  - list
  - watch
- apiGroups:
  - extensions
  resources:
  - ingresses
  verbs:
  - get
  - list
  - watch
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: traefik-ingress
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: traefik-ingress
subjects:
- kind: ServiceAccount
  name: traefik-ingress
  namespace: foody-clone
---
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    k8s-app: traefik-ingress-lb
  name: traefik-ingress
  namespace: foody-clone
spec:
  replicas: 1
  selector:
    matchLabels:
      k8s-app: traefik-ingress-lb
  template:
    metadata:
      labels:
        k8s-app: traefik-ingress-lb
        name: traefik-ingress-lb
    spec:
      containers:
      - args:
        - --api
        - --kubernetes
        - --logLevel=INFO
        image: traefik:1.7
        name: traefik-ingress-lb
        ports:
        - containerPort: 80
          name: app-services
        - containerPort: 8080
          name: dashboard
      serviceAccountName: traefik-ingress
      terminationGracePeriodSeconds: 60
```