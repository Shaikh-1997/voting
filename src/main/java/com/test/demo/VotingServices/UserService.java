package com.test.demo.VotingServices;

import com.test.demo.Repository.ResultDetails;
import com.test.demo.Repository.UserDetails;
import com.test.demo.VotingModel.UserModel;
import com.test.demo.VotingModel.VotingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    // int user_id_copy;

   // UserService us = new UserService();
    @Autowired

    private static UserDetails userDetails;

    @Autowired
    private ResultDetails resultDetails;


    public void addUser(UserModel userModel) {

        userDetails.save(userModel);
    }

    public void addResult(VotingModel votingModel, int user_id_copy) {
        resultDetails.save(votingModel);

    }


    /*   public int registration(UserModel userModel) throws SQLException {
       Connection con = null;
       Statement stmnt = null;
       int i, d = 0;

       try {

           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "lenovo");
           System.out.println("here");
           String insert_query = "insert into user_voting_model (name,password,email,phone)value ('" + userModel.getName() + "','" + userModel.getPassword() + "','" + userModel.getEmail() + "','" + userModel.getPhone() + "');";
           System.out.println(insert_query);
           stmnt = con.prepareStatement(insert_query);
           i = stmnt.executeUpdate(insert_query);
           System.out.println(i);


       } catch (Exception m) {
           System.out.println(m);


       } finally {

           con.close();
           stmnt.close();


       }
       return userModel.getUser_id();


   }

*/
    public int votecaste(UserModel userModel, String get_name) throws SQLException {
        Connection con = null;
        //Statement st = null;
        int i= 0;
        int user_id_copy=0;
       int  user_id_voting_result=0;
String name= null;
String password=null;
        System.out.println(get_name);

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "lenovo");
            String get_user_id="select user_id, name ,password from user_voting_model where name='"+get_name+"';";
            PreparedStatement ps = con.prepareStatement(get_user_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                user_id_copy=rs.getInt("user_id");
                name=rs.getString("name");
               password =rs.getString("password");
            }

        String check_user_id = "select user_id  from voting_result where user_id='" + user_id_copy + "';";
                PreparedStatement ps1 = con.prepareStatement(check_user_id);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    //   int user_id_voting_result;

                    user_id_voting_result = rs1.getInt("user_id");
                }
                if(user_id_copy==1) {
                    return user_id_copy;
                }
                else {
                    if  (user_id_copy == user_id_voting_result && user_id_copy>1){
                        return user_id_copy=-1;
                    }
                    else{
                        if(user_id_copy == user_id_voting_result ){
                            return user_id_copy=0;
                        }
                        else{

                           if( user_id_voting_result==0 && user_id_copy>1){
                               return user_id_copy;

                           }
                        }
                }
            }
        } catch (Exception m) {
            System.out.println(m);


        } finally {

            con.close();
           // st.close();


        }
       return user_id_copy;


    }



    public int vote_submit(VotingModel votingModel, int get_user_id) throws SQLException {


        Connection con = null;
        Statement st = null;
        int i, d = 0;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "lenovo");
            System.out.println("Result connection establishes");
           //String check_user_id="select user_ id and candidate_name from voting_result where user_id='get_user_id';";
           // PreparedStatement ps = con.prepareStatement(check_user_id);
           // ResultSet rs = ps.executeQuery();


            String insert_query = "insert into voting_result (candidate_name,user_id)value ('" + votingModel.getCandidate_name() + "','"+get_user_id+"');";

            System.out.println(insert_query);
            st = con.prepareStatement(insert_query);
            st.executeUpdate(insert_query);
            // System.out.println(i);


        } catch (Exception m) {
            System.out.println(m);


        } finally {

            con.close();
            st.close();


        }
        return votingModel.getVote_id();


    }

    public int showResult(VotingModel votingModel) throws SQLException {
        Connection con = null;


        Statement stmnt = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "lenovo");
            stmnt = con.createStatement();
            ResultSet rs;
            String query_result = "  SELECT  candidate_name, COUNT(*)FROM voting_result GROUP BY candidate_name ORDER BY candidate_name asc;";
            rs = stmnt.executeQuery(query_result);
            int total_vote = 0;
            List<Integer> list= new ArrayList<>();
            while (rs.next()) {
                String names = rs.getString("candidate_name");
                //   int i=1;
                list.add(rs.getInt("count(*)"));
                int vote_count = rs.getInt("count(*)");


                total_vote = total_vote + vote_count;
                System.out.println(names + " " + vote_count);

            }

            System.out.println("total_vote " + total_vote);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close();
            stmnt.close();
        }
        return 1;

    }


}


   // }


