package josealdeguer.tfg.sdbackend;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Tweet {
    @Id @GeneratedValue
    private Integer jpaId;
    private Integer identifier;
    private String tweet_id;
    @Column(columnDefinition="TEXT")
    private String text;
    private String user;
    private String gathered_at;
    private Date fecha;
    private Boolean isRetweet;
    private Integer retweets;
    private Integer favorites;
    private String user_location;
    private String country;
    private String city;
    private String stance;

    public Tweet() {}

    public Tweet(Integer identifier, String tweet_id, String text, String stance) {
        this.identifier = identifier;
        this.tweet_id = tweet_id;
        this.text = text;
        this.stance = stance;
    }

    public Tweet(Integer identifier, String tweet_id, String text, String user, String gathered_at, Date fecha, Boolean isRetweet, Integer retweets, Integer favorites, String user_location, String country, String city, String stance) {
        this.identifier = identifier;
        this.tweet_id = tweet_id;
        this.text = text;
        this.user = user;
        this.gathered_at = gathered_at;
        this.fecha = fecha;
        this.isRetweet = isRetweet;
        this.retweets = retweets;
        this.favorites = favorites;
        this.user_location = user_location;
        this.country = country;
        this.city = city;
        this.stance = stance;
    }
}
