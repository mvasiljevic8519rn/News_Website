package rs.raf.demo.services;

import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.tags.TagRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TagService {
    public TagService(){
        System.out.println(this);
    }
    @Inject
    private TagRepository tagRepository;

    public List<Tag> allTagsFromNews(int id){return this.tagRepository.tagsFromNews(id);}

    public void deleteTagsFromNews(int id){this.tagRepository.deleteTagsFromNews(id);}

    public void addTagsToNews(int id, String tags){this.tagRepository.addTags(id,tags);}
}
