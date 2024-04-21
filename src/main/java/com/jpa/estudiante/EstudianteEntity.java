package com.jpa.estudiante;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "estudiante", schema = "registro", catalog = "")


public class EstudianteEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idestudiante")
    private int idestudiante;
    @Basic
    @Column(name = "carnet")
    private String carnet;
    @Basic
    @Column(name = "nombres")
    private String nombres;

    @Basic
    @Column(name = "apellidos")
    private String apellidos;



    //UNION 1-1
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idestudiante")
    @MapsId
    private CredencialEntity credencial;


    public EstudianteEntity() {
    }

    public EstudianteEntity(int idestudiante, String carnet, String nombres, String apellidos, CredencialEntity credencial) {
        this.idestudiante = idestudiante;
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.credencial = credencial;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudianteEntity that = (EstudianteEntity) o;
        return idestudiante == that.idestudiante && Objects.equals(carnet, that.carnet) && Objects.equals(nombres, that.nombres) && Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idestudiante, carnet, nombres, apellidos);
    }
}
