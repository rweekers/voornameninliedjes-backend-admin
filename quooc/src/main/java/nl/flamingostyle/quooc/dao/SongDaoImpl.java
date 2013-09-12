package nl.flamingostyle.quooc.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import nl.flamingostyle.quooc.domain.Song;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SongDaoImpl {

    private Log log = LogFactory.getLog(SongDaoImpl.class);

    private SessionFactory sessionFactory;

    @PostConstruct
    public void setup() throws Throwable
    {
        System.out.println("setup()");
    }

    @SuppressWarnings("unchecked")
    public List<Song> listSongs()
    {
        try
        {
            return (List<Song>) sessionFactory.getCurrentSession()
                    .createCriteria(Song.class).list();

        } catch (Exception e)
        {
            log.fatal(e.getMessage());
            return null;
        }
    }
	
}


