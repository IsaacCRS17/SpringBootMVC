package irs.springbootmvc.service;

import irs.springbootmvc.model.Cuenta;

import java.util.List;

public interface ICuentaService {
    public List<Cuenta> listCuentas();

    public Cuenta searchCuentaById(Integer idCuenta);

    public void saveCuenta(Cuenta cuenta);

    public void deleteCuenta(Cuenta cuenta);

}
