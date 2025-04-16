package rs.raf.demo.repositories.tags;

import rs.raf.demo.entities.Tag;

import java.util.List;

public interface TagRepository {

    public List<Tag> tagsFromNews(int id);

    public void deleteTagsFromNews(int id);

    public void addTags(int id,String tags);
}
