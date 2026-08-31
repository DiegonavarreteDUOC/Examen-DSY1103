package cl.duoc.eft.producto.service;

import cl.duoc.eft.producto.model.Producto;
import cl.duoc.eft.producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_Success() {
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Pan");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(mockProducto));

        Optional<Producto> result = productoService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Pan", result.get().getNombre());
    }
}
