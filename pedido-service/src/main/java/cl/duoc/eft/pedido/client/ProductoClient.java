package cl.duoc.eft.pedido.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", url = "http://localhost:8082/api/productos")
public interface ProductoClient {
    @GetMapping("/{id}")
    Object getProducto(@PathVariable("id") Long id);
}
