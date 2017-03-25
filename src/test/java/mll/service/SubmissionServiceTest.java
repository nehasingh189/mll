package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Metadata;


public class SubmissionServiceTest {
    @Test
    public void testPopulateSong() throws Exception {
        SubmissionService service = new SubmissionService();
        Metadata metadata = new Metadata();

        // Tests for the empty cases
        assertEquals(true, service.populateSong(null, null, null, null, null, null) == null);
        assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), null, null, null, null) == metadata);
        assertEquals(true, service.populateSong(metadata, null, getOwnerInfoJsonObject(), null, null, null) == metadata);

        // Tests that each metadata field is set properly
        Metadata populatedMetadata1 = service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), "dropboxURL", null, "myFilename");
        assertEquals(true, populatedMetadata1.getSongMetadata().getOwnerType().equals("testOwnerType"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getTrackType().equals("testTrackType"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getTitle().equals("testSongTitle"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getCopyright_number().equals("copyright"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getPublishing_company().equals("pubCompany"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getPro().equals("pro"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getPrimary_genre().equals("Alternative"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getSecondary_genre().equals("Classical"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getSourceOfContent().equals("DROPBOX"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getFileName().equals("myFilename"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getArtists().equals("ArtistName"));
        assertEquals(true, populatedMetadata1.getSongMetadata().getUserId() == 1L);
        assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), null, new byte[1024], null).getSongMetadata().getSourceOfContent().equals("HARDDRIVE"));
        assertEquals(true, service.populateSong(metadata, getGeneralInfoJsonObject(), getOwnerInfoJsonObject(), null, new byte[1024], null).getSongMetadata().getSourceOfContent().equals("HARDDRIVE"));
    }

    @Test
    public void testPopulateSongGenres() throws Exception {
        SubmissionService service = new SubmissionService();
        Metadata metadata = new Metadata();
        assertEquals(true, service.populateSongGenres(null, null) == null);
        assertEquals(true, service.populateSongGenres(metadata, null) == metadata);
        assertEquals(true, service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().size() == 2);
        assertEquals("Alternative", service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().get(0).getGenre());
        assertEquals("Classical", service.populateSongGenres(metadata, getGenresJsonObject()).getGenres().get(1).getGenre());
    }

    @Test
    public void testPopulateSongArtists() throws Exception {
        SubmissionService service = new SubmissionService();
        Metadata metadata = new Metadata();
        assertEquals(true, service.populateSongArtists(null, null) == null);
        assertEquals(true, service.populateSongArtists(metadata, null) == null);
        assertEquals(false, service.populateSongArtists(metadata, getArtistJsonObject()).length() == 1);
        assertEquals(true, service.populateSongArtists(metadata, getArtistJsonObject()).equals("ArtistName"));
    }

    @Test
    public void testPopulateSongWriters() throws Exception {
        SubmissionService service = new SubmissionService();
        Metadata metadata = new Metadata();
        assertEquals(true, service.populateSongWriters(null, null) == null);
        assertEquals(true, service.populateSongWriters(metadata, null) == metadata);
        assertEquals(true, service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().size() == 1);
        assertEquals("Test", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getName());
        assertEquals("primary@neu.edu", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getPrimaryEmail());
        assertEquals("123-456-7890", service.populateSongWriters(metadata, getWriterJsonObject()).getOwners().get(0).getPrimaryPhone());
    }

    @SuppressWarnings("unchecked")
    public JSONObject getGeneralInfoJsonObject() {
        JSONObject jor = new JSONObject();
        jor.put("name", "ArtistName");
        JSONArray artists = new JSONArray();
        artists.add(jor);
        JSONObject jo = new JSONObject();
        jo.put("title", "testSongTitle");
        jo.put("artists", artists);
        jo.put("userId", 1L);
        jo.put("ownerType", "testOwnerType");
        jo.put("trackType", "testTrackType");
        jo.put("primaryGenre", "Alternative");
        jo.put("secondaryGenre", "Classical");
        jo.put("pubCompany", "pubCompany");
        jo.put("pro", "pro");
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getOwnerInfoJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("copyright", "copyright");
        jo.put("pubCompany", "pubCompany");
        jo.put("pro", "pro");
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getGenresJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("primaryGenre", "Alternative");
        jo.put("secondaryGenre", "Classical");
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getArtistJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("name", "ArtistName");

        JSONArray artists = new JSONArray();
        artists.add(jo);

        JSONObject input = new JSONObject();
        input.put("artists", artists);
        return input;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getRecorderJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("name", "Test");
        jo.put("primaryEmail", "primary@neu.edu");
        jo.put("secondaryEmail", "secondary@neu.edu");
        jo.put("primaryPhone", "123-456-7890");
        jo.put("secondaryPhone", "012-345-6789");

        JSONArray recorders = new JSONArray();
        recorders.add(jo);

        JSONObject input = new JSONObject();
        input.put("soundOwners", recorders);
        return input;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getWriterJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("name", "Test");
        jo.put("primaryEmail", "primary@neu.edu");
        jo.put("secondaryEmail", "secondary@neu.edu");
        jo.put("primaryPhone", "123-456-7890");
        jo.put("secondaryPhone", "012-345-6789");

        JSONArray recorders = new JSONArray();
        recorders.add(jo);

        JSONObject input = new JSONObject();
        input.put("songwriters", recorders);
        return input;
    }
}

	
