import os

base_path = 'E:/ExamenCR'
modules = ['api-gateway', 'usuario-service', 'producto-service', 'inventario-service', 'carrito-service', 'pedido-service', 'pago-service', 'reparto-service', 'promocion-service', 'devolucion-service', 'reporte-service']
ports = {
    'api-gateway': '8080',
    'usuario-service': '8081',
    'producto-service': '8082',
    'inventario-service': '8083',
    'carrito-service': '8084',
    'pedido-service': '8085',
    'pago-service': '8086',
    'reparto-service': '8087',
    'promocion-service': '8088',
    'devolucion-service': '8089',
    'reporte-service': '8090'
}

dockerfile_content = """FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
"""

compose_content = 'version: "3.8"\nservices:\n'

for mod in modules:
    # 1. Crear Dockerfile
    with open(os.path.join(base_path, mod, 'Dockerfile'), 'w', encoding='utf-8') as f:
        f.write(dockerfile_content)
    
    # 2. Agregar al docker-compose
    port = ports[mod]
    compose_content += f"""  {mod}:
    build: ./{mod}
    ports:
      - "{port}:{port}"
    restart: on-failure
"""

# 3. Guardar docker-compose.yml
with open(os.path.join(base_path, 'docker-compose.yml'), 'w', encoding='utf-8') as f:
    f.write(compose_content)

print('Docker files created.')
