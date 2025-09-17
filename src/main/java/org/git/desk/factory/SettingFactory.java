package org.git.desk.factory;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import jakarta.inject.Named;
import jakarta.inject.Qualifier;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.model.AppPreferences;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Factory
@Slf4j
@Lazy
public class SettingFactory {

  @Bean
  @Named
  Category appPreferences() {
    var stringProperty = new SimpleStringProperty("String");
    var booleanProperty = new SimpleBooleanProperty(true);
    return Category.of(
      "Category title 1",
      Setting.of("Setting title 1", stringProperty), // creates a group automatically
      Setting.of("Setting title 2", booleanProperty) // which contains both settings
    );
  }

  @Bean
  @Named
  Category category() {
    var integerProperty = new SimpleIntegerProperty(12);
    var doubleProperty = new SimpleDoubleProperty(6.5);
    return Category.of("Category title 2")
      .expand()                                       // Expand the parent category in the tree-view
      .subCategories( // adds a subcategory to "Category title 2"
        Category.of(
          "Category title 3",
          Group.of(
            "Group title 1",
            Setting.of("Setting title 3", integerProperty)
          ),
          Group.of( // group without title
            Setting.of("Setting title 3", doubleProperty)
          )
        )
      );
  }

  @Bean
  PreferencesFx preferencesFx(@NotNull List<Category> categories) {
    return PreferencesFx.of(AppPreferences.class, categories.toArray(new Category[0]));
  }
}
