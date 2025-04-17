package com.myTraining.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.day.cq.wcm.api.components.Component;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.inject.Inject;
import java.util.List;

// Defining the Sling Model for the MultiField component
@Model(adaptables = Component.class, adapters = { ComponentExporter.class }, 
       resourceType = "myTraining/components/news-multifield",
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsMultiFieldModel implements ComponentExporter {

    // Injecting the list of news items
    @Inject
    private List<NewsItem> newsItems;

    // Getter method to fetch the news items
    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    // Exported type for JSON support
    @Override
    public String getExportedType() {
        return "myTraining/components/news-multifield";
    }

    // Nested class for each news item
    public static class NewsItem {
        @ValueMapValue
        private String title;

        @ValueMapValue
        private String source;

        // Getter method for title
        public String getTitle() {
            return title;
        }

        // Getter method for source
        public String getSource() {
            return source;
        }
    }
}
