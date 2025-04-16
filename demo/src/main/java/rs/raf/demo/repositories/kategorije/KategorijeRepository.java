package rs.raf.demo.repositories.kategorije;

import rs.raf.demo.entities.Kategorija;

import java.util.List;

public interface KategorijeRepository {
    public Integer max();
    public Kategorija  addKategorija(Kategorija kategorija);
    public Integer deleteKategorija(Integer id);
    public String getKategorijaName(Integer id);
    public Kategorija getKategorija(Integer id);
    public List<String> getAllKategorijaNames();
    public Kategorija replaceKategorija(Kategorija kategorija);
    public List<Kategorija> getKategorije();
    public List<Kategorija> getKategorije(Integer page);

}
