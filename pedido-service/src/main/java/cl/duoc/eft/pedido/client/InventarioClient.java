package cl.duoc.eft.pedido.client;
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
