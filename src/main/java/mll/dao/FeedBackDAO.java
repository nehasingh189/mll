package mll.dao;

import mll.beans.*;
import mll.service.RazunaService;
import mll.utility.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nehas on 7/8/2017.
 */
public class FeedBackDAO {

    public List<Feedback> getAllFeedbacks() {
        // we add songs from db to this variable and return this
        Session session = null;
        Transaction tx = null;
        List<Feedback> feedbacks = null;

        try
        {
            // Initialize the session and transaction
            session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            // query to get all feedbacks ids for id
            Query query = session.createQuery("FROM mll.beans.Feedback");

            feedbacks =  query.list();

            // Commit the transaction if all the data is successfully saved
            tx.commit();
        }
        catch(Exception e)
        {
            if(null != tx)
            {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }

       return feedbacks;

    }


    public boolean addSongPlaylist(Feedback feedback)
    {
        if(feedback == null || feedback.getFeedbackMsg()==null || feedback.getFeedbackMsg().trim() == "")
            return false;

        Session session = null;
        Transaction tx = null;

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
            return false;
            // Error message for integrity constraint violation
        }
        catch(Exception e)
        {
            if( null != tx)
            {
                tx.rollback();
            }
            return false;
            //
        }

        return true;
    }
}
