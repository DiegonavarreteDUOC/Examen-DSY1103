package cl.duoc.eft.usuario.service;

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
