package com.app.restful.service;

import com.app.restful.domain.CongestionData;
import com.app.restful.domain.CongestionResponse;
import com.app.restful.domain.PetTourDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenApiService {

    @Value("${spring.api.base-url}")
    private String baseUrl;

    @Value("${spring.api.service-key}")
    private String serviceKey;

    @Value("${spring.api.area-based-List}")
    private String areaBasedList;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<PetTourDTO> petTourList = new ArrayList<PetTourDTO>();

//    레거시
    public List<PetTourDTO> fetchData() throws IOException {
//    URL
        String urlStr = UriComponentsBuilder.fromUriString(baseUrl + areaBasedList)
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "testApp")
                .queryParam("_type", "json")
                .build().toString();

        URL url = new URL(urlStr);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        br.close();
        JsonNode jsonResponse = objectMapper.readTree(result.toString());

        if(jsonResponse.has("error")) {
            return null;
        }
        JsonNode itemsArray = jsonResponse.get("response").get("body").get("items").get("item");

        for(JsonNode item : itemsArray) {
            log.info("{}", item);
            PetTourDTO petTourDTO = new PetTourDTO();
            petTourDTO.setAreaCode(item.get("areacode").asText());
            petTourDTO.setTitle(item.get("title").asText());
            petTourDTO.setContentId(item.get("contentid").asText());
            petTourDTO.setAddress(item.get("addr1").asText());
            petTourDTO.setFirstImage1(item.get("firstimage").asText());
            petTourDTO.setFirstImage2(item.get("firstimage2").asText());
            petTourDTO.setTel(item.get("tel").asText());
            petTourDTO.setZipCode(item.get("zipcode").asText());
            petTourList.add(petTourDTO);
        }

        log.info("PetTourList : {}", petTourList);
        return petTourList;
    }

//    Rest Templates
    @Value("${spring.api.base-url2}")
    private String baseUrl2;

    @Value("${spring.api.congestion-20171231}")
    private String congestion20171231;

    public List<CongestionData> fetchData2() throws IOException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

//        URL
        String url = baseUrl2 + congestion20171231;

        String fullUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", serviceKey)
                .queryParam("page", 1)
                .queryParam("perPage", 10)
                .queryParam("_type", "json")
                .build().toString();

        URI uri = new URI(fullUrl);

        CongestionResponse response = restTemplate.getForObject(uri, CongestionResponse.class);
        log.info("{}", response);

        List<CongestionData> datas = response.getData();
        return datas;
    }
}
