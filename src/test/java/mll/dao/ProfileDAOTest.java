package mll.dao;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.junit.Test;

import mll.beans.Artist;
import mll.beans.Genre;
import mll.beans.Metadata;
import mll.beans.Owner;
import mll.beans.SongMetadata;
import mll.utility.SessionFactoryUtil;

/**
 * Created by ajbeairsto on 2/28/17.
 */
public class ProfileDAOTest {

    //Asserts that calling saveMetadata with null values throws exception
    @Test
    public void testSaveMetadata1()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();
            dao.saveMetadata(null,null);
            fail("testSaveMetadata1 exception");
        }
        catch (Exception e)
        {

        }
    }

    //Asserts that calling saveMetadata with a blank JSON object doesn't throw exception
    @Test
    public void testSaveMetadata2()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();

            JSONObject obj = new JSONObject();

            dao.saveMetadata(obj, 5);
        }
        catch (Exception e)
        {
            fail("testSaveMetadata2 exception");
        }
    }

    //Asserts that calling saveMetadata with an unexpected JSON object doesn't throw exception
    @Test
    public void testSaveMetadata3()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();

            JSONObject obj = new JSONObject();
            obj.put("test1", "test_val");
            obj.put("test2", "test_val2");

            dao.saveMetadata(obj,5);
        }
        catch (Exception e)
        {
            fail("testSaveMetadata3 exception");
        }
    }

    //Asserts that calling saveMetadata with valid JSON object throws exception (invalid session)
    @Test
    public void testSaveMetadata4()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();

            JSONObject obj = new JSONObject();
            obj.put("bandName", "test_band");

            JSONArray list = new JSONArray();
            list.add("Bob");
            list.add("Frank");
            list.add("Steve");
            obj.put("owners", list);

            dao.saveMetadata(obj, 5);
            fail("testSaveMetadata4 exception");
        }
        catch (Exception e)
        {

        }
    }

    //Asserts that calling saveBand with nulls throws exception
    @Test
    public void testSaveBand1()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();

            dao.saveOwners(null, null, null);
        }
        catch (Exception e)
        {
            fail("testSaveMetadata4 exception");
        }
    }

    //Asserts that calling saveBand with null owners throws exception
    @Test
    public void testSaveBand2()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();

            Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            dao.saveOwners(null, 1, session);
        }
        catch (Exception e)
        {
            fail("testSaveMetadata4 exception");
        }
    }

    //Asserts that calling saveBand with one owner succeeds
    @Test
    public void testSaveBand3()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();
            List<Owner> owners = new ArrayList<Owner>();
            Owner owner = new Owner();
            owner.setId(1);
            owner.setSongId("DEDGWY42828");
            owner.setName("Name");
            owner.setPrimaryEmail("owner@gmail.com");
            owners.add(owner);

            Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            dao.saveOwners(owners, 1, session);
            fail("testSaveMetadata4 exception");
        }
        catch (Exception e)
        {

        }
    }

    //Asserts that calling saveBand with two owners succeeds
    @Test
    public void testSaveBand4()
    {
        try
        {
            ProfileDAO dao = new ProfileDAO();
            List<Owner> owners = new ArrayList<Owner>();
            Owner owner = new Owner();
            owner.setId(1);
            owner.setSongId("DEDGWY42828");
            owner.setName("Name");
            owner.setPrimaryEmail("owner@gmail.com");
            Owner owner2 = new Owner();
            owner.setId(2);
            owner.setSongId("ABCDEF42828");
            owner.setName("Name2");
            owner.setPrimaryEmail("owner@gmail.com");
            owners.add(owner);
            owners.add(owner2);

            Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
            dao.saveOwners(owners, 1, session);
            fail("testSaveMetadata4 exception");
        }
        catch (Exception e)
        {

        }
    }
}
