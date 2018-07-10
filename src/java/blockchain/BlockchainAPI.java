/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import com.model.pojo.Transfer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;



/**
 *
 * @author lovet
 */
public class BlockchainAPI {
    
    public BlockchainAPI() {
    }
    
    
    public void addToBlock(Transfer transfer) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult = new JSONObject();
        
        try {
            jsonObject.put("userSender", transfer.getUserSender().getIdUser());
            jsonObject.put("userRecipient", transfer.getUserRecipient().getIdUser());
            jsonObject.put("currencySender", transfer.getCurrencySender().getIdCurrency());
            jsonObject.put("currencyRecipient", transfer.getCurrencyRecipient());
            jsonObject.put("rateTransfer", transfer.getRateTansfer());
            jsonObject.put("amountSender", transfer.getAmountSender());
            jsonObject.put("amountRecipient", transfer.getAmountRecipient());
            jsonObject.put("dateTransfer", transfer.getDateTransfer());
            jsonResult.put("data", jsonObject);
            
           
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://localhost:3001/mineBlock");
            StringEntity params = new StringEntity(jsonResult.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
            
            
            
            
            
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BlockchainAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlockchainAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
