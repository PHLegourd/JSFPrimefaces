package org.formation.primefaces.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.formation.primefaces.Car;
import org.formation.primefaces.CarService;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "dtView")
@ViewScoped
public class View implements Serializable {

	private static final long serialVersionUID = 5469369578019340557L;

	private List<Car> cars;
	private Car selectedCar;

	@ManagedProperty("#{carService}")
	private CarService service;

	@PostConstruct
	public void init() {
		cars = service.createCars(30);
	}

	public List<Car> getCars() {
		return cars;
	}

	public List<String> getBrands() {
		return service.getBrands();
	}

	public List<String> getColors() {
		return service.getColors();
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public void setService(CarService service) {
		this.service = service;
	}
    public void deleteCar() {
        cars.remove(selectedCar);
        selectedCar = null;
    }
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
