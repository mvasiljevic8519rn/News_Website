package rs.raf.demo.services;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.repositories.kategorije.KategorijeRepository;

import javax.inject.Inject;
import java.util.List;

public class KategorijeService {

    public KategorijeService(){
        System.out.println(this);
    }
    @Inject
    private KategorijeRepository kategorijeRepository;

    public Integer max(){return this.kategorijeRepository.max();}
    public Kategorija addKategorija(Kategorija kategorija){return  this.kategorijeRepository.addKategorija(kategorija);}

    public Integer delteKategorija(Integer id){return this.kategorijeRepository.deleteKategorija(id);}

    public Kategorija getKategorija(Integer id){return this.kategorijeRepository.getKategorija(id);}

    public List<String> getAllKategorijaNames(){return this.kategorijeRepository.getAllKategorijaNames();}

    public List<Kategorija> getKategorije(){return  this.kategorijeRepository.getKategorije();}

    public List<Kategorija> getKategorije(Integer page){return  this.kategorijeRepository.getKategorije(page);}

    public Kategorija replaceKategorija(Kategorija kategorija){return this.kategorijeRepository.replaceKategorija(kategorija);}

    public String getKategorijaName(Integer id){return this.kategorijeRepository.getKategorijaName(id);}
}
