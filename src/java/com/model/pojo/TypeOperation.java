package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity TypeOperation in the database
 * @author lovet
 */
@Entity
@Table(name = "TypeOperation", catalog = "MA9627S")
public class TypeOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typeOperation", unique = true/*, nullable = false*/)
    private int idTypeOperation;
    @Column(name = "name_typeOperation")
    private String nameTypeOperation;

    
    public TypeOperation() {
    }

    public TypeOperation(int idTypeOperation, String nameTypeOperation) {
        this.idTypeOperation = idTypeOperation;
        this.nameTypeOperation = nameTypeOperation;
    }
    

    public int getIdTypeOperation() {
        return idTypeOperation;
    }

    public void setIdTypeOperation(int idTypeOperation) {
        this.idTypeOperation = idTypeOperation;
    }

    public String getNameTypeOperation() {
        return nameTypeOperation;
    }

    public void setNameTypeOperation(String nameTypeOperation) {
        this.nameTypeOperation = nameTypeOperation;
    }

    @Override
    public String toString() {
        return "TypeOperation{" + "idTypeOperation=" + idTypeOperation + ", nameTypeOperation=" + nameTypeOperation + '}';
    }
}
