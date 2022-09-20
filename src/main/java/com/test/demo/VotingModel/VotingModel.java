package com.test.demo.VotingModel;

import javax.persistence.*;

@Entity
@Table(name="voting_result")
public class VotingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;
    private String candidate_name;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

  /*  public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }*/


    @Override
    public String toString() {
        return "VotingModel{" +
                "vote_id=" + vote_id +
                ", candidate_name='" + candidate_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
