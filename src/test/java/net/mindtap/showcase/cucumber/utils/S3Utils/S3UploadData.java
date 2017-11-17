package net.mindtap.showcase.cucumber.utils.S3Utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * Created by QAIT\Priyam on 27/6/17.
 */
public class S3UploadData {
   static String bucketName="cengage-product-analytics-rta-qa";
   static AmazonS3 s3client;

    public static void uploadData(String fileName, String filePath) {
        System.out.println("*************File/pck Details**************");
        System.out.println("fileName "+fileName);
        System.out.println("filePath "+filePath);
        System.out.println("***************************************");
        initiateS3Connection();

        s3client.putObject(new PutObjectRequest(bucketName, fileName,new File(filePath)));

        System.out.println("File Pushed To S3 Bucket : "+bucketName);

    }

    private static void initiateS3Connection() {
        // Programmatically set credentials
        AWSCredentials credentials = new BasicAWSCredentials("AKIAJHHMYM4CE6UNPXAA", "3wCH8W+6ND4t+awP8Gr25MaQjwfjnt7vw9mWmgLU");

        //Create S3 Client
        s3client= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("us-east-1").build();
    }


    public static void retriveData(String fileName, String outputPath) {
        initiateS3Connection();
//        s3client.

    }

}
