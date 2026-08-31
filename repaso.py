import os
import glob
import re

base_path = 'E:/ExamenCR'

# 1. Agregar SLF4J y @Valid
search_path = os.path.join(base_path, '*-service/src/main/java/**/*.java')
files = glob.glob(search_path, recursive=True)

for f in files:
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    
    modified = False

    # A. En Servicios y Controladores, agregar @Slf4j
    if 'Service.java' in f or 'Controller.java' in f:
        if '@Slf4j' not in content:
            content = content.replace('import org.springframework.stereotype.Service;', 'import org.springframework.stereotype.Service;\nimport lombok.extern.slf4j.Slf4j;')
            content = content.replace('import org.springframework.web.bind.annotation.*;', 'import org.springframework.web.bind.annotation.*;\nimport lombok.extern.slf4j.Slf4j;')
            
            content = content.replace('@Service\npublic class', '@Slf4j\n@Service\npublic class')
            content = content.replace('@RestController\n', '@Slf4j\n@RestController\n')
            modified = True
            
            # Insertar un log en algun metodo
            if 'public List<' in content and 'log.info' not in content:
                content = re.sub(r'(public List<.*?> getAll\(\) {\n)', r'\1        log.info("Obteniendo todos los registros");\n', content)
            if 'public Optional<' in content and 'log.info' not in content:
                content = re.sub(r'(public Optional<.*?> findById\(Long id\) {\n)', r'\1        log.info("Buscando registro por ID: {}", id);\n', content)

    # B. En Controladores, agregar @Valid al @RequestBody
    if 'Controller.java' in f:
        if '@Valid' not in content:
            content = content.replace('import org.springframework.web.bind.annotation.*;', 'import org.springframework.web.bind.annotation.*;\nimport jakarta.validation.Valid;')
            content = content.replace('@RequestBody ', '@Valid @RequestBody ')
            modified = True

    # C. En Modelos, agregar @NotNull (Bean Validation JSR 380)
    if 'model' in f and '.java' in f:
        if '@NotNull' not in content:
            content = content.replace('import jakarta.persistence.*;', 'import jakarta.persistence.*;\nimport jakarta.validation.constraints.NotNull;\nimport jakarta.validation.constraints.NotBlank;')
            
            # Simple heuristic: add @NotNull to fields that are not id
            content = re.sub(r'(private String )', r'@NotBlank(message = "El campo no puede estar vacio")\n    \1', content)
            content = re.sub(r'(private Integer )', r'@NotNull\n    \1', content)
            content = re.sub(r'(private Double )', r'@NotNull\n    \1', content)
            modified = True

    if modified:
        with open(f, 'w', encoding='utf-8') as file:
            file.write(content)

print("Repaso de arquitectura completado: SLF4J, @Valid y @NotNull inyectados.")
