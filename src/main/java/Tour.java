import org.bson.types.ObjectId;

import java.util.Objects;

public class Tour {
    private ObjectId _id;
    private String id;
    private String keys;
    private String name;
    private String description;
    private int price;
    private String imgUri;


    public Tour(String id, String keys, String name, String description, int price, String imgUri) {
        this.id = id;
        this.keys = keys;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUri = imgUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (price != tour.price) return false;
        if (id != null ? !id.equals(tour.id) : tour.id != null) return false;
        if (keys != null ? !keys.equals(tour.keys) : tour.keys != null) return false;
        if (name != null ? !name.equals(tour.name) : tour.name != null) return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null) return false;
        return imgUri != null ? imgUri.equals(tour.imgUri) : tour.imgUri == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keys != null ? keys.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (imgUri != null ? imgUri.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id='" + id + '\'' +
                ", keys='" + keys + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgUri='" + imgUri + '\'' +
                '}';
    }
}
