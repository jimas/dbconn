package com.jimas.dbconn.http;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jimas.dbconn.urlenum.UrlEnum;
import com.jimas.dbconn.util.MD5Util;

@Service
public class RestService {

    private static final Logger logger=Logger.getLogger(RestService.class);
    private RestTemplate restTemplate=new RestTemplate();
    @Value("${rest.key}")
    private String restKey;//rest 接口 key
    @Value("${rest.url}")
    private String restBaseUrl;//rest 接口 base url
    
    private int TRY_TIME=3;//重试次数
    /**
     * post 请求 返回 String
     * @param url
     * @param request
     * @return
     */
    public String postHttp(UrlEnum urlEnum,Object request,String siteSource){
        String url = restBaseUrl+urlEnum.getUrl()+"?token="+MD5Util.MD5Encode(restKey+siteSource);
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
        String url = restBaseUrl+urlEnum.getUrl();
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
