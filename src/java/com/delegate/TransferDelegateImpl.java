package com.delegate;

import blockchain.BlockchainAPI;
import com.model.dao.CurrenciesDAO;
import com.model.dao.RatesDAO;
import com.model.dao.TransferDAO;
import com.model.dao.UserDAO;
import com.model.pojo.Currency;
import com.model.pojo.Rate;
import com.model.pojo.Transfer;
import com.model.pojo.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lovet
 */
@Service
public class TransferDelegateImpl implements TransferDelegate {
    @Autowired
    TransferDAO transferDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CurrenciesDAO currencyDAO;
    @Autowired
    RatesDAO ratesDAO;
    
    
    @Override
    public void processTransfer(User userSender, double amountSender, int idUserRecipient) {
        User userRecipient = userDAO.getUserById(idUserRecipient);
        Currency currencySender = userSender.getCurrency();
        Currency currencyRecipient = userRecipient.getCurrency();
        Rate rateTransfer = ratesDAO.getRateForCurrencyFromAndCurrencyTo(currencySender, currencyRecipient);
        double amountRecipient = rateTransfer.getRate() * amountSender;
        String amountRecipientStr = String.format("%.2f", amountRecipient);
        double amountRecipient2Digits = Double.valueOf(amountRecipientStr);
        Date dateTransfer = new Date();
        Transfer newTransfer = new Transfer();
        
        newTransfer.setUserSender(userSender);
        newTransfer.setUserRecipient(userRecipient);
        newTransfer.setCurrencySender(currencySender);
        newTransfer.setCurrencyRecipient(currencyRecipient);
        newTransfer.setRateTansfer(rateTransfer.getRate());
        newTransfer.setAmountSender(amountSender);
        newTransfer.setAmountRecipient(amountRecipient2Digits);
        newTransfer.setDateTransfer(dateTransfer);
        userSender.setCredit(userSender.getCredit() - amountSender);
        userRecipient.setCredit(userRecipient.getCredit() + amountRecipient2Digits);
        
        BlockchainAPI blchain = new BlockchainAPI();
        blchain.addToBlock(newTransfer);
        
        transferDAO.saveTransfer(newTransfer);
        userDAO.updateUser(userSender);
        userDAO.updateUser(userRecipient);        
    }
    
    @Override
    public List<Transfer> getAllTransfersByUser(User user) {
        return transferDAO.getAllTransfersByUser(user);
    }
    
    @Override
    public List<Transfer> getTransferByUserForSearch(
            User user, String transferStatus, String contact, String dateFrom, String dateTo) {
        HashMap<String, Object> searchCriterias = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if(!transferStatus.isEmpty() && (transferStatus.equals("1") || transferStatus.equals("2"))) {
            searchCriterias.put("transferStatus", Integer.valueOf(transferStatus));
        }
        if(!contact.isEmpty()) {            
            searchCriterias.put("contact", userDAO.getUserById(Integer.valueOf(contact)));
        }
        if(!dateFrom.isEmpty()) {
            try {
                searchCriterias.put("dateFrom", dateFormat.parse(dateFrom));
            } catch (ParseException ex) {
                Logger.getLogger(BalanceDelegateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!dateTo.isEmpty()) {
            try {
                searchCriterias.put("dateTo", dateFormat.parse(dateTo));
            } catch (ParseException ex) {
                Logger.getLogger(BalanceDelegateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return transferDAO.getTransferByUserForSearch(user, searchCriterias);
    }
}
