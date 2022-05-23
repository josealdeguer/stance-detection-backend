package josealdeguer.tfg.sdbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@Slf4j
public class LoadData {

    @Bean
    CommandLineRunner initData(TweetRepository tweetRepository, CoronavirusRepository coronavirusRepository) {
        return args -> {
            String[] fields, data;
            List<Tweet> tweets = new ArrayList<>();
            Boolean saved = false;

            try {
                tweetRepository.deleteAll();

                InputStream in = new FileInputStream("C:/Users/Jose/Dropbox/Infórmatica/TFG/Dataset_Cataluna/cataluna_all_stance.txt");
                try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                    String line = "";
                    br.readLine();
                    while ((line = br.readLine()) != null && (!line.equals(""))) {
                        //'fields' is an array containing 13 strings that will store all the information of the tweets (id, tweet_id, text, used... stance).
                        //It's initialized each time so it doesn't keep data from previous iterations.
                        fields = new String[13];
                        //'data' is initialized with the data obtained from the txt file.
                        data = line.split("\t");
                        //'data' is copied into 'fields' so there are no empty fields in the array.
                        System.arraycopy(data,0,fields,0,data.length);
                        //In the following if-else blocks we parse the data types fields so they are stored with a correct format.
                        Integer identifier;
                        Date fecha;
                        Boolean isRetweet;
                        Integer retweets;
                        Integer favorites;

                        try {
                            identifier = Integer.parseInt(fields[0]);
                        } catch(NumberFormatException ex) {
                            identifier = null;
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            fecha = sdf.parse(fields[5]);
                        } catch(ParseException ex) {
                            fecha = null;
                        }
                        try {
                            isRetweet = Boolean.parseBoolean(fields[6]);
                        } catch(NumberFormatException ex) {
                            isRetweet = null;
                        }
                        try {
                            retweets = Integer.parseInt(fields[7]);
                        } catch(NumberFormatException ex) {
                            retweets = null;
                        }
                        try {
                            favorites = Integer.parseInt(fields[8]);
                        } catch(NumberFormatException ex) {
                            favorites = null;
                        }

                        tweets.add(new Tweet(
                                identifier,
                                fields[1],
                                fields[2].replaceAll("^\"|\"$", ""),
                                fields[3],
                                fields[4],
                                fecha,
                                isRetweet,
                                retweets,
                                favorites,
                                fields[9],
                                fields[10],
                                fields[11],
                                fields[12]
                        ));
                    }
                    saved = tweetRepository.saveAll(tweets).size() > 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String[] fieldsCoronavirus, dataCoronavirus;
            List<Coronavirus> tweetsCoronavirus = new ArrayList<>();
            Boolean savedCoronavirus = false;

            try {
                coronavirusRepository.deleteAll();

                InputStream in = new FileInputStream("C:/Users/Jose/Dropbox/Infórmatica/TFG/Dataset_Coronavirus/Final/coronavirus_all_stance.txt");
                try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                    String line = "";
                    br.readLine();
                    while ((line = br.readLine()) != null && (!line.equals(""))) {
                        //'fieldsCoronavirus' is an array containing 13 strings that will store all the information of the tweets (id, tweet_id, text, used... stance).
                        //It's initialized each time so it doesn't keep data from previous iterations.
                        fieldsCoronavirus = new String[16];
                        //'dataCoronavirus' is initialized with the data obtained from the txt file.
                        dataCoronavirus = line.split("\t");
//                        System.out.println(dataCoronavirus[0]);
                        //'dataCoronavirus' is copied into 'fieldsCoronavirus' so there are no empty fieldsCoronavirus in the array.
                        System.arraycopy(dataCoronavirus,0,fieldsCoronavirus,0,dataCoronavirus.length);
                        //In the following if-else blocks we parse the data types fieldsCoronavirus so they are stored with a correct format.
                        Integer identifier;
                        Date fecha;
                        Boolean isRetweet;
                        Integer retweets;
                        Integer favorites;
                        Boolean contains_media;
                        Double latitude;
                        Double longitude;

                        try {
                            identifier = Integer.parseInt(fieldsCoronavirus[0]);
                        } catch(NumberFormatException ex) {
                            identifier = null;
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            fecha = sdf.parse(fieldsCoronavirus[5]);
                        } catch(ParseException ex) {
                            fecha = null;
                        }
                        try {
                            isRetweet = Boolean.parseBoolean(fieldsCoronavirus[6]);
                        } catch(NumberFormatException ex) {
                            isRetweet = null;
                        }
                        try {
                            retweets = Integer.parseInt(fieldsCoronavirus[7]);
                        } catch(NumberFormatException ex) {
                            retweets = null;
                        }
                        try {
                            favorites = Integer.parseInt(fieldsCoronavirus[8]);
                        } catch(NumberFormatException ex) {
                            favorites = null;
                        }
                        try {
                            contains_media = Boolean.parseBoolean(fieldsCoronavirus[12]);
                        } catch(NumberFormatException ex) {
                            contains_media = null;
                        }
                        try {
                            latitude = Double.parseDouble(fieldsCoronavirus[13]);
                        } catch(NumberFormatException ex) {
                            latitude = null;
                        }
                        try {
                            longitude = Double.parseDouble(fieldsCoronavirus[14]);
                        } catch(NumberFormatException ex) {
                            longitude = null;
                        }

                        tweetsCoronavirus.add(new Coronavirus(
                                identifier,
                                fieldsCoronavirus[1],
                                fieldsCoronavirus[2].replaceAll("^\"|\"$", ""),
                                fieldsCoronavirus[3],
                                fieldsCoronavirus[4],
                                fecha,
                                isRetweet,
                                retweets,
                                favorites,
                                fieldsCoronavirus[9],
                                fieldsCoronavirus[10],
                                fieldsCoronavirus[11],
                                contains_media,
                                latitude,
                                longitude,
                                fieldsCoronavirus[15]
                        ));
                    }
                    savedCoronavirus = coronavirusRepository.saveAll(tweetsCoronavirus).size() > 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}
