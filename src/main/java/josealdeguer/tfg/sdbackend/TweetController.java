package josealdeguer.tfg.sdbackend;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;

    public TweetController(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @GetMapping("/tweets")
    List<Tweet> getTweets(
            @RequestParam(name="stance", required = false) String stance) {

        if(stance == null) {
            return tweetRepository.findAll();
        }
        else {
            return tweetRepository.findByStanceIgnoreCase(stance);
        }
    }

    @GetMapping("/tweets/{identifier}")
    Tweet getTweet(@PathVariable Integer identifier) {
        return tweetRepository.findByIdentifier(identifier);
    }

    @CrossOrigin
    @GetMapping("/countByStance")
    List<?> getCountGroupByStance() {
        return tweetRepository.countByStance();
    }

    @CrossOrigin
    @GetMapping("/countByDate")
    List<Tweet> getCountGroupByDate() {
        return (List<Tweet>) tweetRepository.countByDate();
    }

    @CrossOrigin
    @GetMapping("/countByStanceAndDate")
    List<Tweet> getCountGroupByStanceAndDate() {
        return (List<Tweet>) tweetRepository.countByStanceAndDate();
    }

//    @GetMapping("/tweetsByStanceGroupByDate")
//    List<Tweet> getTweetsByStanceAndDate() {
//        return (List<Tweet>) tweetRepository.findPrueba("hola");
//    }

//    @GetMapping("/import")
//    Boolean importTweets() {
//        String[] fields, data;
//        List<Tweet> tweets = new ArrayList<>();
//        Boolean saved = false;
//
//        try {
//            tweetRepository.deleteAll();
//
//            InputStream in = new FileInputStream("C:/Users/Jose/Dropbox/Infórmatica/TFG/Dataset_Cataluna/cataluna_all_predict_import.txt");
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
//                String line;
//                br.readLine();
//                while ((line = br.readLine()) != null && (!line.equals(""))) {
//                    //'fields' is an array containing 13 strings that will store all the information of the tweets (id, tweet_id, text, used... stance).
//                    //It's initialized each time so it doesn't keep data from previous iterations.
//                    fields = new String[13];
//                    //'data' is initialized with the data obtained from the txt file.
//                    data = line.split("\t");
//                    //'data' is copied into 'fields' so there are no empty fields in the array.
//                    System.arraycopy(data,0,fields,0,data.length);
//                    //In the following if-else blocks we parse the data types fields so they are stored with a correct format.
//                    Integer identifier;
//                    Date fecha;
//                    Boolean isRetweet;
//                    Integer retweets;
//                    Integer favorites;
//
//                    try {
//                        identifier = Integer.parseInt(fields[0]);
//                    } catch(NumberFormatException ex) {
//                        identifier = null;
//                    }
//                    try {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        fecha = sdf.parse(fields[5]);
//                    } catch(ParseException ex) {
//                        fecha = null;
//                    }
//                    try {
//                        isRetweet = Boolean.parseBoolean(fields[6]);
//                    } catch(NumberFormatException ex) {
//                        isRetweet = null;
//                    }
//                    try {
//                        retweets = Integer.parseInt(fields[7]);
//                    } catch(NumberFormatException ex) {
//                        retweets = null;
//                    }
//                    try {
//                        favorites = Integer.parseInt(fields[8]);
//                    } catch(NumberFormatException ex) {
//                        favorites = null;
//                    }
//
//                    tweets.add(new Tweet(
//                            identifier,
//                            fields[1],
//                            fields[2].replaceAll("^\"|\"$", ""),
//                            fields[3],
//                            fields[4],
//                            fecha,
//                            isRetweet,
//                            retweets,
//                            favorites,
//                            fields[9],
//                            fields[10],
//                            fields[11],
//                            fields[12]
//                    ));
//                }
//                saved = tweetRepository.saveAll(tweets).size() > 0;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return saved;
//    }
}
