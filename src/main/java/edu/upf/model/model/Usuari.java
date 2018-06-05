package edu.upf.model.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MODEL_USUARI")
@Getter
@Setter
@EqualsAndHashCode(of="nis")
public class Usuari {

	@Id
    @Column(name = "NIS")
    private Long nis;
     
    @Column(name = "NOMCOMPLET")
    private String nomComplet;
     
    @Column(name = "DEFAULT_IDIOMA")
    private String defaultIdioma;
    
    @Column(name = "DEFAULT_ROL")
    private String defaultRol;
    
    @Column(name = "USERALTA")
    private Long userAlta;
    
    @Column(name = "DATAALTA")
    private Date dataAlta;
    
    @Column(name = "USERMODI")
    private Long userModi;
    
    @Column(name = "DATAMODI")
    private Date dataModi;
    
    /* L'Usuari és l'owning side de la relació Many2Many amb els rols */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "MODEL_USUARI_ROL",
            joinColumns = { @JoinColumn(name = "NIS") },
            inverseJoinColumns = { @JoinColumn(name = "IDROL") })
    private Set<Rol> rols = new HashSet<>();
    
}
