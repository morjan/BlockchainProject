package com.model.dao;

import com.model.pojo.TypeOperation;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class TypeOperation
 * @author lovet
 */
public interface TypeOperationDAO {
    
    /**
     * get a type of operation by its id
     * @param idTypeOperation
     * @return 
     */
    public TypeOperation getTypeOperationById(int idTypeOperation);
    
    /**
     * get the list of all type operations in the database
     * @return 
     */
    public List<TypeOperation> getAllTypeOperation();
}
