package com.example.cst438project1;

import android.content.Context;

import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.GradeCategoryDAO;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.cst438project1", appContext.getPackageName());
    }

    private GradeCategoryDAO gradeCategoryDAO;
    private AppDatabase db;

    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        gradeCategoryDAO = db.getGradeCategoryDao();
    }

    @Before
    public void setUp() throws Exception {
        createDb();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }


    @Test
    public void insert() {
        GradeCategory g = new GradeCategory("Homework",10.00, 123);
        gradeCategoryDAO.insert(g);
        List<GradeCategory> DBValues = gradeCategoryDAO.getGradeCategories();

        assertEquals(1,DBValues.size());

        assertEquals(g.getTitle(), DBValues.get(0).getTitle());
        //assertEquals(g.getWeight(),DBValues.get(0).getWeight());
        assertEquals(g.getGradeId(),DBValues.get(0).getGradeId());

    }

    @Test
    public void update() {
        GradeCategory g = new GradeCategory("Homework",15.50,111);
        gradeCategoryDAO.insert(g);
        List<GradeCategory> DBValues = gradeCategoryDAO.getGradeCategories();

        g = DBValues.get(0);
        g.setTitle("Quizzes");
        g.setWeight(10.00);
        g.setGradeId(112);
        gradeCategoryDAO.update(g);

        List<GradeCategory> UpdatedDB = gradeCategoryDAO.getGradeCategories();
        GradeCategory updated = UpdatedDB.get(0);

        assertEquals(g.getTitle(), updated.getTitle());
        assertEquals(g.getGradeId(), updated.getGradeId());
    }

    @Test
    public void delete() {
        GradeCategory g = new GradeCategory("Homework",10.00,111);
        gradeCategoryDAO.insert(g);
        List<GradeCategory> DBValues = gradeCategoryDAO.getGradeCategories();

        assertEquals(1,DBValues.size());

        gradeCategoryDAO.delete(g);

        List<GradeCategory> DBUpdated = gradeCategoryDAO.getGradeCategories();



        GradeCategory updated = DBUpdated.get(0);

        //Should Pass
        assertEquals(0, DBUpdated.size());
        assertNotEquals(g.getTitle(),updated.getTitle());
        assertNotEquals(g.getGradeId(),updated.getGradeId());

        // Should Fail
//        assertEquals(g.getTitle(),DBUpdated.get(0).getTitle());
//        assertEquals(g.getGradeId(),DBUpdated.get(0).getGradeId());

    }


}

