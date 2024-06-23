package com.example.demo.Schedule;
import com.example.demo.entity.Enrollement;
import com.example.demo.entity.Paiement;
import com.example.demo.service.EnrollementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Component
public class PaimentSchedule {
    @Autowired
    private EnrollementService enrollementService ;
    private Date paiment_date ;

    private Paiement last_paiment ;
    private List<Paiement> payments ;
    private double duration ;
    @Scheduled(cron = "5 * * * * *")
    public void update_Paimenet(){

        for (Enrollement enrollement: enrollementService.getAllEnrollements()
             ) {


            duration = 1 ;


             payments = enrollement.getPaiement();
             if(!payments.isEmpty()) {
                 last_paiment = payments.get(payments.size()-1);
                 paiment_date = Date.from(last_paiment.getPaimentDate().atStartOfDay(ZoneId.systemDefault()).toInstant() );

                 System.out.println("paimentdate = "+paiment_date);
                 System.out.println("calculateDaysSince(paiment_date) = "+calculateDaysSince(paiment_date));

                 System.out.println("remain time = "+((duration*30)-calculateDaysSince(paiment_date)));

                 if ( (duration*30)-calculateDaysSince(paiment_date)   >0 ){

                     enrollement.setPaiement_Status("paid");

                 }
                 else  {

                 enrollement.setPaiement_Status("unpaid");

                 }
                 System.out.println(enrollement.getPaiement_Status());
                 enrollementService.updateEnrollement(enrollement);
             }

        }




    }


    //passed time
    private double calculateDaysSince(Date lastPaymentDate) {
        Calendar today = Calendar.getInstance();
        Calendar paymentDate = Calendar.getInstance();

        paymentDate.setTime(lastPaymentDate);

        long differenceInTime = today.getTimeInMillis() - paymentDate.getTimeInMillis();



        // Convert milliseconds to days, rounding up if necessary
        return Math.ceil((double) differenceInTime / (1000 * 60 * 60 * 24));



    }




}
