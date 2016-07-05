package rest;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.Category;

import java.io.IOException;

/**
 * Created by mkolbusz on 6/29/16.
 */
public class CategoryAdapter extends TypeAdapter<Category> {

    @Override
    public void write(JsonWriter jsonWriter, Category category) throws IOException {
        if (category == null){
            jsonWriter.nullValue();
            return;
        }

        jsonWriter.value(category.toString());
    }

    @Override
    public Category read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
