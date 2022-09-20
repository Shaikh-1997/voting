package com.test.demo.Repository;

import com.test.demo.VotingModel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




    @Repository
    public interface UserDetails extends JpaRepository<UserModel, Integer> {
    }


