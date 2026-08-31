import os

base_group = "cl.duoc.eft"

# Define the models for each service
services = {
    "usuario-service": [
        {"name": "Usuario", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("String", "nombre", ""), ("String", "email", ""), ("String", "password", ""), ("String", "rol", "")]}
    ],
    "producto-service": [
        {"name": "Producto", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("String", "codigo", ""), ("String", "nombre", ""), ("String", "descripcion", ""), ("Double", "precio", "")]},
        {"name": "Categoria", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("String", "nombre", "")]}
    ],
    "inventario-service": [
        {"name": "Inventario", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "productoId", ""), ("Integer", "cantidad", ""), ("Integer", "nivelMinimo", "")]}
    ],
    "carrito-service": [
        {"name": "Carrito", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "usuarioId", "")]}
    ],
    "pedido-service": [
        {"name": "Pedido", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "usuarioId", ""), ("Double", "total", ""), ("String", "estado", "")]}
    ],
    "pago-service": [
        {"name": "Pago", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "pedidoId", ""), ("Double", "monto", ""), ("String", "estado", "")]}
    ],
    "reparto-service": [
        {"name": "Reparto", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "pedidoId", ""), ("Long", "repartidorId", ""), ("String", "estado", "")]}
    ],
    "promocion-service": [
        {"name": "Promocion", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("String", "codigo", ""), ("Double", "porcentaje", ""), ("Boolean", "activa", "")]}
    ],
    "devolucion-service": [
        {"name": "Devolucion", "fields": [("Long", "id", "@Id @GeneratedValue(strategy = GenerationType.IDENTITY)"), ("Long", "pedidoId", ""), ("String", "motivo", ""), ("String", "estado", "")]}
    ],
    "reporte-service": [
        # Reporte service won't have standard DB models, it'll fetch from others. We'll skip for now.
    ]
}

exception_handler = """package {package}.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
"""

application_yml_template = """server:
  port: 0 # random port for local dev or configured by gateway
spring:
  application:
    name: {app_name}
  datasource:
    url: jdbc:h2:mem:{db_name};DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
"""

for service_dir, models in services.items():
    # Package path
    pkg_name = service_dir.replace("-service", "").replace("-", "")
    full_pkg = f"{base_group}.{pkg_name}"
    base_path = os.path.join(service_dir, "src", "main", "java", *full_pkg.split("."))
    
    # Update application.yml
    yml_path = os.path.join(service_dir, "src", "main", "resources", "application.yml")
    if os.path.exists(os.path.dirname(yml_path)):
        with open(yml_path, "w", encoding="utf-8") as f:
            f.write(application_yml_template.format(app_name=service_dir, db_name=pkg_name))

    if not models:
        continue

    # Create subpackages
    for sub in ["model", "repository", "service", "controller", "exception", "dto"]:
        os.makedirs(os.path.join(base_path, sub), exist_ok=True)
    
    # Write Exception Handler
    with open(os.path.join(base_path, "exception", "GlobalExceptionHandler.java"), "w", encoding="utf-8") as f:
        f.write(exception_handler.replace('{package}', full_pkg))
    
    for model in models:
        m_name = model["name"]
        m_name_lower = m_name.lower()
        
        # 1. Model
        fields_str = ""
        for typ, name, annot in model["fields"]:
            if annot:
                fields_str += f"    {annot}\n"
            fields_str += f"    private {typ} {name};\n"
            
        model_code = f"""package {full_pkg}.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "{m_name_lower}s")
public class {m_name} {{
{fields_str}
}}
"""
        with open(os.path.join(base_path, "model", f"{m_name}.java"), "w", encoding="utf-8") as f:
            f.write(model_code)
            
        # 2. Repository
        repo_code = f"""package {full_pkg}.repository;

import {full_pkg}.model.{m_name};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface {m_name}Repository extends JpaRepository<{m_name}, Long> {{
}}
"""
        with open(os.path.join(base_path, "repository", f"{m_name}Repository.java"), "w", encoding="utf-8") as f:
            f.write(repo_code)
            
        # 3. Service
        service_code = f"""package {full_pkg}.service;

import {full_pkg}.model.{m_name};
import {full_pkg}.repository.{m_name}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class {m_name}Service {{

    @Autowired
    private {m_name}Repository repository;

    public List<{m_name}> findAll() {{
        return repository.findAll();
    }}

    public Optional<{m_name}> findById(Long id) {{
        return repository.findById(id);
    }}

    public {m_name} save({m_name} entity) {{
        return repository.save(entity);
    }}

    public void deleteById(Long id) {{
        repository.deleteById(id);
    }}
}}
"""
        with open(os.path.join(base_path, "service", f"{m_name}Service.java"), "w", encoding="utf-8") as f:
            f.write(service_code)
            
        # 4. Controller
        controller_code = f"""package {full_pkg}.controller;

import {full_pkg}.model.{m_name};
import {full_pkg}.service.{m_name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{m_name_lower}s")
public class {m_name}Controller {{

    @Autowired
    private {m_name}Service service;

    @GetMapping
    public ResponseEntity<List<{m_name}>> getAll() {{
        return ResponseEntity.ok(service.findAll());
    }}

    @GetMapping("/{{id}}")
    public ResponseEntity<{m_name}> getById(@PathVariable Long id) {{
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }}

    @PostMapping
    public ResponseEntity<{m_name}> create(@RequestBody {m_name} entity) {{
        return ResponseEntity.ok(service.save(entity));
    }}

    @PutMapping("/{{id}}")
    public ResponseEntity<{m_name}> update(@PathVariable Long id, @RequestBody {m_name} entity) {{
        return service.findById(id).map(existing -> {{
            entity.setId(existing.getId());
            return ResponseEntity.ok(service.save(entity));
        }}).orElse(ResponseEntity.notFound().build());
    }}

    @DeleteMapping("/{{id}}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {{
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }}
}}
"""
        with open(os.path.join(base_path, "controller", f"{m_name}Controller.java"), "w", encoding="utf-8") as f:
            f.write(controller_code)

print("Scaffolding completed.")

