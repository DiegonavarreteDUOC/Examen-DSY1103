import os

base_path = 'E:/ExamenCR'
modules = ['usuario-service', 'producto-service']

usuario_test = '''package cl.duoc.eft.usuario.service;

import cl.duoc.eft.usuario.model.Usuario;
import cl.duoc.eft.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_Success() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1L);
        mockUsuario.setNombre("Test User");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockUsuario));

        Optional<Usuario> result = usuarioService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test User", result.get().getNombre());
    }

    @Test
    void save_Success() {
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre("New User");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(newUsuario);

        Usuario saved = usuarioService.save(newUsuario);
        assertEquals("New User", saved.getNombre());
        verify(usuarioRepository, times(1)).save(newUsuario);
    }
}
'''

os.makedirs(os.path.join(base_path, 'usuario-service/src/test/java/cl/duoc/eft/usuario/service'), exist_ok=True)
with open(os.path.join(base_path, 'usuario-service/src/test/java/cl/duoc/eft/usuario/service/UsuarioServiceTest.java'), 'w') as f:
    f.write(usuario_test)


producto_test = '''package cl.duoc.eft.producto.service;

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
'''
os.makedirs(os.path.join(base_path, 'producto-service/src/test/java/cl/duoc/eft/producto/service'), exist_ok=True)
with open(os.path.join(base_path, 'producto-service/src/test/java/cl/duoc/eft/producto/service/ProductoServiceTest.java'), 'w') as f:
    f.write(producto_test)

print("Tests injected successfully.")
