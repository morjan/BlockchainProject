package com.delegate;

import com.model.pojo.Transfer;
import com.model.pojo.User;
import java.util.List;

/**
 *
 * @author lovet
 */
public interface TransferDelegate {
    
    public void processTransfer(User userSender, double amountSender, int idUserRecipient);
    
    public List<Transfer> getAllTransfersByUser(User user);
    
    public List<Transfer> getTransferByUserForSearch(User user, String transferStatus, String contact, String dateFrom, String dateTo);
}
