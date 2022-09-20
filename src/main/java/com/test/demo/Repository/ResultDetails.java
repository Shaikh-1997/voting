package com.test.demo.Repository;


import com.test.demo.VotingModel.VotingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultDetails extends JpaRepository<VotingModel, Integer> {

}
