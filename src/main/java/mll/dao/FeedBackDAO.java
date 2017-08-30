package mll.dao;

import mll.beans.*;
import mll.service.RazunaService;
import mll.utility.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nehas on 7/8/2017.
 */
public class FeedBackDAO {

    public JSONArray getAllFeedbacks() {
        // we add songs from db to this variable and return this
        Session session = null;
        Transaction tx = null;

        JSONArray feedbackArray = new JSONArray();

        List<Feedback> feedbacks = null;

        try
        {
            // Initialize the session and transaction
            session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            ObjectMapper mapper = new ObjectMapper();

            // query to get all feedbacks ids for id
            Query query = session.createQuery("FROM mll.beans.Feedback");

            feedbacks =  query.list();

            for(Feedback feedback:feedbacks){
                JSONObject obj = new JSONObject(mapper.writeValueAsString(feedback));
                feedbackArray.put(obj);
            }


            session.disconnect();
            return feedbackArray;

        } catch (Exception e) {
            e.printStackTrace();
            session.disconnect();
            return null;
        }

    }



    public void saveMetadata(Feedback feedback) {

//        if(feedback == null || feedback.getFeedbackMsg()==null || feedback.getFeedbackMsg().trim() == "")
//            return false;

        Session session = null;
        Transaction tx = null;
// id id auto yo i know :P have to write a hql query there .i.s . wsaooching
        // there is something call genrate native
        try
        {
            session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(feedback);
            session.getTransaction().commit();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        }
        catch(HibernateException e)
        {
            if( null != tx)
            {
                tx.rollback();
            }
//            return false;
            // Error message for integrity constraint violation
        }
        catch(Exception e)
        {
            if( null != tx)
            {
                tx.rollback();
            }
//            return false;
            //
        }

    }
}
