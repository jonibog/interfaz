package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Factura {
    @Id
    private Integer id;

    private String nro1;
    private String nro2;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cliente comprador;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario vendedor;

    @OneToMany
    @JoinTable(
                name="factura_detalle",
                joinColumns={ @JoinColumn(name="factura", referencedColumnName="id") },
                inverseJoinColumns={ @JoinColumn(name="detalleFactura", referencedColumnName="id", unique=true) }
            )
    private List<DetalleFactura> detalles;
}
