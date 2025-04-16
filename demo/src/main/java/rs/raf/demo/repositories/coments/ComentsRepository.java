package rs.raf.demo.repositories.coments;

import rs.raf.demo.entities.Coment;

import java.util.List;

public interface ComentsRepository {
    public  Integer max(Integer id);
    public Coment addComent(Coment coment);
    public void deleteComent(Integer comentId);
    public void deleteAllComents(Integer newsID);
    public Coment getComentWihtId(Integer comentId);
    public List<Coment> getAllComentFromNewsWithId(Integer newsId ,Integer page);


}

