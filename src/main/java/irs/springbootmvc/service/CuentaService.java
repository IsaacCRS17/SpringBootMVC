package irs.springbootmvc.service;

import irs.springbootmvc.model.Cuenta;
import irs.springbootmvc.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements ICuentaService{

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> listCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta searchCuentaById(Integer idCuenta) {
        return cuentaRepository.findById(idCuenta).orElse(null);
    }

    @Override
    public void saveCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    @Override
    public void deleteCuenta(Cuenta cuenta) {
        cuentaRepository.delete(cuenta);
    }
}
