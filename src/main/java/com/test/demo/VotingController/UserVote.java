package com.test.demo.VotingController;
import com.test.demo.Repository.ResultDetails;
import com.test.demo.Repository.UserDetails;
import com.test.demo.VotingModel.UserModel;
import com.test.demo.VotingModel.VotingModel;
import com.test.demo.VotingServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller


public class UserVote {
    // UserVote uv=new UserVote();
    int user_id_copy;
    int i=0;
    @Autowired
    private UserDetails userDetails;
    @Autowired
    private ResultDetails resultDetails;
    @Autowired
    UserService userService;


    @GetMapping("/")

    public String home() {

        return "login";
    }

    @GetMapping("/register")
    public String addUser() {
        return "register";

    }

    @PostMapping("/register_user")
    public String registration(@ModelAttribute UserModel userModel) {
        System.out.print(userModel);
        userDetails.save(userModel);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String votecaste(@ModelAttribute UserModel userModel, @RequestParam("name") String name) throws SQLException {

        //String name= (String) session.getAttribute("name");
        System.out.println(name);
         i = userService.votecaste(userModel, name);




        if (i == 1) {
            return "votecount";
        } else {
            if (i ==0) {

                return "login";

            } else {
                if (i == -1) {
                    return "already_voted";
                } else {


                    return "castvote";
                }
            }
        }



    }


    @PostMapping("/vote_casted")
    public String vote_submit(@ModelAttribute VotingModel votingModel)  throws SQLException {
        System.out.println(i);

        userService.vote_submit(votingModel, i);


        return "login";
        //}

    }

    @PostMapping("/result")
    public String showResult(@ModelAttribute VotingModel votingModel) throws SQLException {
int result=userService.showResult(votingModel);


return "login";
    }

}



