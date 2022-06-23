package Examen.Test;

import Examen.Daos.Impl.OdontologoDaoH2;
import Examen.Entidades.Odontologo;
import Examen.Servicios.OdontologoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    OdontologoService O = new OdontologoService(new OdontologoDaoH2());

    @Test
    public void listarTodoTest() {
        // Creamos odontologos
        Odontologo A = new Odontologo(1L, 1, "Maria", "Perez");
        Odontologo B = new Odontologo(2L, 2, "Ana", "Lopez");
        Odontologo C = new Odontologo(3L, 3, "lucas", "Suarez");
        // Corremos los metodos
        O.guardarOdontologo(A);
        O.guardarOdontologo(B);
        O.guardarOdontologo(C);

        assertTrue(O.listarTodo().size() > 0 && O.listarTodo().size() < 4);
    }
}
