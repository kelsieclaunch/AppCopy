package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USERNAME = field("User", "username");
  public static final QueryField AGE = field("User", "age");
  public static final QueryField ACTIVITY_LEVEL = field("User", "activity_level");
  public static final QueryField HEIGHT = field("User", "height");
  public static final QueryField SEX = field("User", "sex");
  public static final QueryField WEIGHT = field("User", "weight");
  public static final QueryField PICTURE = field("User", "picture");
  public static final QueryField BMR = field("User", "bmr");
  public static final QueryField LOCATION = field("User", "location");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String username;
  private final @ModelField(targetType="Int") Integer age;
  private final @ModelField(targetType="String") String activity_level;
  private final @ModelField(targetType="String") String height;
  private final @ModelField(targetType="String") String sex;
  private final @ModelField(targetType="Int") Integer weight;
  private final @ModelField(targetType="String") String picture;
  private final @ModelField(targetType="Int") Integer bmr;
  private final @ModelField(targetType="String") String location;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public Integer getAge() {
      return age;
  }
  
  public String getActivityLevel() {
      return activity_level;
  }
  
  public String getHeight() {
      return height;
  }
  
  public String getSex() {
      return sex;
  }
  
  public Integer getWeight() {
      return weight;
  }
  
  public String getPicture() {
      return picture;
  }
  
  public Integer getBmr() {
      return bmr;
  }
  
  public String getLocation() {
      return location;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String username, Integer age, String activity_level, String height, String sex, Integer weight, String picture, Integer bmr, String location) {
    this.id = id;
    this.username = username;
    this.age = age;
    this.activity_level = activity_level;
    this.height = height;
    this.sex = sex;
    this.weight = weight;
    this.picture = picture;
    this.bmr = bmr;
    this.location = location;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getAge(), user.getAge()) &&
              ObjectsCompat.equals(getActivityLevel(), user.getActivityLevel()) &&
              ObjectsCompat.equals(getHeight(), user.getHeight()) &&
              ObjectsCompat.equals(getSex(), user.getSex()) &&
              ObjectsCompat.equals(getWeight(), user.getWeight()) &&
              ObjectsCompat.equals(getPicture(), user.getPicture()) &&
              ObjectsCompat.equals(getBmr(), user.getBmr()) &&
              ObjectsCompat.equals(getLocation(), user.getLocation()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getAge())
      .append(getActivityLevel())
      .append(getHeight())
      .append(getSex())
      .append(getWeight())
      .append(getPicture())
      .append(getBmr())
      .append(getLocation())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("age=" + String.valueOf(getAge()) + ", ")
      .append("activity_level=" + String.valueOf(getActivityLevel()) + ", ")
      .append("height=" + String.valueOf(getHeight()) + ", ")
      .append("sex=" + String.valueOf(getSex()) + ", ")
      .append("weight=" + String.valueOf(getWeight()) + ", ")
      .append("picture=" + String.valueOf(getPicture()) + ", ")
      .append("bmr=" + String.valueOf(getBmr()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      age,
      activity_level,
      height,
      sex,
      weight,
      picture,
      bmr,
      location);
  }
  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep username(String username);
    BuildStep age(Integer age);
    BuildStep activityLevel(String activityLevel);
    BuildStep height(String height);
    BuildStep sex(String sex);
    BuildStep weight(Integer weight);
    BuildStep picture(String picture);
    BuildStep bmr(Integer bmr);
    BuildStep location(String location);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String username;
    private Integer age;
    private String activity_level;
    private String height;
    private String sex;
    private Integer weight;
    private String picture;
    private Integer bmr;
    private String location;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          username,
          age,
          activity_level,
          height,
          sex,
          weight,
          picture,
          bmr,
          location);
    }
    
    @Override
     public BuildStep username(String username) {
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep age(Integer age) {
        this.age = age;
        return this;
    }
    
    @Override
     public BuildStep activityLevel(String activityLevel) {
        this.activity_level = activityLevel;
        return this;
    }
    
    @Override
     public BuildStep height(String height) {
        this.height = height;
        return this;
    }
    
    @Override
     public BuildStep sex(String sex) {
        this.sex = sex;
        return this;
    }
    
    @Override
     public BuildStep weight(Integer weight) {
        this.weight = weight;
        return this;
    }
    
    @Override
     public BuildStep picture(String picture) {
        this.picture = picture;
        return this;
    }
    
    @Override
     public BuildStep bmr(Integer bmr) {
        this.bmr = bmr;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.location = location;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String username, Integer age, String activityLevel, String height, String sex, Integer weight, String picture, Integer bmr, String location) {
      super.id(id);
      super.username(username)
        .age(age)
        .activityLevel(activityLevel)
        .height(height)
        .sex(sex)
        .weight(weight)
        .picture(picture)
        .bmr(bmr)
        .location(location);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder age(Integer age) {
      return (CopyOfBuilder) super.age(age);
    }
    
    @Override
     public CopyOfBuilder activityLevel(String activityLevel) {
      return (CopyOfBuilder) super.activityLevel(activityLevel);
    }
    
    @Override
     public CopyOfBuilder height(String height) {
      return (CopyOfBuilder) super.height(height);
    }
    
    @Override
     public CopyOfBuilder sex(String sex) {
      return (CopyOfBuilder) super.sex(sex);
    }
    
    @Override
     public CopyOfBuilder weight(Integer weight) {
      return (CopyOfBuilder) super.weight(weight);
    }
    
    @Override
     public CopyOfBuilder picture(String picture) {
      return (CopyOfBuilder) super.picture(picture);
    }
    
    @Override
     public CopyOfBuilder bmr(Integer bmr) {
      return (CopyOfBuilder) super.bmr(bmr);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
  }
  
}
