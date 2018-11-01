package com.jiangj.title;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Jiang Jian
 * @since 2018/10/13
 */
public class TitleLocalTestCreateEvent extends AbstractJavaSamplerClient {

    RestTemplate restTemplate = new RestTemplate();


    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        SampleResult result = new SampleResult();
        try {
            result.sampleStart();

            //=============start ========
            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            requestHeaders.setAccept(mediaTypes);
            ResponseEntity<String> responseEntity=restTemplate.
                    getForEntity("http://127.0.0.1:6060/ms/api/v1/title/search?name=四川",String.class);

         if (null != responseEntity && null != responseEntity.getBody()){
             String resultBody = responseEntity.getBody();
             result.setResponseData(resultBody,"utf-8");
             result.setSuccessful(true);
         }else {
             result.setResponseData("查询失败","utf-8");
             result.setSuccessful(false);
         }
         //=============end ========
        }catch (Exception e){
            result.setSuccessful(false);
            e.printStackTrace();
        }finally {
            result.sampleEnd();
        }
        return result;
    }

    public static void main(String[] args) {
        TitleLocalTestCreateEvent tets = new TitleLocalTestCreateEvent();
        Arguments arg = new Arguments();
        JavaSamplerContext context = new JavaSamplerContext(arg);
        SampleResult result = tets.runTest(context);

        System.out.println(new String(result.getResponseData()));
    }

}
