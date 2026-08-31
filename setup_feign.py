import os
import glob

base_path = 'E:/ExamenCR'
modules = ['usuario-service', 'producto-service', 'inventario-service', 'carrito-service', 'pedido-service', 'pago-service', 'reparto-service', 'promocion-service', 'devolucion-service', 'reporte-service']

# 1. Add @EnableFeignClients to all Application classes
for mod in modules:
    search_path = os.path.join(base_path, mod, 'src/main/java/**/*.java')
    files = glob.glob(search_path, recursive=True)
    for f in files:
        if 'Application.java' in f:
            with open(f, 'r', encoding='utf-8') as file:
                content = file.read()
            if '@EnableFeignClients' not in content:
                content = content.replace('import org.springframework.boot.autoconfigure.SpringBootApplication;', 
                                          'import org.springframework.boot.autoconfigure.SpringBootApplication;\nimport org.springframework.cloud.openfeign.EnableFeignClients;')
                content = content.replace('@SpringBootApplication', '@SpringBootApplication\n@EnableFeignClients')
                with open(f, 'w', encoding='utf-8') as file:
                    file.write(content)

# 2. Create Feign Clients for Pedido Service
pedido_client_dir = os.path.join(base_path, 'pedido-service/src/main/java/cl/duoc/eft/pedido/client')
os.makedirs(pedido_client_dir, exist_ok=True)

inventario_client = '''package cl.duoc.eft.pedido.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventario-service", url = "http://localhost:8083/api/inventarios")
public interface InventarioClient {
    @GetMapping("/{id}")
    Object getInventario(@PathVariable("id") Long id);
    
    @PutMapping("/descontar")
    void descontarStock(@RequestParam("productoId") Long productoId, @RequestParam("cantidad") Integer cantidad);
}
'''
with open(os.path.join(pedido_client_dir, 'InventarioClient.java'), 'w') as f: f.write(inventario_client)

producto_client = '''package cl.duoc.eft.pedido.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", url = "http://localhost:8082/api/productos")
public interface ProductoClient {
    @GetMapping("/{id}")
    Object getProducto(@PathVariable("id") Long id);
}
'''
with open(os.path.join(pedido_client_dir, 'ProductoClient.java'), 'w') as f: f.write(producto_client)

print('Feign configs injected successfully.')
