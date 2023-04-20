package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void testObjectMapper() throws JsonProcessingException {
        String json = "{\"uid\":\"123456789\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"major\":\"Computer Science\",\"gradTerm\":\"Fall\",\"gradYear\":2021}";
        Student student = new Student();
        student.setUid("123456789");
        student.setFirst_name("John");
        student.setLast_name("Doe");
        student.setMajor("Computer Science");
        student.setGrad_term("Fall");
        student.setGrad_year(2021);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String parsedJson = mapper.writeValueAsString(student);

        System.out.println("json:" + json);
        System.out.println("parsedJson:" + parsedJson);

        assertEquals(json, parsedJson);
    }

}