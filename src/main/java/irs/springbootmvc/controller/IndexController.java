package irs.springbootmvc.controller;

import irs.springbootmvc.model.Cuenta;
import irs.springbootmvc.service.CuentaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    CuentaService cuentaService;
    private Cuenta cuentaSeleccionada;

    private List<Cuenta> cuentas;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.cuentas = cuentaService.listCuentas();
        cuentas.forEach((cuenta)-> logger.info(cuenta.toString()));
    }

    public void agregarCuenta(){
        this.cuentaSeleccionada = new Cuenta();
    }

    public void guardarCuenta(){
        logger.info("Cuenta a guardar: "+this.cuentaSeleccionada);

        //Agregar
        if(this.cuentaSeleccionada.getIdCuenta()==null){
            this.cuentaService.saveCuenta(this.cuentaSeleccionada);
            this.cuentas.add(this.cuentaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Agregada"));
        } else { //Modificar
            this.cuentaService.saveCuenta(this.cuentaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Modificada"));
        }
        //Ocultamos la ventana
        PrimeFaces.current().executeScript("PF('ventanaModalCuenta').hide()");
        //Actualizamos la tabla
        PrimeFaces.current().ajax().update("form-cuentas:mensajes", "form-cuentas:cuentas-table");
        this.cuentaSeleccionada=null;
    }

    public void eliminarCuenta(){
        this.cuentaService.deleteCuenta(this.cuentaSeleccionada);
        this.cuentas.remove(this.cuentaSeleccionada);
        this.cuentaSeleccionada=null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta Eliminada"));
        PrimeFaces.current().ajax().update("form-cuentas:mensajes", "form-cuentas:cuentas-table");
    }
}
