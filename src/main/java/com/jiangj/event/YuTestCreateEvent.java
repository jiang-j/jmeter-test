package com.jiangj.event;

import com.alibaba.fastjson.JSONObject;
import com.jiangj.event.vo.EventContent;
import com.jiangj.event.vo.EventVo;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Jiang Jian
 * @since 2018/10/13
 */
public class YuTestCreateEvent extends AbstractJavaSamplerClient {

    RestTemplate restTemplate = new RestTemplate();


    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        SampleResult result = new SampleResult();
        try {
            result.sampleStart();

            //=============start ========
            EventVo eventVo = new EventVo();
            eventVo.setBatchNo(getRandom());
            eventVo.setBusinessNo(getRandom());
            eventVo.setEventType("type1");
            eventVo.setServiceId("service1");
            EventContent content = new EventContent();
            content.setCustomerNo("okEvent");
            content.setDetail("测试"+Thread.currentThread().getName());
            content.setOriginatorNo("string");
            content.setSellerNo("test");
            content.setTenantCode("test");
            eventVo.setContent(content);

            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            requestHeaders.setAccept(mediaTypes);
            HttpEntity<?> requestEntity = new HttpEntity<Object>(eventVo, requestHeaders);

            ResponseEntity<String> responseEntity=restTemplate.
                    postForEntity("http://10.50.8.39:8083/serviceEvent/event/addSealedEvent",requestEntity,String.class);
            String body = responseEntity.getBody();
            JSONObject jsonObject = JSONObject.parseObject(body);
            int code = (int) jsonObject.get("code");
            if (1 == code){
                JSONObject jsonObject1 = (JSONObject)jsonObject.get("result");
                String eventNo = jsonObject1.getString("eventNo");
                Map<String,String> map = new HashMap<>(1);
                requestEntity = new HttpEntity<Object>(map, requestHeaders);
                Thread.sleep(1000);
                responseEntity=restTemplate.
                        postForEntity("http://10.50.8.39:8083/serviceEvent/event/confirmSend?eventNo="+eventNo,requestEntity,String.class);
                //System.out.println(responseEntity.getBody());
                result.setResponseData(responseEntity.getBody(),"utf-8");
                result.setDataType(SampleResult.TEXT);
                result.setSuccessful(true);
            }else {
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

    public static String getRandom() {
        Random r = new Random();
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +r.nextInt(1000000);
    }

    public static void main(String[] args) {
        YuTestCreateEvent tets = new YuTestCreateEvent();
        Arguments arg = new Arguments();
        JavaSamplerContext context = new JavaSamplerContext(arg);
        tets.runTest(context);
    }

}
