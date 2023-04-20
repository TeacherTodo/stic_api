package edu.nau.stic_api;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class S3_Helper
{
    private static String accessKey = "CHANGE ME";

    private static String secretKey = "CHANGE ME";

    private static AWSCredentials credentials;
    private static AmazonS3 s3client;

    public static void setup()
    {
        credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public static boolean uploadFile(String guid, String fileExtension, byte data[]) throws IOException
    {
        FileOutputStream stream = new FileOutputStream("/tmp/" + guid + "." + fileExtension);
        stream.write(data);

        s3client.putObject("nau-stic-capstone", "Document/" + guid + "." + fileExtension,
                new File("/tmp/" + guid + "." + fileExtension));

        Path path = Paths.get("/tmp/" + guid + "." + fileExtension);
        Files.deleteIfExists(path);

        return true;
    }

    public static byte[] downloadFile(String guid, String fileExtension) throws IOException
    {
        S3Object object = s3client.getObject("nau-stic-capstone", "Document/" + guid + "." + fileExtension);
        S3ObjectInputStream inputStream = object.getObjectContent();
        return inputStream.readAllBytes();
    }
}
