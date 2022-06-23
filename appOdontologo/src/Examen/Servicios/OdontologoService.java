package Examen.Servicios;

import Examen.Daos.IDao;
import Examen.Entidades.Odontologo;

import java.util.List;


public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public IDao<Odontologo> getOdontologoIDao(){
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public void eliminar(Long id){
        odontologoIDao.eliminar(id);
        }

    public Odontologo buscar(Long id){
        return odontologoIDao.buscar(id);
    }

    public List<Odontologo> listarTodo(){

        return odontologoIDao.listarTodo();
    }
}
