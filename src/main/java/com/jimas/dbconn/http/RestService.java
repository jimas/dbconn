package com.jimas.dbconn.http;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jimas.dbconn.sourceconfig.ParamsConfig;
import com.jimas.dbconn.urlenum.UrlEnum;
import com.jimas.dbconn.util.MD5Util;

@Service
public class RestService {

    private static final Logger logger=Logger.getLogger(RestService.class);
    private RestTemplate restTemplate=new RestTemplate();
    @Autowired
    private ParamsConfig paramsConfig;
    
    private int TRY_TIME=3;//重试次数
    /**
     * post 请求 返回 String
     * @param url
     * @param request
     * @return
     */
    public String postHttp(UrlEnum urlEnum,Object request,String siteSource){
        String url = paramsConfig.getRestBaseUrl()+urlEnum.getUrl()+"?token="+MD5Util.MD5Encode(paramsConfig.getRestKey()+siteSource);
        String json ="";
        int time=1;
        for(int i=0;i<TRY_TIME;i++){
            try {
                json=restTemplate.postForObject(url, request, String.class);
                return json;
            } catch (Exception e) {
                if(time>=TRY_TIME){
                    logger.error(url+"重试了"+TRY_TIME+"次也失败了,request["+request+"]", e);
                    break;
                }
                time++;
            }
        }
        return json;
        
    }
    
    @SuppressWarnings("unchecked")
    public Object postHttp(UrlEnum urlEnum,Object request,@SuppressWarnings("rawtypes") Class clazz){
        String url = paramsConfig.getRestBaseUrl()+urlEnum.getUrl();
        Object json =null;
        int time=1;
        for(int i=0;i<TRY_TIME;i++){
            try {
                json=restTemplate.postForObject(url, request, clazz);
                return json;
            } catch (Exception e) {
                if(time>=TRY_TIME){
                    logger.error(url+"重试了"+TRY_TIME+"次也失败了,request["+request+"]", e);
                    break;
                }
                time++;
            }
        }
        return json;
        
    }
    
}
